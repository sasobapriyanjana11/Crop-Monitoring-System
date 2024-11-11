package lk.ijse.gdse68.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.MonitoringLogErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.FieldDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.MonitoringLogDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.MonitoringLogDto;
import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.MonitoringLogEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.MonitoringLogException;
import lk.ijse.gdse68.CropMonitoringSystem.service.MonitoringLogService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonitoringLogServiceImpl implements MonitoringLogService {
    @Autowired
    private MonitoringLogDao monitoringLogDao;

    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveMonitoringLog(MonitoringLogDto monitoringLogDto) {
        monitoringLogDto.setLogCode(AppUtil.createMonitoringLogId());
        var MonitoringLogoEntity=mapping.convertToEntity(monitoringLogDto);
        var saveMonitoringLog=monitoringLogDao.save(MonitoringLogoEntity);
        if(saveMonitoringLog==null){
            throw new DataPersistFailedException("cannot save monitoring log");
        }
    }

    @Override
    public void updateMonitoringLog(String logId, MonitoringLogDto monitoringLogDto) {
        Optional<MonitoringLogEntity> tmpLogEntity = monitoringLogDao.findById(logId);

        if (!tmpLogEntity.isPresent()) {
            throw new MonitoringLogException("monitoring log not found");
        } else {
            MonitoringLogEntity monitoringLog = tmpLogEntity.get();

            // Update simple fields
            monitoringLog.setLogCode(monitoringLogDto.getLogCode());
            monitoringLog.setLogDate(monitoringLogDto.getLogDate());
            monitoringLog.setObservationDetails(monitoringLogDto.getObservationDetails());
            monitoringLog.setObservedImage(monitoringLogDto.getObservedImage());

            // Retrieve and set the field entity
            FieldEntity fieldEntity = fieldDao.findById(monitoringLogDto.getFieldCode())
                    .orElseThrow(() -> new MonitoringLogException("Field not found"));
            monitoringLog.setField(fieldEntity);

            // Save the updated entity
            monitoringLogDao.save(monitoringLog);
        }
    }

    @Override
    public void deleteMonitoringLog(String logId) {
        Optional<MonitoringLogEntity> tmpMLogEntity = monitoringLogDao.findById(logId);
        if(!tmpMLogEntity.isPresent()){
            throw new MonitoringLogException("log is not found");
        }else{
            monitoringLogDao.deleteById(logId);
        }
    }

    @Override
    public MonitoringLogResponse getSelectedMonitoringLog(String logId) {
        if(monitoringLogDao.existsById(logId)){
            return mapping.convertMLogEntityToDto(monitoringLogDao.getReferenceById(logId));
        }else{
            return new MonitoringLogErrorResponse(0,"log not found");
        }
    }

    @Override
    public List<MonitoringLogDto> getAllMonitoringLogs() {
        return mapping.convertM_ToDTOList(monitoringLogDao.findAll());
    }
}
