package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.FieldDto;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.StaffDto;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDto staffDto)  ;
    void updateStaff(String staffId,StaffDto staffDto);
    void deleteStaff(String staffId);
    StaffResponse getSelectedStaff(String staffId);
    List<StaffDto> getAllStaff();
}
