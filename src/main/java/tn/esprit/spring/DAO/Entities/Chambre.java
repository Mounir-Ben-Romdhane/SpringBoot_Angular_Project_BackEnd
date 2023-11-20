package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "chambre")
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idChambre;


    @Column(name = "numeroChambre",unique = true)
    private long numeroChambre;

    @Column(name = "typeChambre")
    private TypeChambre typeChambre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    Bloc bloc;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reservation> reservations = new HashSet<>();
}
