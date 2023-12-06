package tn.esprit.spring.DAO.Services.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
import tn.esprit.spring.DAO.Repositories.ReservationRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;



    public List<Reservation> addReservations(List<Reservation> reservations) {
        reservations.forEach(r -> r.setStatus(ReservationStatus.EN_COURS)); // Set default status for each
        return reservationRepository.saveAll(reservations);

    public Reservation addReservation(Reservation r) {
        return null;

    }

    @Override
    public Reservation editReservation(String id, Reservation r) {
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(String id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public void deleteById(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void delete(Reservation e) {
        reservationRepository.delete(e);
    }

    @Override
    public Reservation ajouterReservationEtAssignerAChambreEtAEtudiant(long numChambre, long cin) {
        return null;
    }

    @Override
    public void acceptReservation(String idReservation) {

    }

    @Override
    public void refuseReservation(String idReservation) {


    public long getReservationParAnneeUniversitaire(LocalDate debutAnnee, LocalDate finAnnee) {
        Date start = java.sql.Date.valueOf(debutAnnee);
        Date end = java.sql.Date.valueOf(finAnnee);
        return reservationRepository.countReservationsBetween(start, end);
    }



  /*  @Override
    public Reservation addReservationWithPayment(Reservation reservation, PaymentMethods paymentMethod) {
        reservation.setPaymentMethod(paymentMethod);
        return reservationRepository.save(reservation);
    }*/

    }



    private Reservation findReservationById(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    private Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }





}
