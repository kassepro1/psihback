package com.groupepsih.psihback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupepsih.psihback.domaine.AppUser;
import com.groupepsih.psihback.domaine.dto.AppResponse;
import com.groupepsih.psihback.repositories.AppUserRepository;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegartionTest {
    private final String URL = "/appusers";
    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private AppUserRepository appUserRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void saveAppUser_souldSaveAndReturnAppUser() {
        //arrange
        AppUser appUser = new AppUser("Momar", "KASSE", "kassepro", "momartallakasse@gmail.com");
        //action

        ResponseEntity<AppResponse> response = testRestTemplate.postForEntity(URL, appUser, AppResponse.class);
        //assertion
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getMessage()).isEqualTo("Utilisateur ajoute avec succes");

    }

    @Test
    public void findAllAppUser_souyldReturnAllAppUser() throws JsonProcessingException, JSONException {
        //arrange
        List<AppUser> appUsers = Arrays.asList(
                new AppUser(1L, "Momar", "KASSE", "kassepro", "momartallakasse@gmail.com"),
                new AppUser(2L, "Eliote", "ANDERSON", "eanderson", "eanderson@gmail.com")
        );

        //action
        when(appUserRepository.findAll()).thenReturn(appUsers);
        String exceptList = objectMapper.writeValueAsString(appUsers);
        ResponseEntity<String> response = testRestTemplate.getForEntity(URL, String.class);

        //assertion
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONAssert.assertEquals(response.getBody(), exceptList, false);

        verify(appUserRepository, times(1)).findAll();
    }

    @Test
    public void deleteAppUserById(){

        //Action
        doNothing().when(appUserRepository).deleteById(1L);
        HttpEntity<String> entity = new HttpEntity<>(null,new  HttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange("/appusers/1", HttpMethod.DELETE,entity, String.class);
        //assertion
        assertEquals(response.getStatusCode(),HttpStatus.OK);
        verify(appUserRepository,times(1)).deleteById(1L);
    }
}
