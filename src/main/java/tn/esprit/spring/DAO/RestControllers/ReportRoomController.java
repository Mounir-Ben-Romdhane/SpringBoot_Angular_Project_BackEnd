package tn.esprit.spring.DAO.RestControllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.MaintenanceRoomReport;
import tn.esprit.spring.DAO.Services.Chambre.ChambreService;
import tn.esprit.spring.DAO.Services.ReportedRoom.ReportService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("reported")
public class ReportRoomController {

    @Autowired
    ReportService reportService;
    @Autowired
    ChambreService chambreService;

    @GetMapping("/findAll")
    List<MaintenanceRoomReport> findAll(){
        return reportService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<MaintenanceRoomReport> addReport(@RequestBody MaintenanceRoomReport report) {
        MaintenanceRoomReport newReport = reportService.addReport(report);
        return new ResponseEntity<>(newReport, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MaintenanceRoomReport> updateReport(@PathVariable Long id, @RequestBody MaintenanceRoomReport m) {
        MaintenanceRoomReport updatedReport = reportService.editReport(id, m);
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/delete/{id}")
    void delteReport(@PathVariable("id") long id){
        reportService.deleteById(id);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<MaintenanceRoomReport> findById(@PathVariable Long id) {
        MaintenanceRoomReport report = reportService.findById(id);
        return ResponseEntity.ok(report);
    }

}
