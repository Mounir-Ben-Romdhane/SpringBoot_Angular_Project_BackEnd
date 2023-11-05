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
@Table(name = "etudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEtudiant;
    @Column(name = "nomEt")
    private String nomEt;
    @Column(name = "prenomEt")
    private String prenomEt;

    @Column(name = "cin")
    private long cin;

    @Column(name = "ecole")
    private String ecole;

    @Column(name = "dateNaissance")
    private Date dateNaissance; //JJ/MM/YYYY

    @ManyToMany(mappedBy = "etudiants", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;


}
