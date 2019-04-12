package org.softuni.university.validation.service;

import org.softuni.university.domain.entities.Poll;

public interface WenketeValidationService {
    boolean isValid(Poll poll);
}
