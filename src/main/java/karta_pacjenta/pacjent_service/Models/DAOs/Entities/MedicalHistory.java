package karta_pacjenta.pacjent_service.Models.DAOs.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "medicalHistory")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long patientId;
}
