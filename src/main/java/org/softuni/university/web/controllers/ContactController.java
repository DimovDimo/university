package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.service.ContactService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contact")
public class ContactController extends BaseController {
//    private final ContactService contactService;
//    private final ModelMapper mapper;
//
//    public ContactController(ContactService contactService, ModelMapper mapper) {
//        this.contactService = contactService;
//        this.mapper = mapper;
//    }

    @GetMapping("/form")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addContact() {
        return super.view("contact/form");
    }
}
