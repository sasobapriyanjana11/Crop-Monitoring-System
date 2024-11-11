package lk.ijse.gdse68.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.EquipmentDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.StaffDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.VehicleDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.StaffDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.StaffEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.VehicleEntity;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Gender;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.StaffNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.VehicleNotFoundException;
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

//    @Override
//    public void saveStaff(StaffDto staffDto) {
//        staffDto.setStaffId(AppUtil.createStaffId());
//        var StaffEntity=mapping.convertToEntity(staffDto);
//        var saveStaff=staffDao.save(StaffEntity);
//        if(saveStaff==null){
//            throw new DataPersistFailedException("cannot add staff");
//        }
//    }
@Override
public void saveStaff(StaffDto staffDto) {
    // Set a unique staff ID for the new staff member
    staffDto.setStaffId(AppUtil.createStaffId());

    // Convert StaffDto to StaffEntity
    StaffEntity staffEntity = mapping.convertToEntity(staffDto);

    // Check if equipmentCode exists and fetch the corresponding EquipmentEntity
    if (staffDto.getEquipmentCode() != null) {
        EquipmentEntity equipmentEntity = equipmentDao.findByEquipmentCode(staffDto.getEquipmentCode());
        staffEntity.setEquipment(equipmentEntity); // Set the equipment entity in StaffEntity
    }

    // Check if vehicleCode exists and fetch the corresponding VehicleEntity
    if (staffDto.getVehicleCode() != null) {
        VehicleEntity vehicleEntity = vehicleDao.findByVehicleCode(staffDto.getVehicleCode());
        staffEntity.setVehicle(vehicleEntity); // Set the vehicle entity in StaffEntity
    }

    // Save the staff entity
    StaffEntity savedStaff = staffDao.save(staffEntity);
    if (savedStaff == null) {
        throw new DataPersistFailedException("Cannot add staff");
    }
}


    @Override
    @Transactional
    public void updateStaff(String staffId, StaffDto staffDto) {
        Optional<StaffEntity> tmpStaff = staffDao.findById(staffId);
        if (tmpStaff.isEmpty()) {
            throw new StaffNotFoundException("Staff not found");
        } else {
            StaffEntity staff = tmpStaff.get();

            // Update basic information
            staff.setFirstName(staffDto.getFirstName());
            staff.setLastName(staffDto.getLastName());
            staff.setDesignation(staffDto.getDesignation());

            // Update gender enum directly
            staff.setGender(String.valueOf(Gender.valueOf(staffDto.getGender())));

            // Update other fields
            staff.setJoinDate(staffDto.getJoinDate());
            staff.setDOB(staffDto.getDOB());
            staff.setBuildingNo(staffDto.getBuildingNo());
            staff.setLane(staffDto.getLane());
            staff.setCity(staffDto.getCity());
            staff.setState(staffDto.getState());
            staff.setPostalCode(staffDto.getPostalCode());
            staff.setContactNo(staffDto.getContactNo());
            staff.setEmail(staffDto.getEmail());

            // Update equipment and vehicle associations if applicable
            if (staffDto.getEquipmentCode() != null) {
                EquipmentEntity equipment = equipmentDao.findById(staffDto.getEquipmentCode())
                        .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found"));
                staff.setEquipment(equipment);
            }

            if (staffDto.getVehicleCode() != null) {
                VehicleEntity vehicle = vehicleDao.findById(staffDto.getVehicleCode())
                        .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
                staff.setVehicle(vehicle);
            }

            // Save updated entity
            staffDao.save(staff);
        }



}

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> tmpStaffEntity = staffDao.findById(staffId);
        if(!tmpStaffEntity.isPresent()){
            throw new StaffNotFoundException("staff member not found");
        }else{
            staffDao.delete(tmpStaffEntity.get());
        }
    }

    @Override
    public StaffResponse getSelectedStaff(String staffId) {
        if(staffDao.existsById(staffId)){
            return mapping.convertStaffEntityToDTO(staffDao.getReferenceById(staffId));
        }else{
            return new StaffErrorResponse(0," staff member not found");
        }
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return mapping.convertS_ToDTOList(staffDao.findAll());
    }
}
