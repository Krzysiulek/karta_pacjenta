package karta_pacjenta.pacjent_service.Repositories;

import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patient, Long> {
}
