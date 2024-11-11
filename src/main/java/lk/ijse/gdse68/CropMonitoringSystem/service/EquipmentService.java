package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.CropDto;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDto equipmentDto) ;
    void updateEquipment(String equipmentId,EquipmentDto equipmentDto);
    void deleteEquipment(String equipmentId);
    EquipmentResponse getSelectedEquipmentp(String equipmentId);
    List<EquipmentDto> getAllEquipments();
}
