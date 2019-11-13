package karta_pacjenta.pacjent_service.Interfaces;

import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patient, Long> {
}
