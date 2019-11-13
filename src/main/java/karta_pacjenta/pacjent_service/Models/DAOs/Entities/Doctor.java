package karta_pacjenta.pacjent_service.Models.DAOs.Entities;

import java.util.List;

public class Doctor {
    private long doctorId;
    private long userId;

    private String firstName;
    private String lastName;

    private boolean active;
    private List<String> specializations;
    private List<Long> medicalFacilitiesParticipantIds;
}
