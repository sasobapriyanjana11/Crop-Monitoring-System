package lk.ijse.gdse68.CropMonitoringSystem.controllor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthTest")
@CrossOrigin("*")
public class HealthController {
    @GetMapping
    public String healthTest(){
        return "app run successfully!";
    }

}
