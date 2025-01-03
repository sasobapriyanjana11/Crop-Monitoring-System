package lk.ijse.gdse68.CropMonitoringSystem.controllor;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.CropDto;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.service.CropService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class CropController {
    @Autowired
    private CropService cropService;
    static Logger logger = LoggerFactory.getLogger(CropController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void > createCrop(
            @RequestPart("cropCode")String cropCode,
            @RequestPart("commonName")String commonName,
            @RequestPart("scientificName")String scientificName,
            @RequestPart("image") MultipartFile image,
            @RequestPart("category")String category,
            @RequestPart("cropSeason")String cropSeason,
            @RequestPart("fieldCode")String fieldCode
         ){
        if (cropCode == null || cropCode.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            String base64ProfilePic = AppUtil.toBase64Image(image);
            // build the user object
            CropDto buildCropDTO = new CropDto();
            buildCropDTO.setCropCode(cropCode);
            buildCropDTO.setCommonName(commonName);
            buildCropDTO.setScientificName(scientificName);
            buildCropDTO.setImage(base64ProfilePic);
            buildCropDTO.setCategory(category);
            buildCropDTO.setCropSeason(cropSeason);
            buildCropDTO.setFieldCode(fieldCode);


            buildCropDTO.setLogCode(null);

            cropService.saveCrop(buildCropDTO);
            logger.info("crop added successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            logger.error("There was a error while updating");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping (value = "/{cropCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <Void> updateCrops (@PathVariable ("cropCode") String id ,
                                              @RequestParam("commonName") String commonName,
                                              @RequestParam ("scientificName") String scientificName,
                                              @RequestParam("image") MultipartFile image,
                                              @RequestParam("category") String category,
                                              @RequestParam("cropSeason") String cropSeason,
                                              @RequestParam("fieldCode")String fieldCode,
                                              @RequestParam("logCode")List<String> logCode
    ){
        try {

            String base64Image = AppUtil.toBase64Image(image);
            CropDto cropDTO = new CropDto();
            cropDTO.setCommonName(commonName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setImage(base64Image);
            cropDTO.setCategory(category);
            cropDTO.setCropSeason(cropSeason);
            cropDTO.setFieldCode(fieldCode);
            cropDTO.setLogCode(logCode);
            cropService.updateCrop(id,cropDTO);
            logger.info("crop updated successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            logger.error("Crop not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode){
        try{
            cropService.deleteCrop(cropCode);
            logger.info("crop deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            logger.error("Crop not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "allcrops", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDto> getAllCrops(){
        return cropService.getAllCrops();
    }

    @GetMapping(value = "/{cropCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropResponse getCropById(@PathVariable("cropCode") String id){
        return cropService.getSelectedCrop(id);
    }
}
