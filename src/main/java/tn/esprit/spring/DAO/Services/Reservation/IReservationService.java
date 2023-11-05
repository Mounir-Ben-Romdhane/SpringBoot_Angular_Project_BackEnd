package tn.esprit.spring.DAO.Services.Reservation;

import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation r);

    List<Reservation> addReservations(List<Reservation> reservations);
    Reservation editReservation(String id, Reservation r);
    List<Reservation> findAll();
    Reservation findById(String id);
    void deleteById(String id);
    void delete(Reservation r);
}
