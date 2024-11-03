//package lk.ijse.gdse68.CropMonitoringSystem.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//@Entity
//@Table(name = "vehicle")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class VehicleEntity implements SuperEntity {
//
//    @Id
//    private String vehicleCode;
//
//    private String licensePlate;
//    private String category;
//    private String fuelType;
//    private String status;
//    private String remarks;
//
//    @OneToOne(mappedBy = "allocatedVehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private StaffEntity assignedDriver;
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
@Table(name = "vehicle")
@Entity
public class VehicleEntity implements SuperEntity {
    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;

    @ManyToMany
    @JoinTable(
            name = "vehicle_staff",
            joinColumns = @JoinColumn(name = "vehicleCode"),
            inverseJoinColumns = @JoinColumn(name = "staffId")
    )
    private List<StaffEntity> staff;

    @Column(columnDefinition = "TEXT")
    private String remarks;
}

