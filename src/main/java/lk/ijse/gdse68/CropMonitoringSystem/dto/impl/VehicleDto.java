package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.VehicleResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto implements SuperDto, VehicleResponse {

    private String vehicleCode;
    private String licensePlate;
    private String category;
    private String fuelType;
    private String status;
    private String remarks;
}

