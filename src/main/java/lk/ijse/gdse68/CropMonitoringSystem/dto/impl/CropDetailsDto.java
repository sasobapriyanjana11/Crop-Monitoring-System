package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropDetailsDto implements SuperDto {
    private String logCode;
    private LocalDate date;
    private String observations;
    private String observationImage;
    private String fieldCode ;
    private String cropCode;
    private String staffId;

}
