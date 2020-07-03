package com.groupepsih.psihback.service;

import com.groupepsih.psihback.domaine.AppUser;
import com.groupepsih.psihback.domaine.dto.AppResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppUserService {
    ResponseEntity<AppResponse> saveAppUser(AppUser appUser);

    ResponseEntity<AppResponse> updateAppUser(Long id, AppUser appUser);

    List<AppUser> findAllAppUser();

    ResponseEntity<AppResponse> deleteAppUser(Long id);

    Boolean findAppUserByEmail(String email);

    Boolean findAppUserByUsername(String username);
}
