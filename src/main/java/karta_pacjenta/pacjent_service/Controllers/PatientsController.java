package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Interfaces.PatientsRepository;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/patients")
public class PatientsController {
    @Autowired
    private PatientsRepository patientsRepository;

    // TODO: 2019-11-24 fix this after moving personal infos to MyAppUser. Get infos from more than 1 class
    @GetMapping
    private List<Patient> getAllPatients() {
        return patientsRepository.findAll();
    }

    @PostMapping
    private Patient addPatient(@RequestBody Patient patient) {
        return patientsRepository.save(patient);
    }
}
