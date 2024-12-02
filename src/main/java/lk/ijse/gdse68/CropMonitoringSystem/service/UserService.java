package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.UserResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService{
    void saveUser(UserDto userDTO);
    List<UserDto> getAllUsers();
    UserResponse getSelectedUser(String email);
    void updateUser(String email, UserDto userDTO);
    void deleteUser(String email);
    UserDetailsService userDetailsService();
}
