package karta_pacjenta.pacjent_service.Utils;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;

@Data
@ToString
public class RandomPerson {
    private String name;
    private String surname;
    private String gender;
    private String region;
    private int age;
    private String title;
    private String phone;
    private HashMap<String, String> birthday;
    private String email;
    private String password;
}
