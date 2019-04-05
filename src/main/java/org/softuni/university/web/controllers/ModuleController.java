package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.binding.ModuleAddBindingModel;
import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.domain.models.view.ModuleViewModel;
import org.softuni.university.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/modules")
public class ModuleController extends BaseController {

    private final ModuleService moduleService;
    private final ModelMapper modelMapper;

    @Autowired
    public ModuleController(ModuleService moduleService, ModelMapper modelMapper) {
        this.moduleService = moduleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView addModule() {
        return super.view("module/add-module");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView addModuleConfirm(@ModelAttribute ModuleAddBindingModel model) throws Exception {
        this.moduleService.addModule(this.modelMapper.map(model, ModuleServiceModel.class));

        return super.redirect("/modules/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView allModule(ModelAndView modelAndView) {
        modelAndView.addObject("modules",
                this.moduleService.findAllModules()
                        .stream()
                        .map(c -> this.modelMapper.map(c, ModuleViewModel.class))
                        .collect(Collectors.toList())
        );

        return super.view("module/all-modules", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView editModule(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("model",
                this.modelMapper.map(this.moduleService.findModuleById(id), ModuleViewModel.class)
        );

        return super.view("module/edit-module", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView editModuleConfirm(@PathVariable String id, @ModelAttribute ModuleAddBindingModel model) {
        this.moduleService.editModule(id, this.modelMapper.map(model, ModuleServiceModel.class));

        return super.redirect("/modules/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView deleteModule(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("model",
                this.modelMapper.map(this.moduleService.findModuleById(id), ModuleViewModel.class)
        );

        return super.view("module/delete-module", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView deleteModuleConfirm(@PathVariable String id) {
        this.moduleService.deleteModule(id);

        return super.redirect("/modules/all");
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    @ResponseBody
    public List<ModuleViewModel> fetchModules() {
        return this.moduleService.findAllModules()
                .stream()
                .map(c -> this.modelMapper.map(c, ModuleViewModel.class))
                .collect(Collectors.toList());
    }
}
