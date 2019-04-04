package org.softuni.university.validation.implementations;

import org.softuni.university.domain.models.service.RoleServiceModel;
import org.softuni.university.validation.RoleValidationService;

public class RoleValidationServiceImpl implements RoleValidationService {
    @Override
    public boolean isValid(RoleServiceModel role) {
        return role != null;
    }
}
