package karta_pacjenta.pacjent_service.Controllers.UserValidation;

import karta_pacjenta.pacjent_service.Interfaces.MyAppUsersRepository;
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

    @PutMapping
    public MyServiceUser updateUser(@RequestBody MyServiceUser myServiceUser) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @DeleteMapping("/{userId}")
    private void deleteUser(@PathVariable long userId) {
        myAppUsersRepository.deleteById(userId); // TODO: 2019-11-05 test
    }

    @GetMapping("/currentlyLogged")
    private MyAppUserPrincipal getCurrentyLoggedInUser() {
        return UsersUtils.getCurrentlyLoggedInUser();
    }
}
