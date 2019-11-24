package karta_pacjenta.pacjent_service.Models.DAOs.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * TODO
 */
@Entity
@Data
@Table(name = "medicalFacilities")
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String address;
}
