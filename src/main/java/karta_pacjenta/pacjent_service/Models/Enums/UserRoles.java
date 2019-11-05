package karta_pacjenta.pacjent_service.Models.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRoles {
    PATIENT("ROLE_PATIENT"),
    ADMIN("ROLE_ADMIN"),
    DOCTOR("ROLE_DOCTOR");

    private String role;
}
