package karta_pacjenta.pacjent_service.Models.DAOs.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * TODO
 */

@Entity
@Data
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long doctorId;
    private long userId;

    private String firstName;
    private String lastName;

    private boolean active;
    private ArrayList<String> specializations;
    private ArrayList<Long> medicalFacilitiesParticipantIds;
}
