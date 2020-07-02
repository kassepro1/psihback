package com.groupepsih.psihback;

import com.groupepsih.psihback.domaine.AppUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegartionTest {
    private final String URL = "/users";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void saveAppUser_souldSaveAndReturnAppUser() {
        //arrange
        AppUser appUser = new AppUser("Momar", "KASSE", "kassepro", "momartallakasse@gmail.com");
        //action
        ResponseEntity<AppUser> response = testRestTemplate.postForEntity(URL, appUser, AppUser.class);
        //assertion
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getFirstname()).isEqualTo("Momar");
        assertThat(response.getBody().getLastname()).isEqualTo("KASSE");
        assertThat(response.getBody().getUsername()).isEqualTo("kassepro");
        assertThat(response.getBody().getEmail()).isEqualTo("momartallakasse@gmail.com");

    }
}
