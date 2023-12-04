package tn.esprit.spring.DAO.Entities;

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

    @Column(name = "estValide")
    private boolean estValide;


    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Etudiant> etudiants = new HashSet<>();

    // New status set default to en_cours
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status = ReservationStatus.EN_COURS;

  /*  //payment method :
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentMethod")
    private PaymentMethods paymentMethod;*/


}
