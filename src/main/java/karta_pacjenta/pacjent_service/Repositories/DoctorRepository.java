package karta_pacjenta.pacjent_service.Repositories;


import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
