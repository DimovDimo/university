package org.softuni.university.service;

import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.domain.models.service.InclusionServiceModel;

import java.util.List;

public interface ContactService {

    void createContact(String title, String description, String name) throws Exception;

    List<ContactServiceModel> findAllContacts();
}
