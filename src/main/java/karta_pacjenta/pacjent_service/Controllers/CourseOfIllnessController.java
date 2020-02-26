package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Models.DAOs.Acts.CourseOfIllness;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Diseases;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Doctor;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import karta_pacjenta.pacjent_service.Models.DTOs.FullMedicalHistoryTO;
import karta_pacjenta.pacjent_service.Models.DTOs.MedicalHistoryTO;
import karta_pacjenta.pacjent_service.Repositories.*;
import karta_pacjenta.pacjent_service.Utils.UsersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/illnessCource")
public class CourseOfIllnessController {
    @Autowired
    private CourseOfIllnessRepository courseOfIllnessRepository;

    @Autowired
    private DiseasesRepository diseasesRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MyAppUsersRepository myAppUsersRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping("/{patientId}")
    public List<MedicalHistoryTO> getPatientIllnessHistory(@PathVariable Long patientId) {
        return courseOfIllnessRepository
                .findAll()
                .stream()
                .filter(his -> patientId == his.getPatientId())
                .map(this::getMedicalHistory)
                .collect(Collectors.toList());
    }

    @GetMapping("/{patientId}/anonymous")
    public FullMedicalHistoryTO getPatientIllnessHistoryAnonymous(@PathVariable Long patientId) {
        ArrayList<MedicalHistoryTO> medicalHistoryTOS = (ArrayList<MedicalHistoryTO>) courseOfIllnessRepository
                .findAll()
                .stream()
                .filter(his -> patientId == his.getPatientId())
                .map(this::getMedicalHistoryAnonymous)
                .collect(Collectors.toList());

        return FullMedicalHistoryTO.builder()
                .medicalHistoryTOS(medicalHistoryTOS)
                .build();
    }

    @GetMapping("/{patientId}/full")
    public FullMedicalHistoryTO getPatientIllnessHistoryFull(@PathVariable Long patientId) {
        Patient patient;
        MyServiceUser patientServiceUser = new MyServiceUser();

        ArrayList<MedicalHistoryTO> medicalHistoryTOS = (ArrayList<MedicalHistoryTO>) courseOfIllnessRepository
                .findAll()
                .stream()
                .filter(his -> patientId == his.getPatientId())
                .map(this::getMedicalHistory)
                .collect(Collectors.toList());

        if (patientsRepository.findById(patientId).isPresent()) {
            patient = patientsRepository.findById(patientId).get();
            patientServiceUser = myAppUsersRepository.findByUserId(patient.getUserId());
        }

        return FullMedicalHistoryTO.builder()
                .patientId(patientId)
                .patientUserId(patientServiceUser.getUserId())

                .patientUserName(patientServiceUser.getUserName())
                .patientEmail(patientServiceUser.getEmail())

                .patientFirstName(patientServiceUser.getFirstName())
                .patientLastName(patientServiceUser.getLastName())
                .patientAddress(patientServiceUser.getAddress())
                .patientPhoneNumber(patientServiceUser.getPhoneNumber())
                .patientPersonalIdentityNumber(patientServiceUser.getPersonalIdentityNumber())
                .medicalHistoryTOS(medicalHistoryTOS)
                .build();
    }

    @GetMapping("/{patientId}/{courseOfIllnessId}")
    public Optional<MedicalHistoryTO> getPatientDisease(@PathVariable Long patientId, @PathVariable Long courseOfIllnessId) {
        return courseOfIllnessRepository
                .findAll()
                .stream()
                .filter(his -> patientId == his.getPatientId())
                .filter(his -> courseOfIllnessId == his.getCourseOfIllnessId())
                .findFirst()
                .map(this::getMedicalHistory);
    }

