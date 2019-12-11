package karta_pacjenta.pacjent_service.Controllers.UserValidation;

import karta_pacjenta.pacjent_service.Models.DTOs.UserValidationDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("")
public class LoginController {
    @CrossOrigin
    @GetMapping("/login")
    public UserValidationDTO validateLogin() {
        return new UserValidationDTO("User successfully logged in");
    }
}
