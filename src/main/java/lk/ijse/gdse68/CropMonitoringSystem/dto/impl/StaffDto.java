package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto implements SuperDto, StaffResponse {

    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private LocalDate joinDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate DOB;
    private String address;
    private String postalCode;
    private String contactNo;
    private String email;
    private String equipmentCode;
    private String vehicleCode;
     private List<String> logCode;
}

