package tn.esprit.spring.DAO.Services.Universite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;

import java.util.List;

@Service
public class UniversiteService implements IUniversiteService{
    @Autowired
    private UniversiteRepository universiteRepository;
    @Autowired
    private FoyerRepository foyerRepository;

    @Override
    public Universite addUniversite(Universite u) {
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
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);

       universite.setFoyer(foyer);
        universiteRepository.save(universite);

        return universite;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).get();
        universite.setFoyer(null);
        universiteRepository.save(universite);
        return universite;
    }
}
