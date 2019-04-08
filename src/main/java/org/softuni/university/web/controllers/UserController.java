package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.binding.UserEditBindingModel;
import org.softuni.university.domain.models.binding.UserRegisterBindingModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.domain.models.view.UserAllViewModel;
import org.softuni.university.domain.models.view.UserProfileViewModel;
import org.softuni.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register() {
        return super.view("register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model) throws Exception {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return super.view("register");
        }

        this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class));

        return super.redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return super.view("login");
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView) {
        findUserByUserName(principal, modelAndView);

        return super.view("profile", modelAndView);
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(Principal principal, ModelAndView modelAndView) {
        editProfileUserByUserName(principal, modelAndView);

        return super.view("edit-profile", modelAndView);
    }

    @PatchMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@ModelAttribute UserEditBindingModel model) {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return super.view("edit-profile");
        }

        this.userService.editUserProfile(this.modelMapper.map(model, UserServiceModel.class), model.getOldPassword());

        return super.redirect("/users/profile");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    public ModelAndView allUsers(ModelAndView modelAndView) {
        List<UserAllViewModel> users = findAllUsers();
        modelAndView.addObject("users", users);

        return super.view("all-users", modelAndView);
    }

    @PostMapping("/set-student/{id}")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    public ModelAndView setStudent(@PathVariable String id) throws Exception {
        this.userService.setUserRole(id, "student");

        return super.redirect("/users/all");
    }

    @PostMapping("/set-public-relations/{id}")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    public ModelAndView setPublicRelations(@PathVariable String id) throws Exception {
        this.userService.setUserRole(id, "public");

        return super.redirect("/users/all");
    }

    @PostMapping("/set-chair-of-a-department/{id}")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    public ModelAndView setChair(@PathVariable String id) throws Exception {
        this.userService.setUserRole(id, "chair");

        return super.redirect("/users/all");
    }

    @PostMapping("/set-dean/{id}")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    public ModelAndView setDean(@PathVariable String id) throws Exception {
        this.userService.setUserRole(id, "dean");

        return super.redirect("/users/all");
    }

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    private List<UserAllViewModel> findAllUsers() {
        return this.userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserAllViewModel user = this.modelMapper.map(u, UserAllViewModel.class);
                    user.setAuthorities(u.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet()));

                    return user;
                })
                .collect(Collectors.toList());
    }

    private void editProfileUserByUserName(Principal principal, ModelAndView modelAndView) {
        modelAndView
                .addObject("model",
                        this.modelMapper.map(
                                this.userService.findUserByUserName(principal.getName()),
                                UserProfileViewModel.class)
                );
    }

    private void findUserByUserName(Principal principal, ModelAndView modelAndView) {
        modelAndView
                .addObject("model",
                        this.modelMapper.map(
                                this.userService.findUserByUserName(principal.getName()),
                                UserProfileViewModel.class)
                );
    }
}
