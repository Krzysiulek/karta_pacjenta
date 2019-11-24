package karta_pacjenta.pacjent_service.Models.DAOs.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "medicalHistory")
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long patientId;
}
