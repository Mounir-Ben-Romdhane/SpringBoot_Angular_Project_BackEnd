package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.DAO.Entities.MaintenanceRoomReport;


@Repository
public interface ReportRepository extends JpaRepository<MaintenanceRoomReport,Long> {
}
