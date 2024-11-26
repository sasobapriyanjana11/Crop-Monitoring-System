package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.impl.MonitoringLogDto;
import java.util.List;

public interface MonitoringLogService {
    void saveMonitoringLog(MonitoringLogDto monitoringLogDto)  ;
    void updateMonitoringLog(String logId,MonitoringLogDto monitoringLogDto);
    void deleteMonitoringLog(String logId);
    MonitoringLogResponse getSelectedMonitoringLog(String logId);
    List<MonitoringLogDto> getAllMonitoringLogs();
}
