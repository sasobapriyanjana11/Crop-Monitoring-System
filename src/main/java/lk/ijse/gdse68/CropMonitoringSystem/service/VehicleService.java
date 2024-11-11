package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.VehicleResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.StaffDto;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.VehicleDto;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDto vehicleDto)   ;
    void updateVehicle(String vehicleId,VehicleDto vehicleDto);
    void deleteVehicle(String vehicleId);
    VehicleResponse getSelectedVehicle(String vehicleId);
    List<VehicleDto> getAllVehicles();
}
