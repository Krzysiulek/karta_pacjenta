package karta_pacjenta.pacjent_service.Models.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;

@Builder
@Data
public class PatientInfoTO {
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