    @PostMapping("")
    public CourseOfIllness addPatientIllnessHistory(@RequestBody CourseOfIllness courseOfIllness) {
        courseOfIllness.setTimeStamp(String.valueOf(new Timestamp(System.currentTimeMillis())));
        long doctorUserId = UsersUtils.getCurrentlyLoggedInUser().getUser().getUserId(); // TODO: 2019-11-26 get doctorId - not like actually - userId
        courseOfIllness.setDoctorId(doctorUserId); // TODO: 2019-11-26 set doctor user not userID - to do this -> make doctor repository and add above

        return courseOfIllnessRepository.save(courseOfIllness);
    }

    private MedicalHistoryTO getMedicalHistory(CourseOfIllness courseOfIllness) {
        Diseases disease = new Diseases();
        Doctor doctor = new Doctor();

        if (diseasesRepository.findById(courseOfIllness.getDiseaseId()).isPresent())
            disease = diseasesRepository.findById(courseOfIllness.getDiseaseId()).get();

        if (doctorRepository.findByUserId(courseOfIllness.getDoctorId()).isPresent())
            doctor = doctorRepository.findByUserId(courseOfIllness.getDoctorId()).get();

        MyServiceUser doctorServiceUser = myAppUsersRepository.findByUserId(courseOfIllness.getDoctorId());

        return MedicalHistoryTO.builder()
                .courseOfIllnessId(courseOfIllness.getCourseOfIllnessId())
                .visitCategory(courseOfIllness.getVisitCategory())

                .diseaseName(disease.getName())
                .diseaseCategory(disease.getCategory())
                .diseaseDescription(disease.getDescription())

                .patientId(courseOfIllness.getPatientId())

                .doctorId(doctor.getDoctorId())
                .doctorFirstName(doctorServiceUser.getFirstName())
                .doctorLastName(doctorServiceUser.getLastName())
                .doctorPhoneNumber(doctorServiceUser.getPhoneNumber())
                .doctorEmail(doctorServiceUser.getEmail())

                .visitTimeStamp(courseOfIllness.getTimeStamp())

                .patientDescription(courseOfIllness.getPatientDescription())
                .doctorDescription(courseOfIllness.getDoctorDescription())
                .doctorPrescription(courseOfIllness.getPrescription())
                .build();
    }

    private MedicalHistoryTO getMedicalHistoryAnonymous(CourseOfIllness courseOfIllness) {
        Diseases disease = getDiseaseIfExists(courseOfIllness);
        Doctor doctor = getDoctorIfExists(courseOfIllness);

        MyServiceUser doctorServiceUser = myAppUsersRepository.findByUserId(courseOfIllness.getDoctorId());

        return MedicalHistoryTO.builder()
                .courseOfIllnessId(courseOfIllness.getCourseOfIllnessId())
                .visitCategory(courseOfIllness.getVisitCategory())

                .diseaseName(disease.getName())
                .diseaseCategory(disease.getCategory())
                .diseaseDescription(disease.getDescription())


                .doctorId(doctor.getDoctorId())
                .doctorFirstName(doctorServiceUser.getFirstName())
                .doctorLastName(doctorServiceUser.getLastName())
                .doctorPhoneNumber(doctorServiceUser.getPhoneNumber())
                .doctorEmail(doctorServiceUser.getEmail())

                .visitTimeStamp(courseOfIllness.getTimeStamp())

                .patientDescription(courseOfIllness.getPatientDescription())
                .doctorDescription(courseOfIllness.getDoctorDescription())
                .doctorPrescription(courseOfIllness.getPrescription())
                .build();
    }

    private Doctor getDoctorIfExists(CourseOfIllness courseOfIllness) {
        Doctor doctor = new Doctor();

        if (doctorRepository.findByUserId(courseOfIllness.getDoctorId()).isPresent())
            doctor = doctorRepository.findByUserId(courseOfIllness.getDoctorId()).get();

        return doctor;
    }

    private Diseases getDiseaseIfExists(CourseOfIllness courseOfIllness) {
        Diseases disease = new Diseases();

        if (diseasesRepository.findById(courseOfIllness.getDiseaseId()).isPresent())
            disease = diseasesRepository.findById(courseOfIllness.getDiseaseId()).get();

        return disease;
    }

}
