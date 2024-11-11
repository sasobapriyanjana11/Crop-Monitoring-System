package lk.ijse.gdse68.CropMonitoringSystem.dao;

import lk.ijse.gdse68.CropMonitoringSystem.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDao extends JpaRepository<EquipmentEntity,String> {
    EquipmentEntity findByEquipmentCode(String equipmentCode);
}
