package lk.ijse.gdse68.CropMonitoringSystem.controllor;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.MonitoringLogDto;
import lk.ijse.gdse68.CropMonitoringSystem.exception.MonitoringLogException;
import lk.ijse.gdse68.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.CropMonitoringSystem.service.MonitoringLogService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/monitoring_logs")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MonitoringLogController {
    @Autowired
    private MonitoringLogService monitoringLogService;
    @Autowired
    private FieldService fieldService;
    private PatternValidator patternValidator;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void>saveMonitoringLogs(@RequestParam("logDate")@DateTimeFormat(pattern="YYYY-MM-dd") Date logDate,
                                                  @RequestParam("observationDetails") String observationDetails,
                                                  @RequestParam("observedImage") MultipartFile observedImage,
                                                  @RequestParam("fieldCode") String fieldCode){
        try{
            String base64ProfilePic1 = AppUtil.toBase64Image(observedImage);

            MonitoringLogDto monitoringLogDto=new MonitoringLogDto();
            monitoringLogDto.setLogDate(logDate);
            monitoringLogDto.setObservationDetails(observationDetails);
            monitoringLogDto.setObservedImage(base64ProfilePic1);
            monitoringLogDto.setFieldCode(fieldCode);

            monitoringLogService.saveMonitoringLog(monitoringLogDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (MonitoringLogException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{logCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void>updateMonitoringLogs(@PathVariable ("logCode") String logCode,
                                                    @RequestParam("logDate") @DateTimeFormat(pattern="YYYY-MM-dd")Date logDate,
                                                    @RequestParam("observationDetails") String observationDetails,
                                                    @RequestParam("observedImage") MultipartFile observedImage,
                                                    @RequestParam("fieldCode") String fieldCode){
        try{
            String base64ProfilePic1 = AppUtil.toBase64Image(observedImage);

            MonitoringLogDto updatedMonitoringLogDto=new MonitoringLogDto();
            updatedMonitoringLogDto.setLogDate(logDate);
            updatedMonitoringLogDto.setObservationDetails(observationDetails);
            updatedMonitoringLogDto.setObservedImage(base64ProfilePic1);
            updatedMonitoringLogDto.setFieldCode(fieldCode);

            monitoringLogService.updateMonitoringLog(logCode,updatedMonitoringLogDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (MonitoringLogException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteMonitoringLogs(@PathVariable("logCode") String logCode){
        try{
            monitoringLogService.deleteMonitoringLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (MonitoringLogException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="all_logs",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDto> getAllMonitoringLogs(){
        return monitoringLogService.getAllMonitoringLogs();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogResponse getLogsByID (@PathVariable ("id") String id){
        return monitoringLogService.getSelectedMonitoringLog(id);
    }
}
