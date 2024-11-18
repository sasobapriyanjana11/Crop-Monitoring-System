package lk.ijse.gdse68.CropMonitoringSystem.controllor;

import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.CropDto;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.service.CropService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
@RequiredArgsConstructor
public class CropController {
    @Autowired
    private CropService cropService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void > createUser(
            @RequestPart("commonName")String commonName,
            @RequestPart("scientificName")String scientificName,
            @RequestPart("image") MultipartFile image,
            @RequestPart("category")String category,
            @RequestPart("cropSeason")String cropSeason,
            @RequestPart("fieldCode")String fieldCode
         ){

        try {
            String base64ProfilePic = AppUtil.toBase64ProfilePic(image);
            // build the user object
            CropDto buildCropDTO = new CropDto();
            buildCropDTO.setCommonName(commonName);
            buildCropDTO.setScientificName(scientificName);
            buildCropDTO.setImage(base64ProfilePic);
            buildCropDTO.setCategory(category);
            buildCropDTO.setCropSeason(cropSeason);
            buildCropDTO.setFieldCode(fieldCode);


            //send to the service layer
            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{cropCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void>  updateUser(@PathVariable("cropCode") String cropCode,
                                            @RequestPart("commonName")String commonName,
                                            @RequestPart("scientificName")String scientificName,
                                            @RequestPart("image") MultipartFile image,
                                            @RequestPart("category")String category,
                                            @RequestPart("cropSeason")String cropSeason,
                                            @RequestPart("fieldCode")String fieldCode
                                           ){
        try {

            String base64ProfilePic = AppUtil.toBase64ProfilePic(image);
            CropDto updateCropDTO = new CropDto();
            updateCropDTO.setCommonName(commonName);
            updateCropDTO.setScientificName(scientificName);
            updateCropDTO.setImage(base64ProfilePic);
            updateCropDTO.setCategory(category);
            updateCropDTO.setCropSeason(cropSeason);
            updateCropDTO.setFieldCode(fieldCode);

            cropService.updateCrop(cropCode,updateCropDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode){
        try{
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "allcrops", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDto> getAllCrops(){
        return cropService.getAllCrops();
    }
}
