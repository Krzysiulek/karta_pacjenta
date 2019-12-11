package karta_pacjenta.pacjent_service.Controllers.UserValidation;

import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Doctor;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import karta_pacjenta.pacjent_service.Repositories.DoctorRepository;
import karta_pacjenta.pacjent_service.Repositories.MyAppUsersRepository;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import karta_pacjenta.pacjent_service.Models.Enums.UserRoles;
import karta_pacjenta.pacjent_service.Repositories.PatientsRepository;
import karta_pacjenta.pacjent_service.Services.MyAppUserPrincipal;
import karta_pacjenta.pacjent_service.Utils.UsersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<MyServiceUser> getAllUsers() {
        return myAppUsersRepository.findAll();
    }

    @GetMapping("/{userId}")
    private MyServiceUser getUserById(@PathVariable long userId) {
        return myAppUsersRepository
                .findByUserId(userId);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody MyServiceUser myServiceUser) {
        myServiceUser.setPassword(passwordEncoder.encode(myServiceUser.getPassword()));
        myServiceUser.setRoles(new HashSet<>(Collections.singletonList(UserRoles.PATIENT.getRole())));

        MyServiceUser registredUser = myAppUsersRepository.save(myServiceUser);

        patientsRepository.save(new Patient(registredUser.getUserId()));
    }

    @PutMapping
    public MyServiceUser updateUser(@RequestBody MyServiceUser updatedUser) throws Exception {
        if (updatedUser.getRoles().contains(UserRoles.PATIENT.getRole()) &&
                !myAppUsersRepository
                        .findByUserId(updatedUser.getUserId())
                        .getRoles().contains(UserRoles.PATIENT.getRole()) &&
                !patientsRepository.existsByUserId(updatedUser.getUserId()))
        {
            // TODO: 2019-12-10 add info about grants into another DB
            patientsRepository.save(new Patient(updatedUser.getUserId()));
            return myAppUsersRepository.save(updatedUser);
        }

        if (updatedUser.getRoles().contains(UserRoles.DOCTOR.getRole()) &&
                !myAppUsersRepository
                        .findByUserId(updatedUser.getUserId())
                        .getRoles().contains(UserRoles.DOCTOR.getRole()) &&
                !doctorRepository.existsByUserId(updatedUser.getUserId()))
        {
            // TODO: 2019-12-10 add info about grants into another DB
            doctorRepository.save(new Doctor(0L, updatedUser.getUserId(), false, new ArrayList<>(), new ArrayList<>()));
            return myAppUsersRepository.save(updatedUser);
        }

        return myAppUsersRepository.save(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        // TODO: 2019-12-10 add info about deletion into another DB
        myAppUsersRepository.deleteById(userId);
    }

    @GetMapping("/currentlyLogged")
    public MyAppUserPrincipal getCurrentyLoggedInUser() {
        return UsersUtils.getCurrentlyLoggedInUser();
    }
}
