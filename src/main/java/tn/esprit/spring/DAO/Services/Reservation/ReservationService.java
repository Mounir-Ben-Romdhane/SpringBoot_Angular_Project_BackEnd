package tn.esprit.spring.DAO.Services.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
import tn.esprit.spring.DAO.Repositories.ReservationRepository;

import java.util.List;

@Service
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public List<Reservation> addReservations(List<Reservation> reservations) {
        return reservationRepository.saveAll(reservations);
    }

    @Override
    public Reservation editReservation(String id, Reservation r) {
        if(reservationRepository.findById(id).isPresent()){
            Reservation toUpdateReservation = reservationRepository.findById(id).get();
            //toUpdateReservation.setIdReservation(r.getIdReservation());
            toUpdateReservation.setAnneeUniversitaire(r.getAnneeUniversitaire());
            toUpdateReservation.setEstValide(r.isEstValide());
            toUpdateReservation.setEtudiants(r.getEtudiants());

            return reservationRepository.save(toUpdateReservation);
        }
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
}
