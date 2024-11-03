//package lk.ijse.gdse68.CropMonitoringSystem.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//@Entity
//@Table(name = "field")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class FieldEntity implements SuperEntity {
//
//    @Id
//    private String fieldCode;
//
//    private String fieldName;
//    private String location;
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "staff_field",
//            joinColumns = @JoinColumn(name = "field_code"),
//            inverseJoinColumns = @JoinColumn(name = "staff_id")
//    )
//    private List<StaffEntity> allocatedStaff;
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
@Table(name = "field")
@Entity
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;

    @Column(columnDefinition = "POINT")
    private String fieldLocation;  // Assuming GPS point is stored as String

    private Double extentSize;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CropEntity> crops;

    @ManyToMany
    @JoinTable(
            name = "field_staff",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "staffId")
    )
    private List<StaffEntity> staff;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonitoringLogEntity> monitoringLogs;

    @ManyToMany
    @JoinTable(
            name = "field_equipment",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<EquipmentEntity> equipment;

    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;

    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;


}
