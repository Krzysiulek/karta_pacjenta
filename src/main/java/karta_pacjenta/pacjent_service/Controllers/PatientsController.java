package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Repositories.MyAppUsersRepository;
import karta_pacjenta.pacjent_service.Repositories.PatientsRepository;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import karta_pacjenta.pacjent_service.Models.DTOs.PatientInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/patients")
public class PatientsController {
    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private MyAppUsersRepository myAppUsersRepository;

    // TODO: 2019-11-24 fix this after moving personal infos to MyAppUser. Get infos from more than 1 class
    @GetMapping("")
    public List<PatientInfoTO> getAllPatients() {
        return patientsRepository
                .findAll()
                .stream()
                .map(this::getPatientInfo)
                .collect(Collectors.toList());
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
}
