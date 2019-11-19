package karta_pacjenta.pacjent_service.Models.DAOs.Entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TODO
 */
@Entity
@Data
@Table(name = "MedicalFacilities")
public class MedicalFacility {
    private long id;
    private String name;
    private String address;
}
