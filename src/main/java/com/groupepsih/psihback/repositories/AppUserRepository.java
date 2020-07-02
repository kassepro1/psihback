package com.groupepsih.psihback.repositories;

import com.groupepsih.psihback.domaine.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
   AppUser findByEmail(String email);
   AppUser findByUsername(String username);
}
