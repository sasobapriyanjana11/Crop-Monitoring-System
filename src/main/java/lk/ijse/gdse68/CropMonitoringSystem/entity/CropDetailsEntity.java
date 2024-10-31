package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "crop_details")
@Entity
public class CropDetailsEntity implements SuperEntity {
    @Id
    private String logCode;
    private LocalDate date;
    private String observations;

    @Column(columnDefinition = "LONGTEXT")
    private String observationImage;

    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "cropCode")
    private CropEntity crop;

    @ManyToOne
    @JoinColumn(name = "id")
    private StaffEntity staff;

}
