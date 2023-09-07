package com.gsaramago.digitalstorespring.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsaramago.digitalstorespring.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Create a valid user and get status 200 test")
    public void createValidUserIntegrationTest(){
        User user = new User(null, "Paul Blues"
                , "paul@gmail.com", "988888888", "AbCdEf1234");

        var requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON);
        try {

            this.mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(print());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Create an user with invalid name and get status 400 test")
    public void createUserWithInvalidNameTest(){
        User user = new User(null, "a"
                , "a@gmail.com", "988888888", "AbCdEf1234");

        var requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON);
        try {

            this.mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(print());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Create an user with invalid password and get status 400 test")
    public void createUserWithInvalidPasswordTest(){
        User user = new User(null, "Paul Blues"
                , "paul@gmail.com", "988888888", "123");

        var requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON);
        try {

            this.mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(print());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Delete an existing user without orders and get status 204 test")
    public void deleteExistingUserTest(){

        var requestBuilder = MockMvcRequestBuilders.delete("/users/3");

        try {

            this.mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isNoContent())
                    .andDo(print());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String asJsonString(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
