package tn.esprit.spring.DAO.Services.Reservation;

import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation r);

    Reservation editReservation(String id, Reservation r);
    List<Reservation> findAll();
    Reservation findById(String id);
    void deleteById(String id);
    void delete(Reservation r);

    Reservation ajouterReservationEtAssignerAChambreEtAEtudiant (long numChambre, long cin) ;

    void acceptReservation(String idReservation);
    void refuseReservation(String idReservation);

  //  Reservation addReservationWithPayment(Reservation reservation, PaymentMethods paymentMethod);

    long getReservationParAnneeUniversitaire(LocalDate debutAnnee, LocalDate finAnnee);






}

