package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Inclusion;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.InclusionServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.repository.InclusionRepository;
import org.softuni.university.repository.CourseRepository;
import org.softuni.university.service.InclusionService;
import org.softuni.university.service.UserService;
import org.softuni.university.validation.CourseValidationService;
import org.softuni.university.validation.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InclusionServiceTests {
    @Autowired
    InclusionService service;

    @MockBean
    InclusionRepository mockInclusionRepository;

    @MockBean
    UserValidationService mockUserValidation;

    @MockBean
    UserService mockUserService;

    @MockBean
    CourseRepository mockCourseRepository;

    @MockBean
    CourseValidationService productValidation;

    private List<Inclusion> inclusions;

    @Before
    public void setupTest() {
        inclusions = new ArrayList<>();
        when(mockInclusionRepository.findAll())
                .thenReturn(inclusions);
    }

    @Test
    public void findAllOrders_when1Orders_return1Orders() {
        String customer = "Test customer";
        String productImageUrl = "http://image.url";
        String productName = "course 1";
        BigDecimal productPrice = BigDecimal.valueOf(1.34);

        Inclusion inclusion = new Inclusion();
        inclusion.setUser(new User() {{
            setUsername(customer);
        }});
        inclusion.setCourse(new Course() {{
            setImageUrl(productImageUrl);
            setName(productName);
            setPrice(productPrice);
        }});

        inclusions.add(inclusion);

        var result = service.findAllInclusions();
        InclusionServiceModel orderResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(customer, orderResult.getCustomer());
        assertEquals(productName, orderResult.getName());
        assertEquals(productImageUrl, orderResult.getImageUrl());
        assertEquals(productPrice, orderResult.getPrice());
    }

    @Test
    public void findAllOrders_whenNoOrders_returnEmptyOrders() {
        inclusions.clear();
        var result = service.findAllInclusions();
        assertTrue(result.isEmpty());
    }

    @Test
    public void createOrder_whenUserAndProductAreValid_orderCreated() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(true);
        when(productValidation.isValid(any(Course.class)))
                .thenReturn(true);

        when(mockUserService.findUserByUserName(any()))
                .thenReturn(new UserServiceModel());

        when(mockCourseRepository.findById(any()))
                .thenReturn(java.util.Optional.of(new Course()));

        service.createInclusion("", "");

        verify(mockInclusionRepository)
            .save(any());
    }

    @Test(expected = Exception.class)
    public void createOrder_whenUserIsValidAndProductIsNotValid_throw() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(true);
        when(productValidation.isValid(any(Course.class)))
                .thenReturn(false);

        service.createInclusion("", "");
    }

    @Test(expected = Exception.class)
    public void createOrder_whenUserIsNotValidAndProductIsValid_throw() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(false);
        when(productValidation.isValid(any(Course.class)))
                .thenReturn(true);

        service.createInclusion("", "");
    }

    @Test(expected = Exception.class)
    public void createOrder_whenUserAndProductAreNotValid_throw() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(false);
        when(productValidation.isValid(any(Course.class)))
                .thenReturn(false);

        service.createInclusion("", "");
    }
}
