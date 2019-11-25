package karta_pacjenta.pacjent_service.Models.DAOs.Acts;

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

    private String visitCategory;
    private long diseaseId;
    private long patientId;
    private long doctorId;
    private String timeStamp;

    @Lob
    private String patientDescription;

    @Lob
    private String doctorDescription;

    @Lob
    private String prescription;
    // TODO: 2019-11-25 Add photo
}
