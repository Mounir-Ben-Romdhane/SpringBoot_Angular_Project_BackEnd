package tn.esprit.spring.DAO.Services.Foyer;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
import tn.esprit.spring.DAO.Repositories.ReservationRepository;
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoyerService implements IFoyerService{
    @Autowired
    private FoyerRepository foyerRepository;
    @Autowired
    private UniversiteRepository universiteRepository;



    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public Foyer addFoyer(Foyer f, long IdUniversite) {
        Universite u=universiteRepository.findById(IdUniversite).get();
        f.setUniversite(u);

        return foyerRepository.save(f);
    }

    @Override
    public List<Foyer> addFoyers(List<Foyer> foyers) {
        return foyerRepository.saveAll(foyers);
    }

    @Override
    public Foyer editFoyer(Long id, Foyer f) {
        if(foyerRepository.findById(id).isPresent()){
            Foyer toUpdateFoyer = foyerRepository.findById(id).get();
            toUpdateFoyer.setNomFoyer(f.getNomFoyer());
            toUpdateFoyer.setCapaciteFoyer(f.getCapaciteFoyer());
            toUpdateFoyer.setBlocs(f.getBlocs());
            toUpdateFoyer.setUniversite(f.getUniversite());

            return foyerRepository.save(toUpdateFoyer);
        }
        return null;
    }


    @Override
    public List<Foyer> findAll() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer findById(long id) {
        return foyerRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        foyerRepository.deleteById(id);
    }

    @Override
    public void delete(Foyer e) {
        foyerRepository.delete(e);
    }

    public List<Foyer> searchFoyersByNomFoyer(String nomFoyer) {
        return foyerRepository.findByNomFoyerContainingIgnoreCase(nomFoyer);
    }

    public Long countChambresByFoyerId(Long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);

        if (foyer != null) {
            return foyer.getBlocs().stream()
                    .mapToLong(bloc -> bloc.getChambres().size())
                    .sum();
        }

        return 0L; // ou retournez null selon vos besoins
    }

    @Override
    public List<Universite> findAllUniversite() {
        List<Universite>listeU=new ArrayList<>();
        for (Universite u:universiteRepository.findAll()){
            if(u.getFoyer()==null)
                listeU.add(u);
        }
        return listeU;
    }

    @Override
    public List<Foyer> findByUniversiteNamee(String nomUniversite) {
      return  foyerRepository.findByUniversiteNomUniversiteIgnoreCase(nomUniversite);    }

    public Universite getUniversiteByFoyer(long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new EntityNotFoundException("Chambre not found"));

        return foyer.getUniversite();
    }

}