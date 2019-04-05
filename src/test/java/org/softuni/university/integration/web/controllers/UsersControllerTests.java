package org.softuni.university.integration.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UsersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void emptyDB(){
        this.userRepository.deleteAll();
    }

    @Test
    public void login_whenGet_returnsCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/login"))
                .andExpect(view().name("login"));
    }

    @Test
    public void register_whenNoRegister_ReturnsCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/register"))
                .andExpect(view().name("register"));
    }

    @Test
    public void register_whenNoRegister_registerUserCorrectly() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "pesho")
                                .param("password", "passwordPesho")
                                .param("confirmPassword", "passwordPesho")
                                .param("email", "p@p.p")
                );

        Assert.assertEquals(1, this.userRepository.count());
    }

    @Test
    public void register_whenRegister_redirectCorrect() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "pesho")
                                .param("password", "passwordPesho")
                                .param("confirmPassword", "passwordPesho")
                                .param("email", "p@p.p")
                )
                .andExpect(view().name("redirect:/login"));
    }

    // TODO
}
