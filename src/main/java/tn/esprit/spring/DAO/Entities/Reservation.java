package tn.esprit.spring.DAO.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "reservation")
public class Reservation {

    @Id
    private String idReservation;


    @Column(name = "anneeUniversitaire")
    private LocalDate anneeUniversitaire; //JJ/MM/YYYY

    @Column(name = "estValide")
    private boolean estValide;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Etudiant> etudiants;
}
