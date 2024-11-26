package lk.ijse.gdse68.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.CropDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.EquipmentDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.FieldDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.StaffDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.FieldDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.StaffEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.StaffNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private CropDao cropDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private Mapping mapping;

    @Override
    public String saveField(FieldDto fieldDto) {
        fieldDto.setFieldCode(AppUtil.createFieldId());
        FieldEntity save = fieldDao.save(mapping.convertToEntity(fieldDto));

        // Check if saving failed or if the field code is null
        if (save == null || save.getFieldCode() == null) {
            return "Couldn't save Field details!";
        }

        // Return a success message if saving was successful
        return "Field added successfully with code: " + save.getFieldCode();
    }

    @Override
    public void updateField(String fieldId, FieldDto fieldDto) {
        Optional<FieldEntity> byId = fieldDao.findById(fieldId);
        if (!byId.isPresent()){
            throw new FieldNotFoundException("Couldn't find the field!");
        } else {
            FieldEntity field = byId.get();
            // Set other fields
            field.setFieldName(fieldDto.getFieldName());
            field.setExtentSize(fieldDto.getExtentSize());
            field.setFieldLocation(fieldDto.getFieldLocation());
            field.setImage1(fieldDto.getImage1());
            field.setImage2(fieldDto.getImage2());

            // Fetch the FieldEntity based on fieldCode
            Optional<EquipmentEntity>equipmentEntity = equipmentDao.findById(fieldDto.getEquipmentCode());
            if (!equipmentEntity.isPresent()) {
                throw new FieldNotFoundException("Field not found for EquipmentCode: " + fieldDto.getEquipmentCode());
            }
            field.setEquipment(equipmentEntity.get());

            if (fieldDto.getStaffId() != null) {
                List<StaffEntity> staffEntities =staffDao .findAllById(fieldDto.getStaffId());
                field.setAssignedStaff(staffEntities);
            }
            // Save the updated cropEntity back to the database
            fieldDao.save(field);
        }

    }
    private List<CropEntity> convertCropCodesToEntities(List<String> cropCodes) {
        List<CropEntity> crops = new ArrayList<>();

        for (String cropCode : cropCodes) {
            Optional<CropEntity> cropEntityOpt = cropDao.findById(cropCode);
            if (cropEntityOpt.isPresent()) {
                crops.add(cropEntityOpt.get());
            } else {
                throw new CropNotFoundException("Crop with code " + cropCode + " not found");
            }
        }

        return crops;
    }

private List<StaffEntity> convertStaffIdsToEntities(List<String> staffIds) {
        List<StaffEntity> staffMembers = new ArrayList<>();

        for (String staffId : staffIds) {
            Optional<StaffEntity> staffEntityOpt = staffDao.findById(staffId);
            if (staffEntityOpt.isPresent()) {
                staffMembers.add(staffEntityOpt.get());
            } else {
                throw new StaffNotFoundException("Staff member with ID " + staffId + " not found");
            }
        }

        return staffMembers;
    }

    @Override
    public void deleteField(String fieldId) {
        Optional<FieldEntity> tmpFieldEntity = fieldDao.findById(fieldId);
        if(!tmpFieldEntity.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else{
            fieldDao.deleteById(fieldId);
        }
    }

    @Override
    public FieldResponse getSelectedField(String fieldId) {
        if(fieldDao.existsById(fieldId)){
            return mapping.convertFieldEntityToDTO(fieldDao.getReferenceById(fieldId));
        }else{
            return new FieldErrorResponse(0,"field not found");
        }
    }

    @Override
    public List<FieldDto> getAllFields() {
        return mapping.convertF_EntityListToDTOList(fieldDao.findAll());
    }
}
