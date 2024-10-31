package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import jakarta.persistence.*;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDto implements SuperDto {

    private String id;
    private String name;
    private String type;
    private String status;
    private String fieldCode;
    private String staffId;

}
