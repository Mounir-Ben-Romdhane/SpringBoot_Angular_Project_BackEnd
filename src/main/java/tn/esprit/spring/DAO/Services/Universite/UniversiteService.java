package tn.esprit.spring.DAO.Services.Universite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;
import tn.esprit.spring.DAO.Services.Foyer.FoyerService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UniversiteService implements IUniversiteService{
    @Autowired
    private UniversiteRepository universiteRepository;


    @Override
    public Universite addUniversite(Universite u) {


       Foyer idFoyer = u.getFoyer();
        u.setFoyer(idFoyer);
        return universiteRepository.save(u);

    }

    @Override
    public List<Universite> addUniversites(List<Universite> universites) {
        return universiteRepository.saveAll(universites);
    }

    @Override
    public Universite editUniversite(Long id, Universite u) {
        if(universiteRepository.findById(id).isPresent()){
            Universite toUpdateUniversite = universiteRepository.findById(id).get();
            toUpdateUniversite.setNomUniversite(u.getNomUniversite());
            toUpdateUniversite.setAdresse(u.getAdresse());
            toUpdateUniversite.setFoyer(u.getFoyer());

            return universiteRepository.save(toUpdateUniversite);
        }
        return null;
    }

    @Override
    public List<Universite> findAll() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite findById(long id) {
        return universiteRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        universiteRepository.deleteById(id);
    }

    @Override
    public void delete(Universite e) {
        universiteRepository.delete(e);
    }

    @Override
    public Universite getByNomUniverst(String nomUniversite) {
        return universiteRepository.findByNomUniversite(nomUniversite);
    }

    @Override
    public Universite getUniversiteByNomFoyer(String nomFoyer) {
        return universiteRepository.findUniversiteByFoyer_NomFoyer(nomFoyer);
    }

    @Override
    public List<Universite> getByAdresse(String adresse) {
        return universiteRepository.selectByAdresse(adresse);
    }

    @Override
    public Long getNombreTotalChambresByNomUniversite(String nomUniversite) {
        return universiteRepository.countChambresByNomUniversite(nomUniversite);
    }

    @Override
    public List<Universite> getByNombreMinChambres(int nombreMinChambres) {
        return universiteRepository.findByNombreMinChambres(nombreMinChambres);
    }


}
