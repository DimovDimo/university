package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.binding.ContactAddBindingModel;
import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.domain.models.view.ContactAllViewModel;
import org.softuni.university.service.ContactService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/contact")
public class ContactController extends BaseController {
    private final ContactService contactService;
    private final ModelMapper mapper;

    public ContactController(ContactService contactService, ModelMapper mapper) {
        this.contactService = contactService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addContact() {
        return super.view("contact/add-contact");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addContactConfirm(@ModelAttribute ContactAddBindingModel model) throws Exception {
        ContactServiceModel contactServiceModel = this.mapper.map(model, ContactServiceModel.class);
        createContact(contactServiceModel);

        return super.view("contact/thanks-contact");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    public ModelAndView allContacts(ModelAndView modelAndView) {
        findAllContacts(modelAndView);

        return super.view("contact/all-contacts", modelAndView);
    }

    private void findAllContacts(ModelAndView modelAndView) {
        modelAndView.addObject("contacts", this.contactService.findAllContacts()
                .stream()
                .map(contactServiceModel -> this.mapper.map(contactServiceModel, ContactAllViewModel.class))
                .collect(Collectors.toList()));
    }

    private void createContact(ContactServiceModel contactServiceModel) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        this.contactService.createContact(
                contactServiceModel.getTitle(),
                contactServiceModel.getDescription(),
                name
        );
    }
}
