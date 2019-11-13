package karta_pacjenta.pacjent_service.Controllers.UserValidation;

import karta_pacjenta.pacjent_service.Interfaces.MyAppUsersRepository;
import karta_pacjenta.pacjent_service.Interfaces.PatientsRepository;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import karta_pacjenta.pacjent_service.Models.Enums.UserRoles;
import karta_pacjenta.pacjent_service.Services.MyAppUserPrincipal;
import karta_pacjenta.pacjent_service.Utils.UsersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users") // TODO: 2019-11-05 endpoint for admin only
public class UsersController {
    @Autowired
    private MyAppUsersRepository myAppUsersRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    private List<MyServiceUser> getAllUsers() {
        return myAppUsersRepository.findAll();
    }

    @GetMapping("/{userId}")
    private MyServiceUser getUserById(@PathVariable long userId) {
        return myAppUsersRepository
                .findByUserId(userId);
    }

    @PostMapping("/register") // TODO: 2019-11-05 allow endpoint for everyone
    private void registerUser(@RequestBody MyServiceUser myServiceUser) {
        myServiceUser.setPassword(passwordEncoder.encode(myServiceUser.getPassword()));
        myServiceUser.setRoles(new HashSet<>(Collections.singletonList(UserRoles.PATIENT.getRole())));
        myAppUsersRepository.save(myServiceUser);
    }

    @DeleteMapping("/{userId}")
    private void deleteUser(@PathVariable long userId) {
        myAppUsersRepository.deleteById(userId); // TODO: 2019-11-05 test
    }

    @GetMapping("/currentlyLogged")
    private MyAppUserPrincipal getCurrentyLoggedInUser() {
        return UsersUtils.getCurrentlyLoggedInUser();
    }

    @GetMapping("/test")
    private void addTestUsers() {
        MyServiceUser us1 = myAppUsersRepository.save(new MyServiceUser("admin", passwordEncoder.encode("admin"), "admin@admin.pl",
                new HashSet<>(Collections.singleton(UserRoles.ADMIN.getRole()))));

        MyServiceUser us2 = myAppUsersRepository.save(new MyServiceUser("admin1", passwordEncoder.encode("admin"), "admin1@admin.pl",
                new HashSet<>(Collections.singleton(UserRoles.ADMIN.getRole()))));

        MyServiceUser us3 = myAppUsersRepository.save(new MyServiceUser("admin2", passwordEncoder.encode("admin"), "admin2@admin.pl",
                new HashSet<>(Collections.singleton(UserRoles.ADMIN.getRole()))));

        myAppUsersRepository.save(new MyServiceUser("admin3", passwordEncoder.encode("admin"), "admin3@admin.pl",
                new HashSet<>(Collections.singleton(UserRoles.ADMIN.getRole()))));

        patientsRepository
                .save(new Patient(0, us1.getUserId(), "Kris", "Czar", "zala", 123123, 970231231));

        patientsRepository
                .save(new Patient(0, us2.getUserId(), "Pat", "Wenzzzz", "Irlandia", 123123, 980231231));

        patientsRepository
                .save(new Patient(0, us3.getUserId(), "Blas", "Czek", "Piła", 123123, 980231231));
    }
}
