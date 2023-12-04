package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.DAO.Entities.Etudiant;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    // 1- Recherche par nom et prénom de l'étudiant
    List<Etudiant> findByNomEtAndPrenomEt(String nom, String prenom);

    // 2- Recherche par CIN de l'étudiant
    Etudiant findByCin(long cin);

    // 3- Recherche des étudiants par date de naissance après une certaine date
    List<Etudiant> findByDateNaissanceAfter(Date dateNaissance);

    // 4- Recherche des étudiants par nom d'école
    List<Etudiant> findByEcole(String nomEcole);

    // 5- Recherche des étudiants par réservation (id de réservation)
    List<Etudiant> findByReservationsIdReservation(String idReservation);

    // 6- Recherche des étudiants par réservation (année de réservation)
    List<Etudiant> findByReservationsAnneeUniversitaire(LocalDate anneeUniversitaire);

    // 7- Recherche des étudiants par nom d'école et date de naissance après une certaine date
    List<Etudiant> findByEcoleAndDateNaissanceAfter(String ecole, Date dateNaissance);

    // 8- Recherche des étudiants par réservation pour une année donnée et triés par date de naissance
    //List<Etudiant> findByReservationsAnneeUniversitaireAndOrderByDateNaissance(LocalDate anneeUniversitaire);

    //9 - find All Cins from etudiant :
    @Query("SELECT e.cin FROM Etudiant e")
    List<Long> findAllCINs();

    @Query(value = "SELECT e.cin FROM Etudiant e WHERE NOT EXISTS (" +
            "SELECT 1 FROM reservation_etudiants re " +
            "JOIN Reservation r ON re.reservations_id_reservation = r.id_reservation " +
            "WHERE re.etudiants_id_etudiant = e.id_etudiant AND r.status = 'ACTIVE')", nativeQuery = true)
    List<Long> findUnreservedCins();
    

}
