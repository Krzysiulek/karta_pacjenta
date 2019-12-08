package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Doctor;
import karta_pacjenta.pacjent_service.Repositories.DiseasesRepository;
import karta_pacjenta.pacjent_service.Repositories.DoctorRepository;
import karta_pacjenta.pacjent_service.Repositories.MyAppUsersRepository;
import karta_pacjenta.pacjent_service.Repositories.PatientsRepository;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Diseases;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import karta_pacjenta.pacjent_service.Models.Enums.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MyAppUsersRepository myAppUsersRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private DiseasesRepository diseasesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("")
    public void addTestUsers() {
        MyServiceUser us1 = myAppUsersRepository.save(new MyServiceUser(11231L, "admin", passwordEncoder.encode("admin"), "admin@email.ru",
                new HashSet<>(Arrays.asList(UserRoles.ADMIN.getRole(), UserRoles.PATIENT.getRole(), UserRoles.DOCTOR.getRole())),
                "Kris", "Admin", "Kutno", "12312414", "124124424"));

        MyServiceUser us2 = myAppUsersRepository.save(new MyServiceUser(211231L, "patient", passwordEncoder.encode("patient"), "admin@email.ru",
                new HashSet<>(Arrays.asList(UserRoles.PATIENT.getRole())),
                "Kris", "Patient", "Kutno", "12312414", "124124424"));

        MyServiceUser us3 = myAppUsersRepository.save(new MyServiceUser(3123123L, "patient2", passwordEncoder.encode("patient2"), "admin@email.ru",
                new HashSet<>(Arrays.asList(UserRoles.PATIENT.getRole())),
                "Blaise", "Patient", "Kutno", "12312414", "124124424"));

        MyServiceUser us4 = myAppUsersRepository.save(new MyServiceUser(412312323123L, "doctor", passwordEncoder.encode("doctor"), "admin@email.ru",
                new HashSet<>(Arrays.asList(UserRoles.DOCTOR.getRole())),
                "Blaise", "Doctor", "Kutno", "12312414", "124124424"));

        patientsRepository
                .save(new Patient(us1.getUserId()));

        patientsRepository
                .save(new Patient(us2.getUserId()));

        patientsRepository
                .save(new Patient(us3.getUserId()));



        diseasesRepository.save(new Diseases(8123, "Ospa", "Kropelkowe", "blablabla to jest jakiś opis"));
        diseasesRepository.save(new Diseases(339, "Różyczka", "Kropelkowe", "blablabla to jest jakiś opis"));
        diseasesRepository.save(new Diseases(11230, "HIV", "Płciowe", "blablabla to jest jakiś opis"));
        diseasesRepository.save(new Diseases(11231, "Grypa", "Zakaźne", "blablabla to jest jakiś opis"));

        doctorRepository.save(new Doctor(1212L, us1.getUserId(), true, null, null));
        doctorRepository.save(new Doctor(133L, us4.getUserId(), true, null, null));
    }
}
