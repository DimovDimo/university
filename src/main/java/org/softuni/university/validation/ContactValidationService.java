package org.softuni.university.validation;

import org.softuni.university.domain.models.service.ContactServiceModel;

public interface ContactValidationService {
    boolean isValid(ContactServiceModel contact);
}
