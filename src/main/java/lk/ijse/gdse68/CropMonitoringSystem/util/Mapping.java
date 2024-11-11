package lk.ijse.gdse68.CropMonitoringSystem.util;

import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.*;
import lk.ijse.gdse68.CropMonitoringSystem.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //crop
    public CropDto convertToDTO(CropEntity entity) {
        return modelMapper.map(entity,CropDto.class);
    }
    public CropEntity convertToEntity(CropDto dto) {
        return modelMapper.map(dto, CropEntity.class);
    }
    public List<CropDto> convertToDTO(List<CropEntity> crops) {

        return modelMapper.map(crops, new TypeToken<List<CropDto>>(){}.getType());
    }

    //equipment

    public EquipmentDto convertToDTO(EquipmentEntity entity) {
        return modelMapper.map(entity,EquipmentDto.class);
    }
    public EquipmentEntity convertToEntity(EquipmentDto dto) {
        return modelMapper.map(dto, EquipmentEntity.class);
    }
    public List<EquipmentDto> convertE_ToDTOList(List<EquipmentEntity> equipment) {
        return modelMapper.map(equipment, new TypeToken<List<EquipmentDto>>(){}.getType());
    }

    //Field
   public FieldDto convertFieldEntityToDTO(FieldEntity entity) {
        return modelMapper.map(entity,FieldDto.class);
   }
   public FieldEntity convertToEntity(FieldDto dto) {
        return modelMapper.map(dto, FieldEntity.class);
   }
   public List<FieldDto> convertF_EntityListToDTOList(List<FieldEntity> fields) {
        return modelMapper.map(fields, new TypeToken<List<FieldDto>>(){}.getType());
   }

   //Monitoring Log
    public MonitoringLogDto convertMLogEntityToDto(MonitoringLogEntity entity) {
        return modelMapper.map(entity,MonitoringLogDto.class);
    }
    public MonitoringLogEntity convertToEntity(MonitoringLogDto dto) {
        return modelMapper.map(dto, MonitoringLogEntity.class);
    }
    public List<MonitoringLogDto> convertM_ToDTOList(List<MonitoringLogEntity> logs) {
        return modelMapper.map(logs, new TypeToken<List<MonitoringLogDto>>(){}.getType());
    }

    //Staff
    public StaffDto convertStaffEntityToDTO(StaffEntity entity) {
        return modelMapper.map(entity,StaffDto.class);
    }
    public StaffEntity convertToEntity(StaffDto dto) {
        return modelMapper.map(dto, StaffEntity.class);
    }
    public List<StaffDto> convertS_ToDTOList(List<StaffEntity> staffs) {
        return modelMapper.map(staffs, new TypeToken<List<StaffDto>>(){}.getType());
    }

    //Vehicle
    public VehicleDto convertVehicleEntityToDTO(VehicleEntity entity) {
        return modelMapper.map(entity,VehicleDto.class);
    }
    public VehicleEntity convertToEntity(VehicleDto dto) {
        return modelMapper.map(dto, VehicleEntity.class);
    }
    public List<VehicleDto> convertV_ToDTOList(List<VehicleEntity> vehicles) {
        return modelMapper.map(vehicles, new TypeToken<List<VehicleDto>>(){}.getType());
    }
}
