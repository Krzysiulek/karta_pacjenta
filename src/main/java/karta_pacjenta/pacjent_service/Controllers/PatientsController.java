package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import karta_pacjenta.pacjent_service.Models.DTOs.PatientInfoTO;
import karta_pacjenta.pacjent_service.Repositories.MyAppUsersRepository;
import karta_pacjenta.pacjent_service.Repositories.PatientInfoTORepository;
import karta_pacjenta.pacjent_service.Repositories.PatientsRepository;
import karta_pacjenta.pacjent_service.Utils.UsersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("")
    public List<PatientInfoTO> getAllPatients() {
        return patientInfoTORepository.findAllPatients();
    }

    @GetMapping("/{patientId}")
    public PatientInfoTO getPatientById(@PathVariable Long patientId) {
        return getPatientInfo(patientsRepository.findById(patientId).get());
    }

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientsRepository.save(patient);
    }

    /** returns PatientInfoTO based on Patient & Users Class . */
    private PatientInfoTO getPatientInfo(Patient patient) {
        MyServiceUser user = myAppUsersRepository.findById(patient.getUserId()).get();

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
}
