package com.gsaramago.digitalstorespring.resources;

import com.gsaramago.digitalstorespring.model.DTO.UserDto;
import com.gsaramago.digitalstorespring.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(UserResource.class)
public class UserResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("FindAll gets status 200")
    public void findAllUsersTestAndExpectCode200(){
        var requestBuilder = MockMvcRequestBuilders.get("/users");
        List<UserDto> list = List.of(new UserDto(null, "Maria Brown", "maria@gmail.com", "988888888"));

        Mockito.when(userService.findAll()).thenReturn(list);

        try {
            mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            throw new RuntimeException("Something when wrong in findAllUsersTestAndExpectCode200");
        }
    }

    @Test
    @DisplayName("FindAll gets users with correct attributes")
    public void findAllUsersTestAndExpectUsersNameCorrect(){

        var requestBuilder = MockMvcRequestBuilders.get("/users");

        List<UserDto> list = List.of(new UserDto(1L, "Maria Brown", "maria@gmail.com", "988888888"));

        Mockito.when(userService.findAll()).thenReturn(list);

        try {
            mockMvc.perform(requestBuilder)
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(jsonPath("$.[0].id").value(1L))
                    .andExpect(jsonPath("$.[0].name").value("Maria Brown"))
                    .andExpect(jsonPath("$.[0].email").value("maria@gmail.com"))
                    .andExpect(jsonPath("$.[0].phone").value("988888888"));

        } catch (Exception e) {
            throw new RuntimeException("Something when wrong in findAllUsersTestAndExpectCode200");
        }
    }

}
