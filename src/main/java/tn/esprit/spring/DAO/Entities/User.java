package tn.esprit.spring.DAO.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "utilisateur")
public class User{

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstName")
    private String nom;
    @Column(name = "lastName")
    private String prenom;

    @Column(name = "dateNaissance")
    private LocalDate dateNaissance; //JJ/MM/YYYY

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Temporal(TemporalType.DATE) //YYYY-MM-JJ hh:mm:ss
    private Date dateInscription; //java.util

    @Transient
    private int age; // Pour ne pas mapper l'attribut age dans la BD




}
