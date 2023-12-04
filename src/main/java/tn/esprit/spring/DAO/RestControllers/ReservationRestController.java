package tn.esprit.spring.DAO.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Services.Chambre.ChambreService;
import tn.esprit.spring.DAO.Services.Etudiant.EtudiantService;
import tn.esprit.spring.DAO.Services.Reservation.IReservationService;
import tn.esprit.spring.DAO.Services.Reservation.ReservationService;

import java.time.LocalDate;
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
    Reservation addReservation(@RequestBody Reservation r) {
        return iReservationService.addReservation(r);
    }

    @PutMapping("update/{id}")
    Reservation updateReservation(@PathVariable("id") String id, @RequestBody Reservation r){
        return iReservationService.editReservation(id, r);
    }

    @DeleteMapping("/delete/{id}")
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


    @GetMapping("/idchambres")
    public ResponseEntity<List<Long>> getAllRoomIds() {
        List<Long> roomIds = chambreService.findAllRoomIds();
        return ResponseEntity.ok(roomIds);
    }

    //retreive data from service chambre to get All Cins directlly using query


    @GetMapping("/getReservationParAnneeUniversitaire")
    public long getReservationParAnneeUniversitaire(
            @RequestParam("debutAnnee") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debutAnnee,
            @RequestParam("finAnnee") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finAnnee) {

        return reservationService.getReservationParAnneeUniversitaire(debutAnnee, finAnnee);
    }

    @GetMapping("/cins")
    public ResponseEntity<List<Long>> getAllStudentCINs() {
        List<Long> cins = etudiantService.findAllCINs();
        return new ResponseEntity<>(cins, HttpStatus.OK);
    }


    @GetMapping("/unreservedchambre")
    public ResponseEntity<List<Long>> getUnreservedRooms() {
        List<Long> unreservedRooms = chambreService.findUnreservedRoomNumbers();
        return new ResponseEntity<>(unreservedRooms, HttpStatus.OK);
    }


    @GetMapping("/unreservedcins")
    public ResponseEntity<List<Long>> getUnreservedCins() {
        List<Long> unreservedCins = etudiantService.findUnreservedCinUsers();
        return new ResponseEntity<>(unreservedCins, HttpStatus.OK);
    }


}