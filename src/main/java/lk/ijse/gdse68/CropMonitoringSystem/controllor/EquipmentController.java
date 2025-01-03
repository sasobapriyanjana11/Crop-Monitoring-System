package lk.ijse.gdse68.CropMonitoringSystem.controllor;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.EquipmentDto;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipments")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
//@CrossOrigin("*")
@RequiredArgsConstructor
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    static Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDto equipmentDto)  {
        try{
            if (equipmentDto == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Added null check
            }
            equipmentService.saveEquipment(equipmentDto);
            logger.info("Equipment saved to database");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{equipmentCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@PathVariable ("equipmentCode") String equipmentCode, @RequestBody EquipmentDto equipmentDto)  {

        try {
            if(equipmentDto==null && (equipmentCode==null||equipmentDto.equals(""))){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(equipmentCode,equipmentDto);
            logger.info("Equipment updated to database");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{equipmentCode}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentCode") String equipmentCode){
        try{
            equipmentService.deleteEquipment(equipmentCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="all_equipments",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDto> getAllEquipments(){
        return equipmentService.getAllEquipments();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentResponse getEquipmentById(@PathVariable("id")String id){
        return equipmentService.getSelectedEquipmentp(id);
    }

}
