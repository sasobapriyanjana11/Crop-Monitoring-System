package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class StaffDto implements SuperDto {

    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private LocalDate joinDate;
    private LocalDate DOB;
    private String buildingNo;
    private String lane;
    private String city;
    private String state;
    private String postalCode;
    private String contactNo;
    private String email;

    private List<String> fieldCodes; // List of FieldEntity's fieldCodes
    private String vehicleCode;

}
