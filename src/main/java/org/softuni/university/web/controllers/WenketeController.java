package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.binding.ContactAddBindingModel;
import org.softuni.university.domain.models.binding.WenketeAddBindingModel;
import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.domain.models.service.WenketeServiceModel;
import org.softuni.university.domain.models.view.ContactAllViewModel;
import org.softuni.university.domain.models.view.WenketeAllViewModel;
import org.softuni.university.service.ContactService;
import org.softuni.university.service.WenketeService;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/wenkete")
public class WenketeController extends BaseController {
    private final WenketeService wenketeService;
    private final ModelMapper mapper;

    public WenketeController(WenketeService wenketeService, ModelMapper mapper) {
        this.wenketeService = wenketeService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Add wenkete")
    public ModelAndView addWenkete() {
        return super.view("wenkete/add-wenkete");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Тhanks for your wenkete")
    public ModelAndView addWenketeConfirm(@ModelAttribute WenketeAddBindingModel model) throws Exception {
        WenketeServiceModel wenketeServiceModel = this.mapper.map(model, WenketeServiceModel.class);
//        createContact(contactServiceModel);//TODO
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        wenketeService.createWenkete(wenketeServiceModel, name);

        return super.view("wenkete/thanks-wenkete");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("All wenketes")
    public ModelAndView allWenketes(ModelAndView modelAndView) {
        findAllWenketes(modelAndView);

        return super.view("wenkete/all-wenketes", modelAndView);
    }

    private void findAllWenketes(ModelAndView modelAndView) {
        modelAndView.addObject("wenketes", this.wenketeService.findAllWenketes()
                .stream()
                .map(wenketeServiceModel -> this.mapper.map(wenketeServiceModel, WenketeAllViewModel.class))
                .collect(Collectors.toList()));
    }

    //TODO : remove
//    private void createContact(ContactServiceModel contactServiceModel) throws Exception {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//
//        this.wenketeService.createContact(
//                contactServiceModel.getTitle(),
//                contactServiceModel.getDescription(),
//                name
//        );
//    }
}
