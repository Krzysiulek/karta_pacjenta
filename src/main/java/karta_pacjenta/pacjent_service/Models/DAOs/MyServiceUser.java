package karta_pacjenta.pacjent_service.Models.DAOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;

@Entity
@Data
@Table(name = "myAppUsers")
@AllArgsConstructor
public class MyServiceUser {

    // TODO: 2019-11-06
    //  - split into patient and doctor
    //  - placówki medyczne


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userName;
    private String password;
    private String email;
    private HashSet<String> roles;

    public MyServiceUser(String username, String password, String email) {
        this.userName = username;
        this.password = password;
        this.email = email;
        roles = new HashSet<String>(Collections.singleton("ROLE_USER"));
    }

    public MyServiceUser(String username, String password, String email, HashSet<String> roles) {
        this.userName = username;
        this.password = password;
        this.email = email;
        this.roles = roles;

        if (roles.isEmpty()) {
            roles = new HashSet<String>(Collections.singleton("ROLE_USER"));
        }
    }

    public MyServiceUser() {}
}
