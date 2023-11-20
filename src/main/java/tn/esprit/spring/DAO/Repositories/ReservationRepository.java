package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    Reservation findByIdReservation(String id);
    // 1- Recherche des réservations validées
    List<Reservation> findByEstValideTrue();

    // 2- Recherche des réservations par l'id de l'étudiant
    List<Reservation> findByEtudiantsIdEtudiant(long idEtudiant);

    // 3- Recherche des réservations par année et validité
    List<Reservation> findByAnneeUniversitaireAndEstValide(Date anneeUniversitaire, boolean estValide);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.anneeUniversitaire BETWEEN :debutAnnee AND :finAnnee")
    long countReservationsBetween(@Param("debutAnnee") Date debutAnnee,
                                  @Param("finAnnee") Date finAnnee);



}
