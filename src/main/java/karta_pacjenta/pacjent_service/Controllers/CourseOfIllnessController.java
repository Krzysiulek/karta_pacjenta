package karta_pacjenta.pacjent_service.Controllers;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/illnessCource")
public class CourseOfIllnessController {


    @GetMapping("/{patientId}")
    public void getPatientIllnessHistory(@PathVariable Long patientId) {

    }
}
