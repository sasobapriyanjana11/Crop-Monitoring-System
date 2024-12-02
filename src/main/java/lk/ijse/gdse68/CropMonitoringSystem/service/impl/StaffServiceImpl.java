package lk.ijse.gdse68.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.*;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.StaffDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.MonitoringLogEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.StaffEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.VehicleEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.*;
import lk.ijse.gdse68.CropMonitoringSystem.service.StaffService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    private MonitoringLogDao monitoringLogDao;


    @Override
    public void saveStaff(StaffDto staffDto) {
//        staffDto.setStaffId(AppUtil.createStaffId());
        staffDto.setStaffId(staffDto.getStaffId());

        // Convert DTO to Entity
        StaffEntity staffEntity = mapping.convertToEntity(staffDto);

        // Fetch and set the EquipmentEntity if equipmentCode is provided
        if (staffDto.getEquipmentCode() != null) {
            EquipmentEntity equipmentEntity = equipmentDao.findById(staffDto.getEquipmentCode())
                    .orElseThrow(() -> new EquipmentNotFoundException("Equipment with code " + staffDto.getEquipmentCode() + " not found!"));
            staffEntity.setEquipment(equipmentEntity);
        }

        // Fetch and set the VehicleEntity if vehicleCode is provided
        if (staffDto.getVehicleCode() != null) {
            VehicleEntity vehicleEntity = vehicleDao.findById(staffDto.getVehicleCode())
                    .orElseThrow(() -> new DataPersistFailedException("Vehicle with code " + staffDto.getVehicleCode() + " not found!"));
            staffEntity.setVehicle(vehicleEntity);
        }

        // Save the StaffEntity
        StaffEntity savedEntity = staffDao.save(staffEntity);

        // Check if saving was successful
        if (savedEntity == null || savedEntity.getStaffId() == null) {
            throw new DataPersistFailedException("Error occurred while staff persistent!");
        }
    }


    @Override
    @Transactional
    public void updateStaff(String staffId, StaffDto staffDto) {
        // Fetch the staff entity by ID
        StaffEntity staffEntity = staffDao.findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException("Staff by this ID does not exist!"));

        // Update basic fields from DTO to Entity
        staffEntity.setFirstName(staffDto.getFirstName());
        staffEntity.setLastName(staffDto.getLastName());
        staffEntity.setDesignation(staffDto.getDesignation());
        staffEntity.setGender(staffDto.getGender());
        staffEntity.setJoinDate(staffDto.getJoinDate());
        staffEntity.setDOB(staffDto.getDOB());
        staffEntity.setBuildingNo(staffDto.getBuildingNo());
        staffEntity.setLane(staffDto.getLane());
        staffEntity.setCity(staffDto.getCity());
        staffEntity.setState(staffDto.getState());
        staffEntity.setPostalCode(staffDto.getPostalCode());
        staffEntity.setContactNo(staffDto.getContactNo());
        staffEntity.setEmail(staffDto.getEmail());

        // Check if equipmentCode is present and set it
        if (staffDto.getEquipmentCode() != null) {
            EquipmentEntity equipmentEntity = equipmentDao.findById(staffDto.getEquipmentCode())
                    .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found for equipmentCode: " + staffDto.getEquipmentCode()));
            staffEntity.setEquipment(equipmentEntity);
        }
        // Check if vehicleCode is present and set it
        if (staffDto.getVehicleCode() != null) {
            VehicleEntity vehicleEntity = vehicleDao.findById(staffDto.getVehicleCode())
                    .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found for vehicleCode: " + staffDto.getVehicleCode()));
            staffEntity.setVehicle(vehicleEntity);
        }
        // Check if fieldCode is present and set it
        if (staffDto.getLogCode() != null) {
            List<MonitoringLogEntity> logEntities =monitoringLogDao .findAllById(staffDto.getLogCode());
            staffEntity.setStaffLogEntities(logEntities);
        }
        // Save the updated staff entity
        staffDao.save(staffEntity);
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> tmpStaffEntity = staffDao.findById(staffId);
        if (!tmpStaffEntity.isPresent()) {
            throw new StaffNotFoundException("staff member not found");
        } else {
            staffDao.delete(tmpStaffEntity.get());
        }
    }

    @Override
    public StaffResponse getSelectedStaff(String staffId) {
        if (staffDao.existsById(staffId)) {
            return mapping.convertStaffEntityToDTO(staffDao.getReferenceById(staffId));
        } else {
            return new StaffErrorResponse(0, " staff member not found");
        }
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return mapping.convertS_ToDTOList(staffDao.findAll());
    }

}
