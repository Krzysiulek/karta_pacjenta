package karta_pacjenta.pacjent_service.Models.DAOs.Acts;

import karta_pacjenta.pacjent_service.Utils.StringEncrypt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "courseOfIllness")
@AllArgsConstructor
@NoArgsConstructor
public class CourseOfIllness {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long courseOfIllnessId;

    @Convert(converter = StringEncrypt.class)
    private String visitCategory;
    private long diseaseId;
    private long patientId;
    private long doctorId;

    @Convert(converter = StringEncrypt.class)
    private String timeStamp;

    @Lob
    @Convert(converter = StringEncrypt.class)
    private String patientDescription;

    @Lob
    @Convert(converter = StringEncrypt.class)
    private String doctorDescription;

    @Lob
    @Convert(converter = StringEncrypt.class)
    private String prescription;
    // TODO: 2019-11-25 Add photo
}
