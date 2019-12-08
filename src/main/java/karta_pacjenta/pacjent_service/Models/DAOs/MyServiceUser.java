package karta_pacjenta.pacjent_service.Models.DAOs;

import karta_pacjenta.pacjent_service.Utils.StringEncrypt;
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
    @Convert(converter = StringEncrypt.class)
    private String userName;

    private String password;

    @Convert(converter = StringEncrypt.class)
    private String email;
    private HashSet<String> roles;

    @Convert(converter = StringEncrypt.class)
    private String firstName;

    @Convert(converter = StringEncrypt.class)
    private String lastName;

    @Convert(converter = StringEncrypt.class)
    private String address;

    @Convert(converter = StringEncrypt.class)
    private String phoneNumber;

    @Convert(converter = StringEncrypt.class)
    private String personalIdentityNumber;

    public MyServiceUser(String userName,
                         String password,
                         String email,
                         String firstName,
                         String lastName,
                         String address,
                         String phoneNumber,
                         String personalIdentityNumber) {
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
