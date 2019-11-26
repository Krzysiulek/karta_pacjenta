package karta_pacjenta.pacjent_service.Controllers;

import karta_pacjenta.pacjent_service.Models.DTOs.ActuallyLoggedInUserTO;
import karta_pacjenta.pacjent_service.Repositories.DoctorRepository;
import karta_pacjenta.pacjent_service.Repositories.PatientsRepository;
import karta_pacjenta.pacjent_service.Services.MyAppUserPrincipal;
import karta_pacjenta.pacjent_service.Utils.UsersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/currentUser")
public class CurrentUserController {
    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping()
    public ActuallyLoggedInUserTO getCurrentUserInfo() {
        MyAppUserPrincipal currentlyLoggedInUser = UsersUtils.getCurrentlyLoggedInUser();
        long currentlyLoggedInUserId = currentlyLoggedInUser.getUser().getUserId();
        Long doctorId = null;
        Long patientId = null;

        if (doctorRepository.findByUserId(currentlyLoggedInUserId).isPresent())
            doctorId = doctorRepository.findByUserId(currentlyLoggedInUserId).get().getDoctorId();

        if (patientsRepository.findByUserId(currentlyLoggedInUserId).isPresent())
            patientId = patientsRepository.findByUserId(currentlyLoggedInUserId).get().getPatientId();

        return ActuallyLoggedInUserTO.builder()
                .doctorId(doctorId)
                .patientId(patientId)
                .userId(currentlyLoggedInUserId)
                .build();
    }
}
