//package lk.ijse.gdse68.CropMonitoringSystem.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import java.time.LocalDate;
//import java.util.List;
//@Entity
//@Table(name = "staff")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class StaffEntity implements SuperEntity {
//
//    @Id
//    private String id;
//
//    private String firstName;
//    private String lastName;
//    private String designation;
//    private LocalDate joinDate;
//    private LocalDate DOB;
//    private String buildingNo;
//    private String lane;
//    private String city;
//    private String state;
//    private String postalCode;
//    private String contactNo;
//    private String email;
//
//    @ManyToMany(mappedBy = "allocatedStaff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<FieldEntity> fields;
//
//    @OneToOne
//    @JoinColumn(name = "vehicleCode")
//    private VehicleEntity allocatedVehicle;
//}
package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Gender;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "staff")
@Entity
public class StaffEntity implements StaffResponse,SuperEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String designation;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date joinedDate;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private String buildingNo;
    private String lane;
    private String city;
    private String state;
    private String postalCode;
    private String contactNo;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "staff")
    private List<FieldEntity> fields;

    @ManyToMany(mappedBy = "staff")
    private List<VehicleEntity> vehicles;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonitoringLogEntity> monitoringLogs;

    @ManyToMany(mappedBy = "staff")
    private List<EquipmentEntity> equipment;
}
