package karta_pacjenta.pacjent_service.Repositories;


import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByUserId(Long userId);
}
