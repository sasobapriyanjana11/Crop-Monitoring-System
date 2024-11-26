package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDto implements SuperDto, FieldResponse {

    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private double extentSize;
    private String image1;
    private String image2;
    private String equipmentCode;
    private List<String>staffId;
}

