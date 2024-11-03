package lk.ijse.gdse68.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.CropErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.CropDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.CropDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.service.CropService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDto cropDto) {
        cropDto.setCropCode(AppUtil.createCropId());
        var cropEntity = mapping.convertToEntity(cropDto);
        var saveCrop= cropDao.save(cropEntity);
        if(saveCrop==null){
            throw new DataPersistFailedException("cannot save crop");
        }

    }

    @Override
    public void updateCrop(String cropCode, CropDto cropDto) {
        Optional<CropEntity> tmpcropEntity = cropDao.findById(cropCode);
        if(!tmpcropEntity.isPresent()){
            throw new CropNotFoundException("crop not found");
        }else{

            tmpcropEntity.get().setCommonName(cropDto.getCommonName());
            tmpcropEntity.get().setScientificName(cropDto.getScientificName());
            tmpcropEntity.get().setImage(tmpcropEntity.get().getImage());
            tmpcropEntity.get().setCropSeason(tmpcropEntity.get().getCropSeason());
            tmpcropEntity.get().setField(tmpcropEntity.get().getField());
        }


    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> tmpcropEntity = cropDao.findById(cropCode);
        if(!tmpcropEntity.isPresent()){
            throw new CropNotFoundException("crop not found");
        }else{
            cropDao.deleteById(cropCode);
        }
    }

    @Override
    public CropResponse getSelectedCrop(String cropCode) {
       if(cropDao.existsById(cropCode)){
           return mapping.convertToDTO(cropDao.getReferenceById(cropCode));
       }else{
           return new CropErrorResponse(0,"crop not found");
       }
    }

    @Override
    public List<CropDto> getAllCrops() {
        return mapping.convertToDTO(cropDao.findAll());
    }
}
