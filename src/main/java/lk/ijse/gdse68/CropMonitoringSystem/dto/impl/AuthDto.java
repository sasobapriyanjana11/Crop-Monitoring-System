package lk.ijse.gdse68.CropMonitoringSystem.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class AuthDto {
    private String email;
    private String token;
    private String refreshToken;
}
