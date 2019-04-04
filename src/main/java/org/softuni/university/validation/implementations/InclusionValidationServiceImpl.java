package org.softuni.university.validation.implementations;

import org.softuni.university.domain.models.service.InclusionServiceModel;
import org.softuni.university.validation.InclusionValidationService;

public class InclusionValidationServiceImpl implements InclusionValidationService {
    @Override
    public boolean isValid(InclusionServiceModel inclusion) {
        return inclusion != null;
    }
}
