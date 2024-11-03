package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoringLogDto implements MonitoringLogResponse,SuperDto {

    private String logCode;
    private String logDate;
    private String logDetails;
    private String observedImage;
    private List<String> fieldCodes; // List of fields associated with this log
    private List<String> cropCodes; // List of crops associated with this log
    private List<String> staffIds; // List of staff members involved in this log
}

