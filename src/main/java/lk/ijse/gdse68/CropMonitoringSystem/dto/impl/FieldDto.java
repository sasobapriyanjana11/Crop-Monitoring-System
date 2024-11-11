package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDto implements SuperDto, FieldResponse {

    private String fieldCode;
    private String fieldName;
    private Double extentSize;
    private String fieldLocation;
    private String image1;
    private String image2;
    private String equipmentCode;
}

