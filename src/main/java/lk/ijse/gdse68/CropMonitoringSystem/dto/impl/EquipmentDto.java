//package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;
//
//import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class EquipmentDto implements SuperDto {
//
//    private String id;
//    private String name;
//    private String type;
//    private String status;
//    private String fieldCode;
//    private String staffId;
//
//}
package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDto implements SuperDto, EquipmentResponse {

    private String equipmentId;
    private String name;
    private String type;
    private String status;
    private List<String> staffIds; // List of assigned staff IDs
    private List<String> fieldCodes;
}

