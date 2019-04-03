package org.softuni.university.validation;

import org.softuni.university.domain.models.service.UserServiceModel;

public interface UserValidationService {
    boolean isValid(UserServiceModel user);
}
