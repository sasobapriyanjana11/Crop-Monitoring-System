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
public class CropDto implements SuperDto {

    private String cropCode;
    private String commonName;
    private String scientificName;
    private String image;
    private String category;
    private String cropSeason;
    private String fieldCode;
    private List<CropDetailsDto>cropDetailsEntities;

}
