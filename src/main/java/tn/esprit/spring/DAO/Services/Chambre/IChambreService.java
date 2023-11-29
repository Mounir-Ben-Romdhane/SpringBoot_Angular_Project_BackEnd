package tn.esprit.spring.DAO.Services.Chambre;

import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;

import java.time.LocalDate;
import java.util.List;


public interface IChambreService {
    Chambre addChambre(Chambre b);
    List<Chambre> addChambres(List<Chambre> blocs);
    Chambre editChambre(Long id, Chambre b);
    List<Chambre> findAll();
    Chambre findById(long id);
    void deleteById(long id);
    void delete(Chambre b);
     Chambre findByNumeroChambre(long numeroChambre);
    boolean isChambreOccupeeALaDate(long chambreId, LocalDate date);

    boolean isChambreOccupee(long chambreId);

    Bloc getBlocByChambre(long idChambre);
    boolean isNumeroChambreUnique(long numeroChambre);


    boolean isNumeroChambreUniqueForUpdate(Long id, Long numeroChambre);

    // Get chambre by ID
    Chambre getChambreById(Long idChambre);


    List<Long> findAllRoomNumbers();



}

