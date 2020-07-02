package com.groupepsih.psihback.service;

import com.groupepsih.psihback.domaine.AppUser;
import com.groupepsih.psihback.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService  {
    private final AppUserRepository appUserRepository;

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        AppUser uWithUsename=appUserRepository.findByUsername(appUser.getUsername());
        AppUser uWithEmail=appUserRepository.findByEmail(appUser.getEmail());
        if(Objects.isNull(uWithEmail) && Objects.isNull(uWithUsename)){
            return appUserRepository.save(appUser);
        }
        return null;
    }
}
