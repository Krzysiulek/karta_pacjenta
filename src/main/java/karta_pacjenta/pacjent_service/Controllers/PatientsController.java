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

    @GetMapping
    private List<Patient> getAllPatients() {
        return patientsRepository.findAll();
    }

    @PostMapping
    private Patient addPatient(@RequestBody Patient patient) {
        return patientsRepository.save(patient);
    }
}
