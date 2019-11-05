package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Interfaces.MyAppUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MyAppUsersRepository myAppUsersRepository;

}
