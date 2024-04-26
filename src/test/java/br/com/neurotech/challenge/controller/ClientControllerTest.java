package br.com.neurotech.challenge.controller;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class ClientControllerTest {

    private final String URL = "/api/client";
    private final String PATH_NEW_CLIENT = "src/test/java/br/com/neurotech/challenge/json/new-client.json";
    private final String PATH_CLIENT_WITH_INVALID_FIELD = "src/test/java/br/com/neurotech/challenge/json/client-with-invalid-field.json";

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Test 01 - Should create a valid new client.")
    void shouldCreateNewClient() throws Exception {
        String client = Files.readString(Paths.get(PATH_NEW_CLIENT));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(new URI(URL))
                        .content(client)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));

    }

    @Test
    @DisplayName("Test 02 - Should return a client when a valid ID is given")
    void shouldReturnAClient() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL + "/1")))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));

    }

    @Test
    @DisplayName("Test 03 - Should return an error when an invalid ID is given")
    void shouldReturnAnErrorWhenAnInvalidIDIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL + "/100000")))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));

    }

    @Test
    @DisplayName("Test 04 - Should return an error when an invalid type ID is given")
    void shouldReturnAnErrorWhenAnInvalidTypeIDIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL + "/a")))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

    }

    @Test
    @DisplayName("Test 05 - Should return an error when an invalid client field is given.")
    void shouldReturnAnErrorWhenAnInvalidClientFieldIsGiven() throws Exception {
        String client = Files.readString(Paths.get(PATH_CLIENT_WITH_INVALID_FIELD));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(new URI(URL))
                        .content(client)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

    }
}