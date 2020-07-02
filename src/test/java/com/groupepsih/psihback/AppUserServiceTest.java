package com.groupepsih.psihback;

import com.groupepsih.psihback.domaine.AppUser;
import com.groupepsih.psihback.repositories.AppUserRepository;
import com.groupepsih.psihback.service.AppUserService;
import com.groupepsih.psihback.service.AppUserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    private AppUserServiceImpl appUserService;

    @Before
    public void setUp(){
        appUserService = new AppUserServiceImpl(appUserRepository);
    }

    @Test
    public void saveAppUser_souldSaveAndReturnAppUser(){
        AppUser appUser = new AppUser("Momar", "KASSE", "kassepro", "momartallakasse@gmail.com");
           //action
        when(appUserRepository.save(appUser)).thenReturn(appUser);
        AppUser user = appUserService.saveAppUser(appUser);
        //assertion
        assertThat(user.getFirstname()).isEqualTo("Momar");
        assertThat(user.getLastname()).isEqualTo("KASSE");
        assertThat(user.getUsername()).isEqualTo("kassepro");
        assertThat(user.getEmail()).isEqualTo("momartallakasse@gmail.com");
    }

}
