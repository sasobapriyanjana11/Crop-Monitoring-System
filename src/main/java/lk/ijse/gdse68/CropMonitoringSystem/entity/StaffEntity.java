package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "staff")
@Entity
public class StaffEntity implements SuperEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private LocalDate joinDate;
    private LocalDate DOB;
    private String buildingNo;
    private String lane;
    private String city;
    private String state;
    private String postalCode;
    private String contactNo;
    private String email;

    @ManyToMany(mappedBy = "staff",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FieldEntity> fields;

    @OneToOne
    @JoinColumn(name = "vehicleCode")
    private VehicleEntity allocatedVehicle;

}
