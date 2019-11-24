package karta_pacjenta.pacjent_service.Interfaces;

import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Diseases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseasesRepository extends JpaRepository<Diseases, Long> {
}
