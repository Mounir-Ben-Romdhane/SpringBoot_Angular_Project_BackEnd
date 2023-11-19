package tn.esprit.spring.DAO.RestControllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Repositories.BlocRepository;
import tn.esprit.spring.DAO.Services.Bloc.IBlocService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("bloc")
@RequiredArgsConstructor
public class BlocRestController {

    @Autowired
    IBlocService iBlocService;

    @Autowired
    BlocRepository blocRepository;

    @GetMapping("/findAll")
    List<Bloc> findAll(){
        return  iBlocService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    Bloc addBloc(@RequestBody Bloc b) {
        return iBlocService.addBloc(b);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Bloc updateBloc(@PathVariable("id") Long id, @RequestBody Bloc b){
        return iBlocService.editBloc(id, b);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteBloc(@PathVariable("id") Long id){
        iBlocService.deleteById(id);
    }

    @GetMapping("/{id}")
    Bloc findById(@PathVariable("id") Long id){
        return iBlocService.findById(id);
    }

    @GetMapping("findByNameBloc")
    Bloc findByNomBloc(@RequestParam String name){
        return blocRepository.findByNomBloc(name);
    }

    @GetMapping("findByNomBlocAndCapaciteBloc")
    List<Bloc> findByNomBlocAndCapaciteBloc(@RequestParam String name,@RequestParam Long capacite){
        return blocRepository.findByNomBlocAndCapaciteBloc(name,capacite);
    }

    @PutMapping("affectuerChambresABloc/{nomBloc}")
    Bloc affecterChambresABloc(@PathVariable("nomBloc") String nomBloc,
                               @RequestBody List<Long> numChambres){
        return iBlocService.affecterChambresABloc(numChambres,nomBloc);
    }

    @PutMapping("affecterBlocAFoyer/{nomBloc}/{nomFoyer}")
    Bloc affecterBlocAFoyer(@PathVariable("nomBloc") String nomBloc,
                            @PathVariable("nomFoyer") String nomFoyer){
        return iBlocService.affecterBlocAFoyer(nomBloc,nomFoyer);
    }


}