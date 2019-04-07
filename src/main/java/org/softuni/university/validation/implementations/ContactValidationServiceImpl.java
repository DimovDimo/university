package org.softuni.university.validation.implementations;

import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.validation.ContactValidationService;

public class ContactValidationServiceImpl implements ContactValidationService {
    @Override
    public boolean isValid(ContactServiceModel contact) {
        return contact != null;
    }
}
