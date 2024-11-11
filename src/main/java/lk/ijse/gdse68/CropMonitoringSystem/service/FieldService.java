package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.CropDto;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.FieldDto;

import java.util.List;

public interface FieldService {
    void saveField(FieldDto fieldDto)  ;
    void updateField(String fieldId,FieldDto fieldDto);
    void deleteField(String fieldId);
    FieldResponse getSelectedField(String fieldId);
    List<FieldDto> getAllFields();
}
