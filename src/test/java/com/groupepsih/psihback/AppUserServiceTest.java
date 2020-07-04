package com.groupepsih.psihback;

import com.groupepsih.psihback.domaine.AppUser;
import com.groupepsih.psihback.domaine.dto.AppResponse;
import com.groupepsih.psihback.repositories.AppUserRepository;
import com.groupepsih.psihback.service.AppUserService;
import com.groupepsih.psihback.service.AppUserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        ResponseEntity<AppResponse> user = appUserService.saveAppUser(appUser);
        //assertion
        assertThat(user.getBody().isEtat());
        assertThat(user.getBody().getMessage()).isEqualTo("Utilisateur ajoute avec succes");
    }

    @Test
    public void findAllAppUser_souldReturnAllAppUser(){
        //arrange
        List<AppUser> appUsers = Arrays.asList(
             new  AppUser(1L,"Momar", "KASSE", "kassepro", "momartallakasse@gmail.com"),
                new AppUser(2L,"Eliote", "ANDERSON", "eanderson", "eanderson@gmail.com")
        );

        //action
        when(appUserRepository.findAll()).thenReturn(appUsers);
        List<AppUser> appUserList = appUserService.findAllAppUser();

        //assertion
        assertThat(appUsers.size()).isEqualTo(2);
        assertThat(appUsers.size()).isEqualTo(appUserList.size());

    }
    @Test
    public void deleteAppUser(){
        //Action
        doNothing().when(appUserRepository).deleteById(1L);
        ResponseEntity<AppResponse> res =  appUserService.deleteAppUser(1L);
        //assertion
        assertThat(res.getBody().getMessage()).isEqualTo("Utilisateur supprimé avec succes");

    }

    @Test
    public void findUserByEmail(){
        //arrange
        AppUser appUser = new AppUser("Momar", "KASSE", "kassepro", "momartallakasse@gmail.com");

        //action
        when(appUserRepository.findByEmail(anyString())).thenReturn(appUser);
        Boolean rep = appUserService.findAppUserByEmail("momartallakasse@gmail.com");

        //assertion
        assertEquals(true,rep);
    }

    @Test
    public void findAppUserByUsername(){
        //arrange
        AppUser appUser = new AppUser("Momar", "KASSE", "kassepro", "momartallakasse@gmail.com");

        //action
        when(appUserRepository.findByUsername(anyString())).thenReturn(appUser);
        Boolean rep = appUserService.findAppUserByUsername("kassepro");

        //assertion
        assertEquals(true,rep);
    }


}
