package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Module;
import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.validation.CourseValidationService;
import org.softuni.university.validation.implementations.CourseValidationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CourseValidationServiceTests {
    private CourseValidationService service;

    @Before
    public void setupTest() {
        service = new CourseValidationServiceImpl();
    }

    @Test
    public void isValidWithProduct_whenValid_true() {
        Course course = new Course();
        course.setModules(List.of(new Module()));
        boolean result = service.isValid(course);
        assertTrue(result);
    }

    @Test
    public void isValidWithProduct_whenNull_false() {
        Course course = null;
        boolean result = service.isValid(course);
        assertFalse(result);
    }

    @Test
    public void isValidWithProductServiceModel_whenNull_false() {
        CourseServiceModel product = null;
        boolean result = service.isValid(product);
        assertFalse(result);
    }

    @Test
    public void isValidWithProductServiceModel_whenValid_true() {
        CourseServiceModel product = new CourseServiceModel();
        product.setModules(List.of(new ModuleServiceModel()));
        boolean result = service.isValid(product);
        assertTrue(result);
    }

    @Test
    public void t1() {
        CourseServiceModel product = new CourseServiceModel();
        product.setModules(null);

        boolean result = service.isValid(product);
        assertFalse(result);
    }


    @Test
    public void t2() {
        CourseServiceModel product = new CourseServiceModel();
        product.setModules(new ArrayList<>());

        boolean result = service.isValid(product);
        assertFalse(result);
    }
}
