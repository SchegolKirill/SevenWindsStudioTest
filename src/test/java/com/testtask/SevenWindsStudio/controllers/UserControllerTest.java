package com.testtask.SevenWindsStudio.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.SevenWindsStudio.entities.User;
import com.testtask.SevenWindsStudio.repositories.UserRepository;
import com.testtask.SevenWindsStudio.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUser() throws Exception {
        Integer id = createTestUser("Schegol", "Kirill", "Borisovich", "schegol.kirill@gmail.com",
                "89210900302").getId();
        mockMvc.perform(
                        get("user/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.surname").value("Schegol"))
                .andExpect(jsonPath("$.name").value("Kirill"))
                .andExpect(jsonPath("$.patronymic").value("Borisovich"))
                .andExpect(jsonPath("$.email").value("schegol.kirill@gmail.com"))
                .andExpect(jsonPath("$.phone").value("89210900302"));
    }

    @Test
    void addUser() throws Exception {
        User testUser = new User("Schegol", "Kirill", "Borisovich", "schegol.kirill@gmail.com",
                "89210900302");

        mockMvc.perform(post("/user")
                        .content(objectMapper.writeValueAsString(testUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getAllUsers() throws Exception {
        Integer id1 = createTestUser("Schegol", "Kirill", "Borisovich", "schegol.kirill@gmail.com",
                "89210900302").getId();
        Integer id2 = createTestUser("Schegol2", "Kirill2", "Borisovich2", "schegol2.kirill@gmail.com",
                "289210900302").getId();
        mockMvc.perform(get("/user/users")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(userService.getUser(id1), userService.getUser(id2)))));
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    private User createTestUser(String surname, String name, String patronymic, String email, String phone) {
        User user = new User(surname, name, patronymic, email, phone);
        return repository.save(user);
    }
}
