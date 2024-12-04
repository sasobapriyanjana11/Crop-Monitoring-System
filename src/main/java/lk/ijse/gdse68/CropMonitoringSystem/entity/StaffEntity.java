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
@Entity
@Table(name = "Staff")
public class StaffEntity implements SuperEntity{
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private LocalDate joinDate;
    private LocalDate dob;
    private String address;
    private String postalCode;
    private String contactNo;
    private String email;

    @ManyToMany
    @JoinTable(name = "staff_log",
            joinColumns = @JoinColumn(name = "staffId"),inverseJoinColumns = @JoinColumn(name = "logCode"))
    private List<MonitoringLogEntity> staffLogEntities;

    @ManyToMany(mappedBy = "assignedStaff")
    private List<FieldEntity> fieldEntities;


    @ManyToOne
    @JoinColumn(name ="equipmentCode")
    private EquipmentEntity equipment;

    @ManyToOne
    @JoinColumn(name = "vehicleCode")
    private VehicleEntity vehicle;


}