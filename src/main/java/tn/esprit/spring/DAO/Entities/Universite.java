package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "universite")
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUniversite;


    @Column(name = "nomUniversite")
    private String nomUniversite;

    @Column(name = "adresse")
    private String adresse;


    @OneToOne( mappedBy = "universite")
    private Foyer foyer;

    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "foyer_id_foyer")
    @JsonIgnore
    private Foyer foyer;


}
