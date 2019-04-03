package org.softuni.university.validation.implementations;

import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.validation.UserValidationService;
import org.springframework.stereotype.Component;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(UserServiceModel user) {
        return user != null;
    }
}
