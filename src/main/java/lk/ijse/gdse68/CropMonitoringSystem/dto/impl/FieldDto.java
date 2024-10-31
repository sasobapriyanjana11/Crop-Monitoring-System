package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import jakarta.persistence.*;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDto implements SuperDto {

    private String fieldCode;
    private String fieldName;
    private Double extentSize;
    private String fieldLocation;
    private List<String> cropCodes;
    private List<String> staffIds;
}
