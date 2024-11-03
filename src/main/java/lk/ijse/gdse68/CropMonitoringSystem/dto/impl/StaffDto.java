//package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;
//
//import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.List;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//
//public class StaffDto implements SuperDto {
//
//    private String id;
//    private String firstName;
//    private String lastName;
//    private String designation;
//    private LocalDate joinDate;
//    private LocalDate DOB;
//    private String buildingNo;
//    private String lane;
//    private String city;
//    private String state;
//    private String postalCode;
//    private String contactNo;
//    private String email;
//
//    private List<String> fieldCodes; // List of FieldEntity's fieldCodes
//    private String vehicleCode;
//
//}
package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto implements SuperDto, StaffResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private String joinedDate;
    private String dob;
    private String buildingNo;
    private String lane;
    private String city;
    private String state;
    private String postalCode;
    private String contactNo;
    private String email;
    private String role;
    private List<String> fieldCodes; // List of field codes assigned to this staff member
    private List<String> vehicleCodes; // List of vehicle codes assigned to this staff member
}

