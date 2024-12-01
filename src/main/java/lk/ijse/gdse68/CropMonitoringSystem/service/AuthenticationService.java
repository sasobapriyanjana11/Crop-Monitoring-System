package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.UserDto;
import lk.ijse.gdse68.CropMonitoringSystem.jwtModels.JWTResponse;
import lk.ijse.gdse68.CropMonitoringSystem.jwtModels.SignIn;

public interface AuthenticationService {
    JWTResponse signIn(SignIn signIn);
    JWTResponse signUp(UserDto userDTO);
    JWTResponse refreshToken(String accessToken);
}
