package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Contact;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.repository.ContactRepository;
import org.softuni.university.validation.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidationService userValidation;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, UserService userService, ModelMapper mapper, UserValidationService userValidation) {
        this.contactRepository = contactRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.userValidation = userValidation;
    }

    @Override
    public void createContact(String title, String description, String name) throws Exception {
        UserServiceModel userModel = userService.findUserByUserName(name);
        if(!userValidation.isValid(userModel)) {
            throw new Exception();
        }

        User user = new User();
        user.setId(userModel.getId());
        Contact contact = new Contact();
        contact.setTitle(title);
        contact.setDescription(description);
        contact.setUser(user);

        contactRepository.save(contact);
    }

    @Override
    public List<ContactServiceModel> findAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(contact -> mapper.map(contact, ContactServiceModel.class))
                .collect(Collectors.toList());
    }
}
