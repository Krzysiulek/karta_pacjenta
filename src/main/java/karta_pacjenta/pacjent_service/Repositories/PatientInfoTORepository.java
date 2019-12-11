package karta_pacjenta.pacjent_service.Repositories;

import karta_pacjenta.pacjent_service.Models.DTOs.PatientInfoTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientInfoTORepository extends JpaRepository<PatientInfoTO, Long> {

    @Query(
            value = "select distinct patients.patient_id, my_app_users.* from my_app_users " +
                    "join patients\n" +
                    "on my_app_users.user_id=patients.user_id",
            nativeQuery = true)
    List<PatientInfoTO> findAllPatients();
}
