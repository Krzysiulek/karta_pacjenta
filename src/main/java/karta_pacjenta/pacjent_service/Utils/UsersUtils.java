package karta_pacjenta.pacjent_service.Utils;

import karta_pacjenta.pacjent_service.Services.MyAppUserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsersUtils {

    public static MyAppUserPrincipal getCurrentlyLoggedInUser() {
        return (MyAppUserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
