package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Repositories.DiseasesRepository;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Diseases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/diseases")
public class DiseasesController {
    @Autowired
    private DiseasesRepository diseasesRepository;

    @GetMapping("")
    public List<Diseases> getAllDiseases() {
        return diseasesRepository.findAll();
    }

    @GetMapping("/{diseaseId}")
    public Optional<Diseases> getDiseaseById(@PathVariable Long diseaseId) {
        return diseasesRepository.findById(diseaseId);
    }

    @PostMapping
    public Diseases addDisease(@RequestBody Diseases diseases) {
        return diseasesRepository.save(diseases);
    }

    @PutMapping
    public Diseases updateDisease(@RequestBody Diseases diseases) throws Exception {
        if (diseasesRepository.existsById(diseases.getDiseaseId())) {
            return diseasesRepository.save(diseases);
        }

        throw new Exception("Such disease does not exists");
    }
}
