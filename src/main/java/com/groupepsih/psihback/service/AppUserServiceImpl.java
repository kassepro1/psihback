package com.groupepsih.psihback.service;

import com.groupepsih.psihback.Exception.BadRequestException;
import com.groupepsih.psihback.Utils.Utils;
import com.groupepsih.psihback.domaine.AppUser;
import com.groupepsih.psihback.domaine.dto.AppResponse;
import com.groupepsih.psihback.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    /**
     * this method permit to save a User
     *
     * @param appUser
     * @return
     */
    @Override
    public ResponseEntity<AppResponse> saveAppUser(AppUser appUser) {
        if(!Utils.isEmailValid(appUser.getEmail())){
            throw new BadRequestException("Email is not valid");
        }
        AppUser uWithUsename = appUserRepository.findByUsername(appUser.getUsername());
        AppUser uWithEmail = appUserRepository.findByEmail(appUser.getEmail());
        if (!Objects.isNull(uWithUsename)) {
            return new ResponseEntity<>(new AppResponse(false, "Username existe deja "), HttpStatus.BAD_REQUEST);
        }
        if (!Objects.isNull(uWithEmail)) {
            return new ResponseEntity<>(new AppResponse(false, "Email existe deja "), HttpStatus.BAD_REQUEST);
        }
        if (Objects.isNull(uWithEmail) && Objects.isNull(uWithUsename)) {
            try {
                appUserRepository.save(appUser);
            } catch (Exception ex) {
                log.info("Erreur d'insertion pour l'utilisateur {} ", appUser.getUsername());
                throw new BadRequestException("Erreur survenue lors de l'insertion de l'utilisateur : " + appUser.getUsername());
            }
            return new ResponseEntity<>(new AppResponse(true, "Utilisateur ajoute avec succes"), HttpStatus.CREATED);
        }
        return null;
    }

    /**
     * this method permit to update a User
     *
     * @param id
     * @param appUser
     * @return
     */
    public ResponseEntity<AppResponse> updateAppUser(Long id, AppUser appUser) {
        Optional<AppUser> appUserToUpdate = appUserRepository.findById(id);
        if (appUserToUpdate.isPresent()) {
            AppUser u = appUserToUpdate.get();
            if (!u.getEmail().equalsIgnoreCase(appUser.getEmail())) {
                AppUser uWithEmail = appUserRepository.findByEmail(appUser.getEmail());
                if (!Objects.isNull(uWithEmail)) {
                    return new ResponseEntity<>(new AppResponse(false, "Email existe deja "), HttpStatus.BAD_REQUEST);
                }
            }
            if (!u.getUsername().equalsIgnoreCase(appUser.getUsername())) {
                AppUser uWithUsername = appUserRepository.findByUsername(appUser.getUsername());
                if (!Objects.isNull(uWithUsername)) {
                    return new ResponseEntity<>(new AppResponse(false, "Username existe deja "), HttpStatus.BAD_REQUEST);
                }
            }
            u.setEmail(appUser.getEmail());
            u.setFirstname(appUser.getFirstname());
            u.setLastname(appUser.getLastname());
            u.setUsername(appUser.getUsername());
            appUserRepository.saveAndFlush(u);
            return new ResponseEntity<>(new AppResponse(true, "Utilisateur modifié avec succes"), HttpStatus.OK);

        } else {
            return null;
        }
    }

    /**
     * this permit to find all users
     *
     * @return
     */

    public List<AppUser> findAllAppUser() {
        return appUserRepository.findAll();
    }

    /**
     * this method permit to delete a user just for test it's not recommended to remove a row in a database
     * @param id
     * @return
     */
    @Override
    public  ResponseEntity<AppResponse> deleteAppUser(Long id) {
        try {
            appUserRepository.deleteById(id);
        } catch (Exception ex) {
            log.info("Erreur d'insertion pour l'utilisateur {} ", id);
            throw new BadRequestException("Erreur lors de la suppresion " );

        }
        return new ResponseEntity<>(new AppResponse(true, "Utilisateur supprimé avec succes"), HttpStatus.OK);

    }

    @Override
    public Boolean findAppUserByEmail(String email) {
        AppUser u = null;
        u = appUserRepository.findByEmail(email);
        if(!Objects.isNull(u)) return true;
        else return false;
    }

    @Override
    public Boolean findAppUserByUsername(String username) {
        AppUser u = null;
        u = appUserRepository.findByUsername(username);
        if(!Objects.isNull(u)) return true;
        else return false;
    }
}
