package org.softuni.university.web.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.softuni.university.domain.models.view.InclusionViewModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.modelmapper.ModelMapper;

import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.domain.models.view.CourseDetailsViewModel;
import org.softuni.university.service.InclusionService;
import org.softuni.university.service.CourseService;

@Controller
@RequestMapping("/inclusion")
public class InclusionsController extends BaseController {
    private final CourseService courseService;
    private final InclusionService inclusionService;
    private final ModelMapper mapper;

    public InclusionsController(
        CourseService courseService,
        InclusionService inclusionService,
        ModelMapper modelMapper
    ){
        this.courseService = courseService;
        this.inclusionService = inclusionService;
        this.mapper = modelMapper;
    }

    @GetMapping("/course/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView inclusionCourse(@PathVariable String id, ModelAndView modelAndView) {
        CourseServiceModel serviceModel = courseService.findCourseById(id);
        CourseDetailsViewModel viewModel = mapper.map(serviceModel, CourseDetailsViewModel.class);
        modelAndView.addObject("course", viewModel);
        return view("inclusion/course", modelAndView);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllInclusions(ModelAndView modelAndView) {
        List<InclusionViewModel> viewModels = inclusionService.findAllInclusions()
                .stream()
                .map(o -> mapper.map(o, InclusionViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("inclusions", viewModels);
        return view("inclusion/list-inclusions", modelAndView);
    }

    @GetMapping("/strudent")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getStudentInclusions(ModelAndView modelAndView, Principal principal) {
        String username = principal.getName();
        List<InclusionViewModel> viewModels = inclusionService.findInclusionsByStudent(username)
                .stream()
                .map(o -> mapper.map(o, InclusionViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("inclusions", viewModels);

        return view("inclusion/list-inclusions", modelAndView);
    }
}
