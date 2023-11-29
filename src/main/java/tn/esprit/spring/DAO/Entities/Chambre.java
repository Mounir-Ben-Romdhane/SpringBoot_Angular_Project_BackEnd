package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column(name = "statut")
    private String statut;

    @Column(name = "dateDebut")
    private LocalDate dateDebut;

    @Column(name = "dateFin")
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "bloc_id_bloc", referencedColumnName = "idBloc")
    @JsonBackReference
    private Bloc bloc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chambre")
    @JsonIgnore
    private Set<Reservation> reservations = new HashSet<>();

    public long getIdChambre() {
        return idChambre;
    }

    public long getNumeroChambre() {
        return numeroChambre;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public String getStatut() {
        return statut;
    }

    public Bloc getBloc() {
        return bloc;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setIdChambre(long idChambre) {
        this.idChambre = idChambre;
    }

    public void setNumeroChambre(long numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }


    public boolean isOccupee() {
        return !reservations.isEmpty();
    }
    public boolean isOccupeeALaDate(LocalDate date) {
        if (dateDebut == null || dateFin == null) {
            return false; // You may need to adapt this condition
        }

        for (Reservation reservation : reservations) {
            if (reservation.couvreDate(date)) {
                return true;
            }
        }
        return false;
    }


}
