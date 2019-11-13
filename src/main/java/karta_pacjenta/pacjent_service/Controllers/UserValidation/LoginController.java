package karta_pacjenta.pacjent_service.Controllers.UserValidation;

import karta_pacjenta.pacjent_service.Interfaces.MyAppUsersRepository;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import karta_pacjenta.pacjent_service.Models.DTOs.UserValidationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@CrossOrigin
@RestController
@RequestMapping("")
public class LoginController {
    @Autowired
    private MyAppUsersRepository myAppUsersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin
    @GetMapping("/login")
    public UserValidationDTO validateLogin() {
        return new UserValidationDTO("User successfully logged in");
    }

    @GetMapping("/test")
    private void test() {
        myAppUsersRepository
                .save(new MyServiceUser("admin", passwordEncoder.encode("admin"), "spam@spam.spam", new HashSet<>(Arrays.asList("ROLE_USER", "ROLE_ADMIN", "ROLE_WRITER"))));
        myAppUsersRepository
                .save(new MyServiceUser("qwe", passwordEncoder.encode("qwe"), "spam@spam.spam", new HashSet<>(Collections.singletonList("ROLE_BOSS"))));
        myAppUsersRepository
                .save(new MyServiceUser("qwe2", passwordEncoder.encode("qwe"), "spam@spam.spam", new HashSet<>(Collections.singletonList("ROLE_USER"))));
    }
}
