package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    private String idReservation;

    @Column(name = "anneeUniversitaire")
    private Date anneeUniversitaire; //JJ/MM/YYYY

    @Column(name = "estValide", nullable = true)
    private Boolean estValide;



    // New status set default to en_cours
  //  @Enumerated(EnumType.STRING)
//    @Column(name = "status")
  //  private ReservationStatus status = ReservationStatus.EN_COURS;

  /*  //payment method :
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentMethod")
    private PaymentMethods paymentMethod;*/

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Etudiant> etudiants = new HashSet<>();
    @Column(name = "dateDebut")
    private LocalDate dateDebut;


    @Column(name = "dateFin")
    private LocalDate dateFin;

    // Autres méthodes

    public boolean couvreDate(LocalDate date) {
        return estValide && !(date.isBefore(dateDebut) || date.isAfter(dateFin));
    }

    @ManyToOne
    @JoinColumn(name = "chambre_idChambre", referencedColumnName = "idChambre")
    @JsonBackReference
    private Chambre chambre;

}
