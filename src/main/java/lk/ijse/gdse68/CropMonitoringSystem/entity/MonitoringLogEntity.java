package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "monitoring_log")
@Entity
public class MonitoringLogEntity implements SuperEntity {
    @Id
    private String logCode;

    @Temporal(TemporalType.DATE)
    private Date logDate;

    @Column(columnDefinition = "TEXT")
    private String logDetails;

    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "cropCode")
    private CropEntity crop;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity staff;
}
