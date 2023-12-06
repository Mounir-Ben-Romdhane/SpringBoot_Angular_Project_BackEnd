package tn.esprit.spring.DAO.Services.ReportedRoom;

import tn.esprit.spring.DAO.Entities.MaintenanceRoomReport;

import java.util.List;

public interface IReportService {

    MaintenanceRoomReport addReport(MaintenanceRoomReport m);

    MaintenanceRoomReport editReport(Long id , MaintenanceRoomReport m);

    List<MaintenanceRoomReport> findAll();

    MaintenanceRoomReport findById(long id);

    void deleteById(long id);
}
