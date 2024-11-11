package lk.ijse.gdse68.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.CropDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.FieldDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.StaffDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.FieldDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.StaffEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
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
    private CropDao cropDao;  // Assume you have a CropDao interface

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDto fieldDto) {
        fieldDto.setFieldCode(AppUtil.createFieldId());
        FieldEntity fieldEntity = mapping.convertToEntity(fieldDto);
        FieldEntity savedField = fieldDao.save(fieldEntity);
        if (savedField == null) {
            throw new DataPersistFailedException("Cannot add field");
        }
    }

    @Override
    public void updateField(String fieldId, FieldDto fieldDto) {
        Optional<FieldEntity> optionalFieldEntity = fieldDao.findById(fieldId);
        if (!optionalFieldEntity.isPresent()) {
            throw new FieldNotFoundException("field not found");
        }

        FieldEntity fieldEntity = optionalFieldEntity.get();

        fieldEntity.setFieldName(fieldDto.getFieldName());
        fieldEntity.setFieldLocation(fieldDto.getFieldLocation());
        fieldEntity.setExtentSize(fieldDto.getExtentSize());
        fieldEntity.setImage1(fieldDto.getImage1());
        fieldEntity.setImage2(fieldDto.getImage2());

        // Save the updated entity back to the database
        fieldDao.save(fieldEntity);

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
