package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Poll;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.domain.models.service.WenketeServiceModel;
import org.softuni.university.repository.WenketeRepository;
import org.softuni.university.validation.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WenketeServiceImpl implements WenketeService {
    private final WenketeRepository wenketeRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidationService userValidation;

    @Autowired
    public WenketeServiceImpl(
            WenketeRepository wenketeRepository,
            UserService userService, ModelMapper mapper,
            UserValidationService userValidation
    ) {
        this.wenketeRepository = wenketeRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.userValidation = userValidation;
    }


    @Override
    public void createWenkete(WenketeServiceModel wenketeServiceModel, String name) throws Exception {
        UserServiceModel userModel = userService.findUserByUserName(name);
        if(!userValidation.isValid(userModel)) {
            throw new UsernameNotFoundException("Username not found!");
        }

        User user = new User();
        user.setId(userModel.getId());
        Poll poll = this.mapper.map(wenketeServiceModel, Poll.class);
        poll.setUser(user);

        this.wenketeRepository.save(poll);
    }

    @Override
    public List<WenketeServiceModel> findAllWenketes() {
        return wenketeRepository.findAll()
                .stream()
                .map(contact -> mapper.map(contact, WenketeServiceModel.class))
                .collect(Collectors.toList());
    }
}
