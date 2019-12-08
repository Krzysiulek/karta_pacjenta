package karta_pacjenta.pacjent_service.Utils;

import javax.persistence.AttributeConverter;

public class StringEncrypt implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String s) {
        return s + "dupa";
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return s.replace("dupa", "");
    }
}
