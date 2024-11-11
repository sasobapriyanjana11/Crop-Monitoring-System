package lk.ijse.gdse68.CropMonitoringSystem.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String createCropId(){
        return "Crop- "+ UUID.randomUUID();
    }

    public static String createEquipmentId(){
        return "Equipment- "+ UUID.randomUUID();
    }

    public static String createFieldId(){
        return "Field- "+ UUID.randomUUID();
    }

    public static String createMonitoringLogId(){
        return "MLog- "+ UUID.randomUUID();
    }

    public static String createStaffId(){
        return "Staff- "+ UUID.randomUUID();
    }

    public static String createVehicleId(){
        return "Vehicle- "+ UUID.randomUUID();
    }

    public static String toBase64ProfilePic(MultipartFile profilePic){
        String proPicBase64=null;
        try{
            byte[] imageByteCollection= profilePic.getBytes();
            proPicBase64= Base64.getEncoder().encodeToString(imageByteCollection);

        }catch(Exception e ){
            e.printStackTrace();
        }
        return proPicBase64;
    }
}
