package tn.esprit.spring.DAO.Services.Etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;

import java.util.List;

@Service
public class EtudiantService implements IEtudiantService{
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {
        return etudiantRepository.saveAll(etudiants);
    }

    @Override
    public Etudiant editEtudiant(Long id, Etudiant e) {
        if(etudiantRepository.findById(id).isPresent()){
            Etudiant toUpdateEtudiant = etudiantRepository.findById(id).get();
            toUpdateEtudiant.setNomEt(e.getNomEt());
            toUpdateEtudiant.setPrenomEt(e.getPrenomEt());
            toUpdateEtudiant.setCin(e.getCin());
            toUpdateEtudiant.setEcole(e.getEcole());
            toUpdateEtudiant.setDateNaissance(e.getDateNaissance());
            toUpdateEtudiant.setReservations(e.getReservations());

            return etudiantRepository.save(toUpdateEtudiant);
        }
        return null;
    }

    @Override
    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant findById(long id) {
        return etudiantRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public void delete(Etudiant e) {
        etudiantRepository.delete(e);
    }

    public List<Long> findAllCINs(){
        return etudiantRepository.findAllCINs();
    }


}
