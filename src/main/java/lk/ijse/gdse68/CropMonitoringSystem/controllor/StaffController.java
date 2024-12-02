package lk.ijse.gdse68.CropMonitoringSystem.controllor;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.StaffDto;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.StaffNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDto staffDto) {
        try{
            staffService.saveStaff(staffDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{staffId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaff(@PathVariable ("staffId") String staff_id, @RequestBody StaffDto staffDto) {
        try {
            if (staffDto == null && (staff_id == null || staffDto.equals(""))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.updateStaff(staff_id, staffDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @DeleteMapping(value = "/{staffId}")
        public ResponseEntity<Void> deleteStaff(@PathVariable("staffId") String staff_id){
            try{
                staffService.deleteStaff(staff_id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch (StaffNotFoundException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @GetMapping(value="all_staff",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDto> getAllStaff(){
        return staffService.getAllStaff();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffResponse getStaffByID(@PathVariable ("id")String id){
        return staffService.getSelectedStaff(id);
    }



}
