package tn.esprit.spring.DAO.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Repositories.BlocRepository;
import tn.esprit.spring.DAO.Services.Bloc.IBlocService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("bloc")
public class BlocRestController {
//test emna
    @Autowired
    IBlocService iBlocService;

    @Autowired
    BlocRepository blocRepository;

    @GetMapping("/findAll")
    List<Bloc> findAll(){
        return  iBlocService.findAll();
    }

    @PostMapping("/add")
    Bloc addBloc(@RequestBody Bloc b) {
        return iBlocService.addBloc(b);
    }

    @PutMapping("update/{id}")
    Bloc updateBloc(@PathVariable("id") Long id, @RequestBody Bloc b){
        return iBlocService.editBloc(id, b);
    }

    @DeleteMapping("/delete/{id}")
    void deleteBloc(@PathVariable("id") Long id){
        iBlocService.deleteById(id);
    }

    @GetMapping("/{id}")
    Bloc findById(@PathVariable("id") Long id){
        return iBlocService.findById(id);
    }
    @GetMapping("findByNameBloc")
    List<Bloc> findByNomBloc(@RequestParam String name){
        return blocRepository.findByNomBloc(name);
    }

    @GetMapping("findByNomBlocAndCapaciteBloc")
    List<Bloc> findByNomBlocAndCapaciteBloc(@RequestParam String name,@RequestParam Long capacite){
        return blocRepository.findByNomBlocAndCapaciteBloc(name,capacite);
    }



}
