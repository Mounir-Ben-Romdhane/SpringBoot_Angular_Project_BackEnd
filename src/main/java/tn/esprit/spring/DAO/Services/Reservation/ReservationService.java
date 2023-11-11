package tn.esprit.spring.DAO.Services.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
import tn.esprit.spring.DAO.Repositories.ReservationRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

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

    @Override
    public Reservation ajouterReservationEtAssignerAChambreEtAEtudiant(Long numChambre, Long cin) {
        //Recuperation des entity
        Chambre chambre = chambreRepository.findByNumeroChambre(numChambre);
        Etudiant etudiant = etudiantRepository.findByCin(cin);


        String idRes = Year.now().getValue()+"/"+(Year.now().getValue()+1) + "-"+chambre.getBloc().getNomBloc()+"-"
                +numChambre+"-"+cin;
        Reservation reservation = new Reservation();
        reservation.setIdReservation(idRes);
        reservation.setEstValide(true);
        reservation.setAnneeUniversitaire(LocalDate.now());
        reservation.getEtudiants().add(etudiant);



        chambre.getReservations().add(reservation);
        etudiant.getReservations().add(reservation);

        chambreRepository.save(chambre);
        etudiantRepository.save(etudiant);
        reservationRepository.save(reservation);
        return reservation;
    }
}
