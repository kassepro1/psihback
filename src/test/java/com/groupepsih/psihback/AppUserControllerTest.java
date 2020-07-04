package com.groupepsih.psihback;


import com.groupepsih.psihback.domaine.AppUser;
import com.groupepsih.psihback.repositories.AppUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AppUserControllerTest extends AbstractTest {
    private final String URL = "/appusers";

    @MockBean
    private AppUserRepository appUserRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        AppUser appUser = new AppUser(1L,"Momar", "KASSE", "kassepro", "momartallakasse@gmail.com");
        //action
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser));
    }

    @Test
    public void saveAppUser() throws Exception {
        //arrange
        AppUser appUser = createAppUser();
        //action
        when(appUserRepository.save(any(AppUser.class))).thenReturn(appUser);
        mockMvc.perform(post(URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(mapToJson(appUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Utilisateur ajoute avec succes")))
                .andExpect(jsonPath("$.etat", is(true)));
        //assertion
        verify(appUserRepository, times(1)).save(any(AppUser.class));
    }
    @Test
    public void updateAppUser() throws Exception {
        //arrange
        AppUser updatedAppUser = new AppUser(1L,"Momeze", "KASSE", "kassepro", "momartallakasse@gmail.com");
        //action
        when(appUserRepository.saveAndFlush(any(AppUser.class))).thenReturn(updatedAppUser);
        mockMvc.perform(put("/appusers/1")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(mapToJson(updatedAppUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Utilisateur modifié avec succes")));
        //assertion
        verify(appUserRepository, times(1)).saveAndFlush(any(AppUser.class));
    }

    @Test
    public void findAllAppUser() throws Exception {
        //arrange
        List<AppUser> appUsers = Arrays.asList(
                new AppUser(1L, "Momar", "KASSE", "kassepro", "momartallakasse@gmail.com"),
                new AppUser(2L, "Eliote", "ANDERSON", "eanderson", "eanderson@gmail.com")
        );
        //action
        when(appUserRepository.findAll()).thenReturn(appUsers);
        mockMvc.perform(get(URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname", is("Momar")))
                .andExpect(jsonPath("$[0].lastname", is("KASSE")))
                .andExpect(jsonPath("$[0].username", is("kassepro")))
                .andExpect(jsonPath("$[0].email", is("momartallakasse@gmail.com")));
        //assertion
        verify(appUserRepository, times(1)).findAll();
    }
    @Test
    public void deleteAppUserById() throws Exception {

        //Action
        doNothing().when(appUserRepository).deleteById(1L);
        mockMvc.perform(delete("/appusers/1")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //assertion
      verify(appUserRepository,times(1)).deleteById(1L);
    }

    @Test
    public void findAppUserByUsername() throws Exception {

        //Action
        when(appUserRepository.findByUsername(anyString())).thenReturn( createAppUser());

        mockMvc.perform(get("/appusers/username/kassepro")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //assertion
        verify(appUserRepository,times(1)).findByUsername(anyString());
    }

    @Test
    public void findAppUserByEmail() throws Exception {

        //Action
        when(appUserRepository.findByEmail(anyString())).thenReturn( createAppUser());

        mockMvc.perform(get("/appusers/email/momartallakasse@gmail.com")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //assertion
        verify(appUserRepository,times(1)).findByEmail(anyString());
    }

    private AppUser createAppUser(){
        AppUser appUser = new AppUser("Momar", "KASSE", "kassepro", "momartallakasse@gmail.com");
       return appUser;
    }

}
