package com.groupepsih.psihback.controller;

import com.groupepsih.psihback.domaine.AppUser;
import com.groupepsih.psihback.domaine.dto.AppResponse;
import com.groupepsih.psihback.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AppUserController {

    private final AppUserService appUserService;
    @PostMapping( "/appusers")
    public ResponseEntity<AppResponse> saveAppUser(@RequestBody @Valid AppUser appUser){
        return appUserService.saveAppUser(appUser);
    }

    @PutMapping( "/appusers/{id}")
    public ResponseEntity<AppResponse> updateAppUser(@PathVariable("id") Long id ,@RequestBody @Valid AppUser appUser){
        return appUserService.updateAppUser(id,appUser);
    }

    @GetMapping( "/appusers")
    public ResponseEntity<List<AppUser>> findAllAppUser(){
        return new ResponseEntity<>(appUserService.findAllAppUser(), HttpStatus.OK);
    }

    @DeleteMapping("/appusers/{id}")
    public ResponseEntity<AppResponse> deleteAppUser(@PathVariable("id") Long id){
        return appUserService.deleteAppUser(id);
    }

    @GetMapping( "/appusers/email/{email}")
    public ResponseEntity<?> findAppUserByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(appUserService.findAppUserByEmail(email), HttpStatus.OK);
    }
    @GetMapping( "/appusers/username/{username}")
    public ResponseEntity<?> findAppUserByUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(appUserService.findAppUserByUsername(username), HttpStatus.OK);
    }
}
