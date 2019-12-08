package karta_pacjenta.pacjent_service.Utils;

import org.jasypt.util.text.StrongTextEncryptor;

import javax.persistence.AttributeConverter;

public class StringEncrypt implements AttributeConverter<String, String> {
    private final String password = "helloMotoXd123";
    private StrongTextEncryptor textEncryptor;


    @Override
    public String convertToDatabaseColumn(String s) {
        textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.decrypt(s);
    }
}
