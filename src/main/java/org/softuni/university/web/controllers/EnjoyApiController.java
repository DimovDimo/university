package org.softuni.university.web.controllers;

import org.softuni.university.domain.models.rest.CourseEnjoyRequestModel;
import org.softuni.university.service.EnjoyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/enjoy")
public class EnjoyApiController {
    private final EnjoyService enjoyService;

    public EnjoyApiController(EnjoyService enjoyService) {
        this.enjoyService = enjoyService;
    }

    @PostMapping("/submit")
    @PreAuthorize("isAuthenticated()")
    public int submitEnjoy(@RequestBody CourseEnjoyRequestModel model, Principal principal) throws Exception {
        String name = principal.getName();
        enjoyService.createEnjoy(model.getId(), name);

        return -1;
    }
}
