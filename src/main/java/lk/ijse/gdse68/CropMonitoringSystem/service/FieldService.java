package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.FieldDto;
import java.util.List;

public interface FieldService {
    String saveField(FieldDto fieldDto);
    void updateField(String fieldId, FieldDto fieldDto);
    void deleteField(String fieldId);
    FieldResponse getSelectedField(String fieldId);
    List<FieldDto> getAllFields();
}
