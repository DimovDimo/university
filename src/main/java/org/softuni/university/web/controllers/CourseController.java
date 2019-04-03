package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.binding.CourseAddBindingModel;
import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.domain.models.view.CourseAllViewModel;
import org.softuni.university.domain.models.view.CourseDetailsViewModel;
import org.softuni.university.error.CourseNameAlreadyExistsException;
import org.softuni.university.error.CourseNotFoundException;
import org.softuni.university.service.ModuleService;
import org.softuni.university.service.CloudinaryService;
import org.softuni.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController extends BaseController {

    private final CourseService courseService;
    private final CloudinaryService cloudinaryService;
    private final ModuleService moduleService;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseController(CourseService courseService, CloudinaryService cloudinaryService, ModuleService moduleService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.cloudinaryService = cloudinaryService;
        this.moduleService = moduleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCourse() {
        return super.view("course/add-course");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCourseConfirm(@ModelAttribute CourseAddBindingModel model) throws IOException {
        CourseServiceModel courseServiceModel = this.modelMapper.map(model, CourseServiceModel.class);
        courseServiceModel.setModules(
                this.moduleService.findAllModules()
                        .stream()
                        .filter(c -> model.getModules().contains(c.getId()))
                        .collect(Collectors.toList())
        );
        courseServiceModel.setImageUrl(
                this.cloudinaryService.uploadImage(model.getImage())
        );

        this.courseService.createCourse(courseServiceModel);

        return super.redirect("/courses/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allCourses(ModelAndView modelAndView) {
        modelAndView.addObject("courses", this.courseService.findAllCourses()
                .stream()
                .map(p -> this.modelMapper.map(p, CourseAllViewModel.class))
                .collect(Collectors.toList()));

        return super.view("course/all-courses", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detailsCourse(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("course", this.modelMapper.map(this.courseService.findCourseById(id), CourseDetailsViewModel.class));

        return super.view("course/details-course", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCourse(@PathVariable String id, ModelAndView modelAndView) {
        CourseServiceModel courseServiceModel = this.courseService.findCourseById(id);
        CourseAddBindingModel model = this.modelMapper.map(courseServiceModel, CourseAddBindingModel.class);
        model.setModules(courseServiceModel.getModules().stream().map(c -> c.getName()).collect(Collectors.toList()));

        modelAndView.addObject("course", model);
        modelAndView.addObject("courseId", id);

        return super.view("course/edit-course", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCourseConfirm(@PathVariable String id, @ModelAttribute CourseAddBindingModel model) {
        this.courseService.editCourse(id, this.modelMapper.map(model, CourseServiceModel.class));

        return super.redirect("/courses/details/" + id);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCourse(@PathVariable String id, ModelAndView modelAndView) {
        CourseServiceModel courseServiceModel = this.courseService.findCourseById(id);
        CourseAddBindingModel model = this.modelMapper.map(courseServiceModel, CourseAddBindingModel.class);
        model.setModules(courseServiceModel.getModules().stream().map(c -> c.getName()).collect(Collectors.toList()));

        modelAndView.addObject("course", model);
        modelAndView.addObject("courseId", id);

        return super.view("course/delete-course", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCourseConfirm(@PathVariable String id) {
        this.courseService.deleteCourse(id);

        return super.redirect("/courses/all");
    }

    @GetMapping("/fetch/{module}")
    @ResponseBody
    public List<CourseAllViewModel> fetchByModule(@PathVariable String module) {
        if(module.equals("all")) {
            return this.courseService.findAllCourses()
                    .stream()
                    .map(product -> this.modelMapper.map(product, CourseAllViewModel.class))
                    .collect(Collectors.toList());
        }

        return this.courseService.findAllByModule(module)
                .stream()
                .map(product -> this.modelMapper.map(product, CourseAllViewModel.class))
                .collect(Collectors.toList());
    }

    @ExceptionHandler({CourseNotFoundException.class})
    public ModelAndView handleCourseNotFound(CourseNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }

    @ExceptionHandler({CourseNameAlreadyExistsException.class})
    public ModelAndView handleCourseNameALreadyExist(CourseNameAlreadyExistsException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }
}
