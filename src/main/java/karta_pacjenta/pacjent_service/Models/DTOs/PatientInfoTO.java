package karta_pacjenta.pacjent_service.Models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfoTO {
    @Id
    private long patientId;
    private long userId;
    private String userName;
    private String email;
    private HashSet<String> roles;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String personalIdentityNumber;
}
