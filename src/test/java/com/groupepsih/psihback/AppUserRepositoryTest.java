package com.groupepsih.psihback;

import com.groupepsih.psihback.domaine.AppUser;
import com.groupepsih.psihback.repositories.AppUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    @Test
    public void saveAppUser() {
        //arrange
        AppUser appUser = new AppUser("Momar", "KASSE", "kassepro", "momartallakasse@gmail.com");
        //action
        AppUser createdUser = appUserRepository.save(appUser);
        //assertion
        assertThat(createdUser.getFirstname()).isEqualTo("Momar");
        assertThat(createdUser.getLastname()).isEqualTo("KASSE");
        assertThat(createdUser.getUsername()).isEqualTo("kassepro");
        assertThat(createdUser.getEmail()).isEqualTo("momartallakasse@gmail.com");

    }
}
