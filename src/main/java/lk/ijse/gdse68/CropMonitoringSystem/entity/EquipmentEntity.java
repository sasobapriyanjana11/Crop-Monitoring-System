package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "equipment")
@Entity
public class EquipmentEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String type;
    private String status;
    private String fieldCode;
    private String staffId;


}
