package karta_pacjenta.pacjent_service.Models.DAOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;

@Entity
@Data
@Table(name = "myAppUsers")
@AllArgsConstructor
@NoArgsConstructor
public class MyServiceUser {

    // TODO: 2019-11-06
    //  - split into patient and doctor
    //  - placówki medyczne

    // TODO: 2019-11-24 Add here personal infos

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userName;
    private String password;
    private String email;
    private HashSet<String> roles;

    private String firstName;
    private String lastName;
    private String address;
    private int phoneNumber;
    private long personalIdentityNumber;

    public MyServiceUser(String userName,
                         String password,
                         String email,
                         String firstName,
                         String lastName,
                         String address,
                         int phoneNumber,
                         long personalIdentityNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = new HashSet<String>(Collections.singleton("ROLE_USER"));
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.personalIdentityNumber = personalIdentityNumber;
    }
}
