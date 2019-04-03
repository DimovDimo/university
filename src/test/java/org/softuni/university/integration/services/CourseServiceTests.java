package org.softuni.university.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.repository.CourseRepository;
import org.softuni.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceTests {
    @Autowired
    private CourseService service;

    @MockBean
    private CourseRepository mockCourseRepository;

    @Test
    public void createProduct_whenValid_productCreated() {
        CourseServiceModel product = new CourseServiceModel();
        product.setModules(List.of(new ModuleServiceModel()));
        when(mockCourseRepository.save(any()))
                .thenReturn(new Course());

        service.createCourse(product);
        verify(mockCourseRepository)
              .save(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createProduct_whenNull_throw() {
        service.createCourse(null);
        verify(mockCourseRepository)
                .save(any());
    }
}
