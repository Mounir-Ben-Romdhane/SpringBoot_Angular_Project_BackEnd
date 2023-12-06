package tn.esprit.spring.DAO.Services.Foyer;

import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;

import java.util.List;

public interface IFoyerService {
    Foyer addFoyer(Foyer f ,long IdUniversite);

    List<Foyer> addFoyers(List<Foyer> foyers);
   // Foyer editFoyer(Long id, Foyer f);
   Foyer editFoyer(Long id, Foyer updatedFoyer);
    List<Foyer> findAll();
    Foyer findById(long id);
    void deleteById(long id);
    void delete(Foyer f);

    Long countChambresByFoyerId(Long id);
    List<Universite>findAllUniversite();

    // New method to find foyers by the name of the university
    List<Foyer> findByUniversiteNamee(String nomUniversite);


    Universite getUniversiteByFoyer (long idFoyer);
}

