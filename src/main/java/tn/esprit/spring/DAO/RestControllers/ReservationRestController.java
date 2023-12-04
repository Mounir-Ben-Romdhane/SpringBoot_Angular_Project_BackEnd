package tn.esprit.spring.DAO.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Services.Chambre.ChambreService;
import tn.esprit.spring.DAO.Services.Etudiant.EtudiantService;
import tn.esprit.spring.DAO.Services.Reservation.IReservationService;
import tn.esprit.spring.DAO.Services.Reservation.ReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("reservation")


@CrossOrigin("*")

public class ReservationRestController {
    @Autowired
    IReservationService iReservationService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ChambreService chambreService;

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/findAll")
    List<Reservation> findAll(){
        return  iReservationService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    Reservation addReservation(@RequestBody Reservation r) {
        return iReservationService.addReservation(r);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Reservation updateReservation(@PathVariable("id") String id, @RequestBody Reservation r){
        return iReservationService.editReservation(id, r);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteReservation(@PathVariable("id") String id){
        iReservationService.deleteById(id);
    }

    @GetMapping("/{id}")
    Reservation findById(@PathVariable("id") String id){
        return iReservationService.findById(id);
    }


   @PostMapping("generateReservation/{numChambre}/{cin}")
    Reservation addReseravationavecChamber(@PathVariable("numChambre") long numChambre ,  @PathVariable("cin") long cin){
        return iReservationService.ajouterReservationEtAssignerAChambreEtAEtudiant(numChambre, cin);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<?> acceptReservation (@PathVariable String id){
        reservationService.acceptReservation(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/refuse")
    public ResponseEntity<?> refuseReservation (@PathVariable String id){
        reservationService.refuseReservation(id);
        return ResponseEntity.ok().build();
    }

  /*  @PostMapping("/addreservation")
    Reservation addReservationWithPayment(@RequestBody Reservation r, @RequestParam PaymentMethods paymentMethod) {
        return iReservationService.addReservationWithPayment(r, paymentMethod);
    }*/

    //retreive data from service chambre to get All rooms directlly using stream method
    @GetMapping("/roomNumbers")
    public ResponseEntity<List<Long>> getAllRoomNumbers() {
        List<Long> roomNumbers = chambreService.findAllRoomNumbers();
        return new ResponseEntity<>(roomNumbers, HttpStatus.OK);
    }

    //retreive data from service chambre to get All Cins directlly using query
    @GetMapping("/cins")
    public ResponseEntity<List<Long>> getAllStudentCINs() {
        List<Long> cins = etudiantService.findAllCINs();
        return new ResponseEntity<>(cins, HttpStatus.OK);
    }



}