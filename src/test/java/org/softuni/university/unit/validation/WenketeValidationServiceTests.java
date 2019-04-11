package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.entities.Wenkete;
import org.softuni.university.validation.service.WenketeValidationService;
import org.softuni.university.validation.service.WenketeValidationServiceImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WenketeValidationServiceTests {
    private WenketeValidationService service;

    @Before
    public void setupTest() {
        service = new WenketeValidationServiceImpl();
    }

    @Test
    public void isValidWithWenkete_whenValid_true() {
        Wenkete wenkete = new Wenkete();
        boolean result = service.isValid(wenkete);
        assertTrue(result);
    }

    @Test
    public void isValidWithWenkete_whenNull_false() {
        Wenkete wenkete = null;
        boolean result = service.isValid(wenkete);
        assertFalse(result);
    }
}
