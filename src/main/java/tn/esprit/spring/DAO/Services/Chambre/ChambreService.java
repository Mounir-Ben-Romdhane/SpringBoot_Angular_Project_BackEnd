package tn.esprit.spring.DAO.Services.Chambre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChambreService implements IChambreService {
    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public List<Chambre> addChambres(List<Chambre> chambres) {
        return chambreRepository.saveAll(chambres);
    }

    @Override
    public Chambre editChambre(Long id, Chambre c) {
        if(chambreRepository.findById(id).isPresent()){
            Chambre toUpdateChambre = chambreRepository.findById(id).get();
            toUpdateChambre.setNumeroChambre(c.getNumeroChambre());
            toUpdateChambre.setTypeChambre(c.getTypeChambre());
            toUpdateChambre.setBloc(c.getBloc());
            toUpdateChambre.setReservations(c.getReservations());
            return chambreRepository.save(toUpdateChambre);
        }
        return null;
    }

    @Override
    public List<Chambre> findAll() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre findById(long id) {
        return chambreRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public void delete(Chambre c) {
        chambreRepository.delete(c);
    }

    @Override
    public List<Long> findAllRoomNumbers() {
        // Retrieve all Chambre entities and map them to their numeroChambre
        return chambreRepository.findAll().stream()
                .map(Chambre::getNumeroChambre)
                .distinct() // only unique
                .collect(Collectors.toList());
    }

}
