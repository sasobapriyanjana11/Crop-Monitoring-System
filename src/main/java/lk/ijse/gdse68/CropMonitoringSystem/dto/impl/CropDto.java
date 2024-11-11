package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropDto implements SuperDto, CropResponse {

    private String cropCode;
    private String commonName;
    private String scientificName;
    private String image;
    private String category;
    private String cropSeason;
    private String fieldCode;
}

