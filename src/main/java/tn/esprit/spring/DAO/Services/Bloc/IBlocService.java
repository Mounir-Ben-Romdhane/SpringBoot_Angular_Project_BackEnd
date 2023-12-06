package tn.esprit.spring.DAO.Services.Bloc;

import tn.esprit.spring.DAO.Entities.Bloc;

import java.util.List;

public interface IBlocService {
    Bloc addBloc(Bloc b);

    List<Bloc> addBlocs(List<Bloc> blocs);
    Bloc editBloc(Long id, Bloc b);
    List<Bloc> findAll();
    Bloc findById(long id);
    void deleteById(long id);
    void delete(Bloc b);
    Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc) ;
    Bloc affecterBlocAFoyer( String nomBloc, String nomFoyer) ;

     Bloc findById(Long idBloc) ;

    }





