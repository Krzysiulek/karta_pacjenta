package karta_pacjenta.pacjent_service.Models.DTOs;

import karta_pacjenta.pacjent_service.Utils.StringEncrypt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
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
    @Convert(converter = StringEncrypt.class)
    private String email;
    private HashSet<String> roles;

    @Convert(converter = StringEncrypt.class)
    private String firstName;
    @Convert(converter = StringEncrypt.class)
    private String lastName;
    @Convert(converter = StringEncrypt.class)
    private String address;
    @Convert(converter = StringEncrypt.class)
    private String phoneNumber;
    @Convert(converter = StringEncrypt.class)
    private String personalIdentityNumber;
}
