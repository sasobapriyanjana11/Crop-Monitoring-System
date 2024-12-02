package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.UserResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.SuperDto;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements SuperDto, UserResponse {
    private String email;
    private String password;
    private Role role;
}

