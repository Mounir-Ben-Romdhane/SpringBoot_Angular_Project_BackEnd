package tn.esprit.spring.DAO.Services.Chambre;

import tn.esprit.spring.DAO.Entities.Chambre;

import java.util.List;


public interface IChambreService {
    Chambre addChambre(Chambre b);

    List<Chambre> addChambres(List<Chambre> blocs);
    Chambre editChambre(Long id, Chambre b);
    List<Chambre> findAll();
    Chambre findById(long id);
    void deleteById(long id);
    void delete(Chambre b);
}

