package karta_pacjenta.pacjent_service.Models.DAOs.Entities;

import karta_pacjenta.pacjent_service.Utils.StringEncrypt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "diseases")
@AllArgsConstructor
@NoArgsConstructor
public class Diseases {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long diseaseId;

    @Convert(converter = StringEncrypt.class)
    private String name;

    @Convert(converter = StringEncrypt.class)
    private String category;

    @Lob
    private String description;
}
