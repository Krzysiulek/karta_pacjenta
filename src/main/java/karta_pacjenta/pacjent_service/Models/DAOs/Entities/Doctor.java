package karta_pacjenta.pacjent_service.Models.DAOs.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * TODO
 */

@Entity
@Data
@Table(name = "doctors")
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long doctorId;
    private long userId;

    private String firstName;
    private String lastName;

    private boolean active;
    private List<String> specializations;
    private List<Long> medicalFacilitiesParticipantIds;
}
