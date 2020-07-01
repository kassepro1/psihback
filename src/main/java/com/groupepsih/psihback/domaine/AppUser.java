package com.groupepsih.psihback.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;

    public AppUser(String firstname, String lastname, String username, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
    }
}
