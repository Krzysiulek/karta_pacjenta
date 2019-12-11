package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Repositories.MyAppUsersRepository;
import karta_pacjenta.pacjent_service.Repositories.PatientsRepository;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import karta_pacjenta.pacjent_service.Models.DTOs.PatientInfoTO;
import karta_pacjenta.pacjent_service.Repositories.PatientInfoTORepository;
import karta_pacjenta.pacjent_service.Utils.UsersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/patients")
public class PatientsController {
    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private MyAppUsersRepository myAppUsersRepository;

    @Autowired
    private PatientInfoTORepository patientInfoTORepository;

    // TODO: 2019-11-24 fix this after moving personal infos to MyAppUser. Get infos from more than 1 class
    @GetMapping("")
    public List<PatientInfoTO> getAllPatients() {
        return patientInfoTORepository.findAllPatients();
    }

    @GetMapping("/{patientId}")
    public PatientInfoTO getPatientById(@PathVariable Long patientId) {
        return getPatientInfo(patientsRepository.findById(patientId).get());
    }

    @PostMapping
    private Patient addPatient(@RequestBody Patient patient) {
        return patientsRepository.save(patient);
    }

    /** returns PatientInfoTO based on Patient & Users Class . */
    private PatientInfoTO getPatientInfo(Patient patient) {
        MyServiceUser user = myAppUsersRepository.findById(patient.getUserId()).get();
        System.out.println(user);

        return PatientInfoTO
                .builder()
                .address(user.getAddress())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patientId(patient.getPatientId())
                .personalIdentityNumber(user.getPersonalIdentityNumber())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @GetMapping("/loggedId")
    public Optional<Patient> getActualLoggedInPatient() {
        long userId = UsersUtils.getCurrentlyLoggedInUser().getUser().getUserId();
        return patientsRepository
                .findByUserId(userId);
    }

    @GetMapping("/test")
    public List<PatientInfoTO> test() {
        System.out.println("test");
        System.out.println(LocalDateTime.now());
        List<PatientInfoTO> xd = patientInfoTORepository.findAllPatients();
        System.out.println(LocalDateTime.now());

        return patientInfoTORepository.findAllPatients();
    }

    @GetMapping("/test2")
    public List<PatientInfoTO> test2() {
        System.out.println("test2");
        System.out.println(LocalDateTime.now());
        List<PatientInfoTO> xd = patientsRepository
                .findAll()
                .stream()
                .map(this::getPatientInfo)
                .collect(Collectors.toList());
        System.out.println(LocalDateTime.now());


        return patientInfoTORepository.findAllPatients();
    }
}
