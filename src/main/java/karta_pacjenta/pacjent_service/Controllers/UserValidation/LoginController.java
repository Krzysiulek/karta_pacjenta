package karta_pacjenta.pacjent_service.Controllers.UserValidation;

import karta_pacjenta.pacjent_service.Interfaces.MyAppUsersRepository;
import karta_pacjenta.pacjent_service.Models.DTOs.UserValidationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
