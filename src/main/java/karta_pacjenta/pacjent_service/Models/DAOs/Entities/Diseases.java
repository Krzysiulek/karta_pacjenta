package karta_pacjenta.pacjent_service.Models.DAOs.Entities;

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

    private String name;
}
