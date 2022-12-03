package com.progressoft.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.dtos.cheques.ChequeRequest;
import com.progressoft.dtos.cheques.ChequeResponse;
import com.progressoft.service.ChequeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest(ChequeController.class)
@SpringBootTest
@AutoConfigureMockMvc
class ChequeControllerTest {

    private static List<ChequeResponse> listOfCheques;

    @BeforeAll
    public static void setup() {
//        List<ChequeResponse> list;
        try {
            listOfCheques = new ObjectMapper().readValue(Paths.get("src/main/resources/static/cheques-samples.json").toFile(),
                    new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        list.forEach(cheque -> {
//            try {
//                listOfCheques.add(new ObjectMapper().writeValueAsString(cheque));
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ChequeService service;

    @Test
    void create() throws Exception {
        System.out.println("This test is ok i think");

        mvc.perform( MockMvcRequestBuilders
                    .post("/cheques")
                    .content(new ObjectMapper().writeValueAsString(listOfCheques.get(0)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void getAll() {

    }

    @Test
    void updateById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void submitById() {
    }

//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}