package karta_pacjenta.pacjent_service.Models.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class FullMedicalHistoryTO {
    private long patientId;
    private long patientUserId;

    private String patientUserName;

    private String patientFirstName;
    private String patientLastName;
    private String patientAddress;

    private String patientPhoneNumber;
    private String patientEmail;

    private String patientPersonalIdentityNumber;

    private ArrayList<MedicalHistoryTO> medicalHistoryTOS;
}
