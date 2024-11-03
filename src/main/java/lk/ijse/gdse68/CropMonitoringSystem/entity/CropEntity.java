//package lk.ijse.gdse68.CropMonitoringSystem.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Table(name = "crop")
//@Entity
//public class CropEntity implements SuperEntity {
//    @Id
//    private String cropCode;
//    private String commonName;
//    private String scientificName;
//
//    @Column(columnDefinition = "LONGTEXT")
//    private String image;
//    private String category;
//    private String cropSeason;
//
//    @ManyToOne
//    @JoinColumn(name = "fieldCode")
//    private FieldEntity field;
//
//    @OneToMany(mappedBy = "crop",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<CropDetailsEntity> monitoringLogs;
//}
package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "crop")
@Entity
public class CropEntity implements SuperEntity {
    @Id
    private String cropCode;
    private String commonName;
    private String scientificName;

    @Column(columnDefinition = "LONGTEXT")
    private String image;
    private String category;
    private String cropSeason;

    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonitoringLogEntity> monitoringLogs;
}

