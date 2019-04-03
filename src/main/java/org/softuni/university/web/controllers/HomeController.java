package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.view.ModuleViewModel;
import org.softuni.university.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    private final ModuleService moduleService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(ModuleService moduleService, ModelMapper modelMapper) {
        this.moduleService = moduleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView index() {
        return super.view("index");
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.addObject("modules", this.moduleService.findAllModules().stream().map(module -> this.modelMapper.map(module, ModuleViewModel.class)).collect(Collectors.toList()));

        return super.view("home", modelAndView);
    }
}
