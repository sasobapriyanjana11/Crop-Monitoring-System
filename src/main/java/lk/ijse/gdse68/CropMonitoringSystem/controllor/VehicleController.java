package lk.ijse.gdse68.CropMonitoringSystem.controllor;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.VehicleResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.VehicleDto;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.VehicleNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
@CrossOrigin("*")
@RequiredArgsConstructor
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createVehicle(@RequestBody VehicleDto vehicleDto) {
        try{
            vehicleService.saveVehicle(vehicleDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{vehicleCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@PathVariable ("vehicleCode") String vehicleCode, @RequestBody VehicleDto vehicleDto) {

        try {
            if(vehicleDto==null && (vehicleCode==null||vehicleDto.equals(""))){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.updateVehicle(vehicleCode, vehicleDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode){
        try{
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="all_vehicles",produces = MediaType.APPLICATION_JSON_VALUE)
        public List<VehicleDto> getAllVehicles(){
            return vehicleService.getAllVehicles();
     }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleResponse getVehicleById(@PathVariable("id") String id) {
        return vehicleService.getSelectedVehicle(id);
    }


}
