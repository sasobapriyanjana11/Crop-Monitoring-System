//package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;
//
//import jakarta.persistence.*;
//import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
//import lk.ijse.gdse68.CropMonitoringSystem.entity.SuperEntity;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class FieldDto implements SuperDto {
//
//    private String fieldCode;
//    private String fieldName;
//    private Double extentSize;
//    private String fieldLocation;
//    private List<String> cropCodes;
//    private List<String> staffIds;
//}
package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDto implements SuperDto, FieldResponse {

    private String fieldCode;
    private String fieldName;
    private String location; // GPS coordinates as String
    private double extentSize;
    private List<String> cropCodes; // List of crop codes related to the field
    private List<String> staffIds; // List of staff IDs allocated to the field
    private String fieldImage1;
    private String fieldImage2;
}

