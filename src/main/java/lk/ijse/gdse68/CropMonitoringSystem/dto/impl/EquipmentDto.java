package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lk.ijse.gdse68.CropMonitoringSystem.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDto implements SuperDto, EquipmentResponse {

   private String equipmentCode;
    private String equipmentName;
    private String status;
    private EquipmentType type;
}

