package com.groupepsih.psihback.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname", length = 70)
    private String firstname;
    @Column(name = "lastname",length = 70)
    private String lastname;
    @NotNull(message = "Username would not be Null")
    @NotEmpty(message = "Username would not be Empty")
    @Column(name="username",unique = true,nullable = false)
    private String username;
    @NotNull(message = "Email would not be Null")
    @NotEmpty(message = "Email would not be Empty")
    @Column(name="email",unique = true,nullable = false)
    private String email;

    public AppUser(String firstname, String lastname, String username, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
    }
}
