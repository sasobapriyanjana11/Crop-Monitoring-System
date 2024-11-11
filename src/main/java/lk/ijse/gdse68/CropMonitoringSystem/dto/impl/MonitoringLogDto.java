package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoringLogDto implements MonitoringLogResponse,SuperDto {

    private String logCode;
    private Date logDate;
    private String observationDetails;
    private String observedImage;
    private String fieldCode;
}

