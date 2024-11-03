//package lk.ijse.gdse68.CropMonitoringSystem.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Table(name = "equipment")
//@Entity
//public class EquipmentEntity implements SuperEntity {
//    @Id
//    private String id;
//    private String name;
//    private String type;
//    private String status;
//    private String fieldCode;
//    private String staffId;
//
//
//}
package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lk.ijse.gdse68.CropMonitoringSystem.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "equipment")
@Entity
public class EquipmentEntity implements SuperEntity {
    @Id
    @Column(name = "equipment_id", nullable = false, updatable = false)
    private String equipmentId;
    private String name;

    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    private String status;

    @ManyToMany
    @JoinTable(
            name = "equipment_staff",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "staffId")
    )
    private List<StaffEntity> staff;

    @ManyToMany
    @JoinTable(
            name = "equipment_field",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "fieldCode")
    )
    private List<FieldEntity> fields;

    public void addFields(FieldEntity field) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        this.fields.add(field);
    }

    public void addStaff(StaffEntity staff) {
        if (this.staff == null) {
            this.staff = new ArrayList<>();
        }
        this.staff.add(staff);

    }
}

