package karta_pacjenta.pacjent_service.Models.DAOs.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "patients")
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long patientId;

    private long userId;
    private String firstName;
    private String lastName;
    private String address;
    private int phoneNumber;
    private int personalIdentityNumber;
}
