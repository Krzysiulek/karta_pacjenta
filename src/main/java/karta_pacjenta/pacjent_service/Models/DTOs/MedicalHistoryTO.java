package karta_pacjenta.pacjent_service.Models.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicalHistoryTO {
    private long courseOfIllnessId;
    private String visitCategory;

    private String diseaseName;
    private String diseaseCategory;
    private String diseaseDescription;

    private long patientId;

    private long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorPhoneNumber;
    private String doctorEmail;

    private String visitTimeStamp;

    private String patientDescription;
    private String doctorDescription;
    private String doctorPrescription;
}
