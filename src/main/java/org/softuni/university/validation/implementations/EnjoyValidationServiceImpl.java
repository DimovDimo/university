package org.softuni.university.validation.implementations;

import org.softuni.university.domain.models.service.EnjoyServiceModel;
import org.softuni.university.validation.EnjoyValidationService;

public class EnjoyValidationServiceImpl implements EnjoyValidationService {
    @Override
    public boolean isValid(EnjoyServiceModel enjoy) {
        return enjoy != null;
    }
}
