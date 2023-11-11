package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
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
    @JsonIgnore
    private Set<Etudiant> etudiants = new HashSet<>();


}
