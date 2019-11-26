package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Models.DAOs.Acts.CourseOfIllness;
import karta_pacjenta.pacjent_service.Repositories.CourseOfIllnessRepository;
import karta_pacjenta.pacjent_service.Utils.UsersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/illnessCource")
public class CourseOfIllnessController {
    @Autowired
    private CourseOfIllnessRepository courseOfIllnessRepository;

    @GetMapping("/{patientId}")
    public List<CourseOfIllness> getPatientIllnessHistory(@PathVariable Long patientId) {
        return courseOfIllnessRepository
                .findAll()
                .stream()
                .filter(his -> patientId == his.getPatientId())
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public CourseOfIllness addPatientIllnessHistory(@RequestBody CourseOfIllness courseOfIllness) {
        courseOfIllness.setTimeStamp(String.valueOf(new Timestamp(System.currentTimeMillis())));
        long doctorUserId = UsersUtils.getCurrentlyLoggedInUser().getUser().getUserId(); // TODO: 2019-11-26 get doctorId - not like actually - userId
        courseOfIllness.setDoctorId(doctorUserId); // TODO: 2019-11-26 set doctor user not userID - to do this -> make doctor repository and add above

        return courseOfIllnessRepository.save(courseOfIllness);
    }
}
