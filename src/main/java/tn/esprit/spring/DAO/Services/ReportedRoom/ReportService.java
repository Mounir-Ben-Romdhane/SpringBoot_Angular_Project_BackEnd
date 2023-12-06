package tn.esprit.spring.DAO.Services.ReportedRoom;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.MaintenanceRoomReport;
import tn.esprit.spring.DAO.Repositories.ReportRepository;
import tn.esprit.spring.DAO.Services.Chambre.ChambreService;

import java.util.List;

@Service
public class ReportService implements IReportService{

    @Autowired
    private ChambreService chambreService;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public MaintenanceRoomReport addReport(MaintenanceRoomReport report) {
       return reportRepository.save(report);
    }


    @Override
    public MaintenanceRoomReport editReport(Long id, MaintenanceRoomReport m) {
        return reportRepository.save(m);
    }

    @Override
    public List<MaintenanceRoomReport> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public MaintenanceRoomReport findById(long id) {
        return reportRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        reportRepository.deleteById(id);
    }
}
