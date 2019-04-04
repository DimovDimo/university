package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.entities.Role;
import org.softuni.university.domain.models.service.RoleServiceModel;
import org.softuni.university.repository.RoleRepository;
import org.softuni.university.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class RoleServiceTests {
    @Autowired
    private RoleService service;

    @MockBean
    private RoleRepository roleRepository;

    @Test(expected = Exception.class)
    public void findAllRoles_whenEmptyRoles_returnEmptyRoles() throws Exception {
        List<Role> roles = new ArrayList<>();
        when(roleRepository.findAll())
                .thenReturn(roles);

        var result = service.findAllRoles();
        assertTrue(result.isEmpty());
    }

    @Test(expected = Exception.class)
    public void findByAuthority_whenInvalid__throw() throws Exception {
        roleRepository.findByAuthority("Invalid");
    }
}
