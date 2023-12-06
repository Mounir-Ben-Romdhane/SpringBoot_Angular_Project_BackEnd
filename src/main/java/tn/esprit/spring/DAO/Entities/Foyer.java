package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "foyer")
public class Foyer {
    public long getIdFoyer() {
        return idFoyer;
    }

    public void setIdFoyer(long idFoyer) {
        this.idFoyer = idFoyer;
    }

    public String getNomFoyer() {
        return nomFoyer;
    }

    public void setNomFoyer(String nomFoyer) {
        this.nomFoyer = nomFoyer;
    }

    public long getCapaciteFoyer() {
        return capaciteFoyer;
    }

    public void setCapaciteFoyer(long capaciteFoyer) {
        this.capaciteFoyer = capaciteFoyer;
    }

    public Set<Bloc> getBlocs() {
        return blocs;
    }

    public void setBlocs(Set<Bloc> blocs) {
        this.blocs = blocs;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFoyer;

    @Column(name = "nomFoyer")
    private String nomFoyer;

    @Column(name = "capaciteFoyer")
    private long capaciteFoyer;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "foyer")
    @JsonIgnore
    private Set<Bloc> blocs = new HashSet<>();


    


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "universite_id")
    @JsonIgnore
    private Universite universite;

    private Set<Bloc> blocs = new HashSet<>();
    @OneToMany( mappedBy = "foyer")
    @JsonIgnore
    private List<Bloc> blocs;

}
