package lk.ijse.gdse68.CropMonitoringSystem.controllor;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.FieldDto;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class FieldController {
    @Autowired
    private FieldService fieldService;

    private static final Logger logger = LoggerFactory.getLogger(FieldController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveField(
            @RequestParam("fieldCode")String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("extentSize") double extentSize,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,
             @RequestParam("equipmentCode") String equipmentCode) {
    try{
        // Convert images to Base64
        byte[] imageByteCollection1 = image1.getBytes();
        String base64ProfilePic1 = AppUtil.toBase64ProfilePic(imageByteCollection1);

        byte[] imageByteCollection2 = image2.getBytes();
        String base64ProfilePic2 = AppUtil.toBase64ProfilePic(imageByteCollection2);

        // Build DTO
        FieldDto buildFieldDTO = new FieldDto();
        buildFieldDTO.setFieldCode(fieldCode);
        buildFieldDTO.setFieldName(fieldName);
        buildFieldDTO.setExtentSize(extentSize);
        buildFieldDTO.setFieldLocation(new Point((int)latitude,(int)longitude));
        buildFieldDTO.setImage1(base64ProfilePic1);
        buildFieldDTO.setImage2(base64ProfilePic2);
        buildFieldDTO.setEquipmentCode(equipmentCode);

        // Save field
        fieldService.saveField(buildFieldDTO);
        logger.info("Save field successfully");
        return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (DataPersistFailedException e) {
        return new ResponseEntity<>("Error saving field data", HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        System.err.println("Error occurred while saving item: " + e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @PostMapping(value = "/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> testField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("extentSize") Double extentSize) {
        System.out.println("fieldName: " + fieldName);
        System.out.println("extentSize: " + extentSize);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping (value = "/{fieldCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateFields (
            @PathVariable ("fieldCode") String id,
            @RequestParam ("fieldName") String fieldName,
            @RequestParam ("extentSize") double fieldSize,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("image1") MultipartFile fieldImage1,
            @RequestParam("image2") MultipartFile fieldImage2,
            @RequestParam("equipmentCode") String equipmentCode,
            @RequestParam("staffId")List<String> staffId
    ){
        try {
            byte[] imageByteCollection1 = fieldImage1.getBytes();
            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(imageByteCollection1);

            byte[] imageByteCollection2 = fieldImage2.getBytes();
            String base64ProfilePic2 = AppUtil.toBase64ProfilePic(imageByteCollection2);

            FieldDto fieldDTO = new FieldDto();
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setExtentSize(fieldSize);
            fieldDTO.setFieldLocation(new Point((int) latitude, (int) longitude));
            fieldDTO.setImage1(base64ProfilePic1);
            fieldDTO.setImage2(base64ProfilePic2);
            fieldDTO.setEquipmentCode(equipmentCode);
            fieldDTO.setStaffId(staffId);
            fieldService.updateField(id,fieldDTO);
            logger.info("Update field successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping("/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable ("fieldCode") String fieldCode){

        try {
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldResponse getSelectedField(@PathVariable ("fieldCode") String fieldCode){
        return fieldService.getSelectedField(fieldCode);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDto> getAllFields(){
        return fieldService.getAllFields();
    }

}
