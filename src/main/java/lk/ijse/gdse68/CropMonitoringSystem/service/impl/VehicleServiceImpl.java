package lk.ijse.gdse68.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.VehicleErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.VehicleResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.VehicleDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.VehicleDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.VehicleEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.VehicleNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.VehicleService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveVehicle(VehicleDto vehicleDto) {
        vehicleDto.setVehicleCode(AppUtil.createVehicleId());
        var VehicleEntity=mapping.convertToEntity(vehicleDto);
        var saveVehicle=vehicleDao.save(VehicleEntity);
        if(saveVehicle==null){
            throw new DataPersistFailedException("cannot add Vehicle");
        }
    }

    @Override
    public void updateVehicle(String vehicleId, VehicleDto vehicleDto) {
        Optional<VehicleEntity> tmpVehicleEntity= vehicleDao.findById(vehicleId);
        if(!tmpVehicleEntity.isPresent()){
            throw new VehicleNotFoundException("Vehicle not found");
        }else {
            tmpVehicleEntity.get().setCategory(vehicleDto.getCategory());
            tmpVehicleEntity.get().setFuelType(vehicleDto.getFuelType());
            tmpVehicleEntity.get().setLicensePlate(vehicleDto.getLicensePlate());
            tmpVehicleEntity.get().setRemarks(vehicleDto.getRemarks());
            tmpVehicleEntity.get().setStatus(vehicleDto.getStatus());
        }
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        Optional<VehicleEntity> tmpVehicleEntity = vehicleDao.findById(vehicleId);
        if(!tmpVehicleEntity.isPresent()){
            throw new VehicleNotFoundException("vehicle not found");
        }else{
            vehicleDao.deleteById(vehicleId);
        }
    }

    @Override
    public VehicleResponse getSelectedVehicle(String vehicleId) {
        if(vehicleDao.existsById(vehicleId)){
            return mapping.convertVehicleEntityToDTO(vehicleDao.getReferenceById(vehicleId));

        }else{
           return new VehicleErrorResponse(0,"vehicle not found");

        }
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return mapping.convertV_ToDTOList(vehicleDao.findAll());
    }
}
