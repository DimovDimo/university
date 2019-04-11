package org.softuni.university.validation.service;

import org.softuni.university.domain.entities.Wenkete;
import org.springframework.stereotype.Component;

@Component
public class WenketeValidationServiceImpl implements WenketeValidationService {
    @Override
    public boolean isValid(Wenkete wenkete) {
        return wenkete != null;
    }
}
