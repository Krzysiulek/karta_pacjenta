package karta_pacjenta.pacjent_service.Models.DTOs;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ActuallyLoggedInUserTO {
    private long userId;
    private long doctorId;
    private long patientId;
    private String username;
}
