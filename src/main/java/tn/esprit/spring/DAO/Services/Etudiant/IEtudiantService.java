package tn.esprit.spring.DAO.Services.Etudiant;

import tn.esprit.spring.DAO.Entities.Etudiant;

import java.util.List;

public interface IEtudiantService {
    Etudiant addEtudiant(Etudiant b);

    List<Etudiant> addEtudiants(List<Etudiant> blocs);
    Etudiant editEtudiant(Long id, Etudiant b);
    List<Etudiant> findAll();
    Etudiant findById(long id);
    void deleteById(long id);
    void delete(Etudiant b);

    List<Long> findUnreservedCinUsers();
}
