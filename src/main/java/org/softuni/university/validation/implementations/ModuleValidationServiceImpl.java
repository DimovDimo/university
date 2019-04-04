package org.softuni.university.validation.implementations;

import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.validation.ModuleValidationService;

public class ModuleValidationServiceImpl implements ModuleValidationService {
    @Override
    public boolean isValid(ModuleServiceModel module) {
        return module != null;
    }
}
