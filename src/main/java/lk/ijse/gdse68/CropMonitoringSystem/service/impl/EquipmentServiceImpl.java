package lk.ijse.gdse68.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.EquipmentErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.EquipmentDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.FieldDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.StaffDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.EquipmentDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.CropMonitoringSystem.enums.EquipmentType;
import lk.ijse.gdse68.CropMonitoringSystem.exception.*;
import lk.ijse.gdse68.CropMonitoringSystem.service.EquipmentService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDto equipmentDto) {
//     equipmentDto.setEquipmentCode(AppUtil.createEquipmentId());
        equipmentDto.setEquipmentCode(equipmentDto.getEquipmentCode());
        var equipmentEntity = mapping.convertToEntity(equipmentDto);
        var saveEquipment= equipmentDao.save(equipmentEntity);
        if(saveEquipment==null){
            throw new DataPersistFailedException("cannot save equipment");
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDto equipmentDto) {
        Optional<EquipmentEntity> tmpEquipEntity = equipmentDao.findById(equipmentId);

        if (!tmpEquipEntity.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        } else {
            EquipmentEntity equipment = tmpEquipEntity.get();

            // Update simple fields
            equipment.setEquipmentName(equipmentDto.getEquipmentName());
            equipment.setStatus(equipmentDto.getStatus());
            equipment.setType(EquipmentType.valueOf(String.valueOf(equipmentDto.getType())));

            equipmentDao.save(equipment);
        }

    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> tmpEquipEntity = equipmentDao.findById(equipmentId);
        if(!tmpEquipEntity.isPresent()){
            throw new EquipmentNotFoundException("Equipment not found");
        }else{
            equipmentDao.deleteById(equipmentId);
        }
    }

    @Override
    public EquipmentResponse getSelectedEquipmentp(String equipmentId) {
        if(equipmentDao.existsById(equipmentId)){
            return mapping.convertToDTO(equipmentDao.getReferenceById(equipmentId));
        }else{
            return new EquipmentErrorResponse(0,"equipment not found");
        }
    }

    @Override
    public List<EquipmentDto> getAllEquipments() {
        return mapping.convertE_ToDTOList(equipmentDao.findAll());
    }
}
