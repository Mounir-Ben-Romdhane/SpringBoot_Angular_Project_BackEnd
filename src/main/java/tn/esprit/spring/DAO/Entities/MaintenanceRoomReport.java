package tn.esprit.spring.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "ReportedRooms")
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceRoomReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idChambre", referencedColumnName = "idChambre")
    private Chambre chambre;

    @Enumerated(EnumType.STRING)
    @Column(name = "problem")
    private ProblemReport problem = ProblemReport.AUTRE;
    private String description;
    private LocalDate dateReport;

}
