package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Inclusion;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.InclusionServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.repository.InclusionRepository;
import org.softuni.university.repository.CourseRepository;
import org.softuni.university.validation.CourseValidationService;
import org.softuni.university.validation.UserValidationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InclusionServiceImpl implements InclusionService {
    private final InclusionRepository inclusionRepository;
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidationService userValidation;
    private final CourseValidationService productValidation;

    public InclusionServiceImpl(
            InclusionRepository inclusionRepository,
            CourseRepository courseRepository,
            UserService userService,
            UserValidationService userValidation,
            CourseValidationService productValidation,
            ModelMapper mapper
    ) {
        this.inclusionRepository = inclusionRepository;
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.userValidation = userValidation;
        this.productValidation = productValidation;
        this.mapper = mapper;
    }

    @Override
    public void createInclusion(String productId, String name) throws Exception {
        UserServiceModel userModel = userService.findUserByUserName(name);
        if(!userValidation.isValid(userModel)) {
            throw new Exception();
        }

        if(!userValidation.isValid(userModel)) {
            throw new Exception();
        }

        Course course = courseRepository.findById(productId)
                .filter(productValidation::isValid)
                .orElseThrow(Exception::new);

        User user = new User();
        user.setId(userModel.getId());
        Inclusion inclusion = new Inclusion();
        inclusion.setCourse(course);
        inclusion.setUser(user);

        inclusionRepository.save(inclusion);
    }

    @Override
    public List<InclusionServiceModel> findAllInclusions() {
        return inclusionRepository.findAll()
                .stream()
                .map(o -> mapper.map(o, InclusionServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InclusionServiceModel> findInclusionsByStudent(String username) {
        return inclusionRepository.findAllByUser_Username(username)
                .stream()
                .map(o -> mapper.map(o, InclusionServiceModel.class))
                .collect(Collectors.toList());
    }
}
