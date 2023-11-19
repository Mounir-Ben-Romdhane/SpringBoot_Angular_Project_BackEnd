package tn.esprit.spring.DAO.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Services.Chambre.IChambreService;
import tn.esprit.spring.DAO.Services.Chambre.IChambreService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("chambre")
@CrossOrigin(origins = "http://localhost:4200")
public class ChambreRestController {
    @Autowired
    IChambreService iChambreService;

    @Autowired
    ChambreRepository chambreRepository;

    @GetMapping("/findAll")
    List<Chambre> findAll(){
        return  iChambreService.findAll();
    }

    @PostMapping("/add")
    Chambre addChambre(@RequestBody Chambre b) {
        return iChambreService.addChambre(b);
    }

    @PutMapping("update/{id}")
    Chambre updateChambre(@PathVariable("id") Long id, @RequestBody Chambre b){
        return iChambreService.editChambre(id, b);
    }

    @DeleteMapping("/delete/{id}")
    void deleteChambre(@PathVariable("id") Long id){
        iChambreService.deleteById(id);
    }

    @GetMapping("/{id}")
    Chambre findById(@PathVariable("id") Long id){
        return iChambreService.findById(id);
    }

    @GetMapping("selectByNumSQL")
    List<Chambre> selectByNumSQL(long num){
        return chambreRepository.selectByNumSQL(num);
    }



}
