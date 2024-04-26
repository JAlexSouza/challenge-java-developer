package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.VehicleModel;
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
class CreditControllerTest {

    private final String URL_ANALYSIS = "/api/credit/analysis";
    private final String URL_CUSTOM_ANALYSIS = "/api/credit/custom-analysis";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 01 - Should return a credit analysis when a valid client ID is given.")
    void shouldReturnACreditAnalysisWhenAValidIDIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL_ANALYSIS + "/1")))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));

    }

    @Test
    @DisplayName("Test 02 - Should return an error when an invalid client ID is given.")
    void shouldReturnAnErrorWhenAnInvalidIDIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL_ANALYSIS + "/10000")))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));

    }

    @Test
    @DisplayName("Test 03 - Should return an error when an invalid client type ID is given.")
    void shouldReturnAnErrorWhenAnInvalidTypeIDIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL_ANALYSIS + "/k")))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

    }

    @Test
    @DisplayName("Test 04 - Should return an auto credit analysis when a valid client ID and a vehicle model are given.")
    void shouldReturnAnAutoCreditAnalysisWhenAValidIDAndAVehicleModelIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL_ANALYSIS))
                        .param("id", "1")
                        .param("model", VehicleModel.SUV.toString()))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));

    }

    @Test
    @DisplayName("Test 05 - Should return an error when an invalid client ID and a vehicle model are given.")
    void shouldReturnAnErrorWhenAnInvalidClientIDAndAVehicleModelIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL_ANALYSIS))
                        .param("id", "100000")
                        .param("model", VehicleModel.SUV.toString()))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));

    }

    @Test
    @DisplayName("Test 06 - Should return an error when an invalid client type ID and a vehicle model are given.")
    void shouldReturnAnErrorWhenAnInvalidClientTypeIDAndAVehicleModelIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL_ANALYSIS))
                        .param("id", "k")
                        .param("model", VehicleModel.SUV.toString()))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

    }

    @Test
    @DisplayName("Test 07 - Should return an error when a client ID and an invalid type vehicle model are given.")
    void shouldReturnAnErrorWhenAClientIDAndAVehicleModelIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL_ANALYSIS))
                        .param("id", "1")
                        .param("model", "invalid model"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

    }

    @Test
    @DisplayName("Test 08 - Should return custom credit analysis for clients from an age group.")
    void shouldReturnCustomCreditAnalysisForClientsFromAnAgeGroup() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL_CUSTOM_ANALYSIS)))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));

    }

}