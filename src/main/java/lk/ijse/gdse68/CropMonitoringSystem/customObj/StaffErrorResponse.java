package lk.ijse.gdse68.CropMonitoringSystem.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffErrorResponse implements StaffResponse,Serializable {
    private int errorCode;
    private String errorMessage;
}
