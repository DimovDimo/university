package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.entities.Poll;
import org.softuni.university.validation.service.WenketeValidationService;
import org.softuni.university.validation.service.WenketeValidationServiceImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PollValidationServiceTests {
    private WenketeValidationService service;

    @Before
    public void setupTest() {
        service = new WenketeValidationServiceImpl();
    }

    @Test
    public void isValidWithWenkete_whenValid_true() {
        Poll poll = new Poll();
        boolean result = service.isValid(poll);
        assertTrue(result);
    }

    @Test
    public void isValidWithWenkete_whenNull_false() {
        Poll poll = null;
        boolean result = service.isValid(poll);
        assertFalse(result);
    }
}
