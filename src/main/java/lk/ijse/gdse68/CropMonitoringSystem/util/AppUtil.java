package lk.ijse.gdse68.CropMonitoringSystem.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class AppUtil {
//    public static String createCropId(){
//        return "Crop- "+ UUID.randomUUID();
//    }

//    public static String createEquipmentId(){
//        return "Equipment- "+ UUID.randomUUID();
//    }

//    public static String createFieldId(){
//        return "Field- "+ UUID.randomUUID();
//    }
//
//    public static String createMonitoringLogId(){
//        return "MLog- "+ UUID.randomUUID();
//    }
//
//    public static String createStaffId(){
//        return "Staff- "+ UUID.randomUUID();
//    }
//
//    public static String createVehicleId(){
//        return "Vehicle- "+ UUID.randomUUID();
//    }

    public static String toBase64Image(MultipartFile file) {
        try {
            byte[] imageBytes = file.getBytes();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String toBase64ProfilePic(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
