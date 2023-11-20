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
        r.setStatus(ReservationStatus.EN_COURS);
        return reservationRepository.save(r);
    }

    @Override
    public List<Reservation> addReservations(List<Reservation> reservations) {
        reservations.forEach(r -> r.setStatus(ReservationStatus.EN_COURS)); // Set default status for each
        return reservationRepository.saveAll(reservations);

    }

    @Override
    public Reservation editReservation(String id, Reservation r) {
        return reservationRepository.findById(id)
                .map(existingReservation -> {
                    existingReservation.setAnneeUniversitaire(r.getAnneeUniversitaire());
                    existingReservation.setEstValide(r.isEstValide());
                    existingReservation.setEtudiants(r.getEtudiants());
                    // Assuming status is being passed in the updated reservation
                    existingReservation.setStatus(r.getStatus());
                    return reservationRepository.save(existingReservation);
                })
                .orElse(null);
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
        int  i;
        int numReservation;
        Etudiant e = etudiantRepository.findByCin(cin);
        System.out.println(e.getNomEt());
        Chambre c = new Chambre() ;
        boolean test = false ;
        //---------------------------------
        LocalDate dateDebutAU;
        LocalDate dateFinAU;
        int year = LocalDate.now().getYear() % 100;
        if (LocalDate.now().getMonthValue() <= 7) {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + (year - 1)), 9, 15);
            dateFinAU = LocalDate.of(Integer.parseInt("20" + year), 6, 30);
        } else {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + year), 9, 15);
            dateFinAU = LocalDate.of(Integer.parseInt("20" + (year + 1)), 6, 30);
        }
        //---------------------------------
        if(e != null){
            c = chambreRepository.findByNumeroChambre(numChambre);

            Set<Reservation> reservations = c.getReservations() ;
            i = (int) reservations.stream().filter(Reservation::isEstValide).count();
            if(c.getTypeChambre().equals(TypeChambre.SIMPLE)&&i==0){
                test = true ;
            }else if (c.getTypeChambre().equals(TypeChambre.DOUBLE) && i<=1){
                test = true ;
            }else if (c.getTypeChambre().equals(TypeChambre.TRIPLE) && i<=2){
                test = true ;
            }

        }
        if(!test){
            return new Reservation();
        }else{
            System.out.println("creation Reservation");
            Reservation r = new Reservation();
            r.setIdReservation(dateDebutAU.getYear()+"-"+dateFinAU.getYear()+"-"+c.getBloc().getNomBloc()
                    +"-"+c.getNumeroChambre()+"-"+e.getCin());
            r.setAnneeUniversitaire(new Date());
            r.setEstValide(true);

            //chamber hwa il parent
            c.getReservations().add(r);
            chambreRepository.save(c);
            // reservation heya il parent w etudiant how child
            r.getEtudiants().add(e);

            return reservationRepository.save(r);
        }
    }


    @Override
    public void acceptReservation(String idReservation) {
        Reservation reservation = findReservationById(idReservation);
        if (reservation != null) {
            reservation.setStatus(ReservationStatus.ACTIVE);
            saveReservation(reservation);
        }
    }

    @Override
    public void refuseReservation(String idReservation) {
        Reservation reservation = findReservationById(idReservation);
        if (reservation != null) {
            reservation.setStatus(ReservationStatus.REFUSED);
            saveReservation(reservation);
        }
    }

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


    private Reservation findReservationById(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    private Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }




}
