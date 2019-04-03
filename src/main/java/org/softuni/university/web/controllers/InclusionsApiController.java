package org.softuni.university.web.controllers;

import org.softuni.university.domain.models.rest.CourseOrderRequestModel;
import org.softuni.university.service.InclusionService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/inclusion")
public class InclusionsApiController {
    private final InclusionService inclusionService;

    public InclusionsApiController(InclusionService inclusionService) {
        this.inclusionService = inclusionService;
    }

    @PostMapping("/submit")
    public int submitInclusion(@RequestBody CourseOrderRequestModel model, Principal principal) throws Exception {
        String name = principal.getName();
        inclusionService.createInclusion(model.getId(), name);

        return -1;
    }
}
