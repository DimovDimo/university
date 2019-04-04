package org.softuni.university.validation;

import org.softuni.university.domain.models.service.RoleServiceModel;

public interface RoleValidationService {
    boolean isValid(RoleServiceModel role);
}
