package tn.esprit.spring.DAO.Services.Bloc;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Repositories.BlocRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocService implements IBlocService{

    @Autowired
    private BlocRepository blocRepository;
    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public List<Bloc> addBlocs(List<Bloc> blocs) {
        return blocRepository.saveAll(blocs);
    }


    @Override
    public Bloc editBloc(Long id,Bloc b)
    {
        if(blocRepository.findById(id).isPresent()){
            Bloc toUpdateBloc = blocRepository.findById(id).get();
            toUpdateBloc.setNomBloc(b.getNomBloc());
            toUpdateBloc.setCapaciteBloc(b.getCapaciteBloc());
            toUpdateBloc.setFoyer(b.getFoyer());
            toUpdateBloc.setChambres(b.getChambres());
            return blocRepository.save(toUpdateBloc);
        }
        return null;
    }

    @Override
    public List<Bloc> findAll() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc findById(long id) {
        return blocRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        blocRepository.deleteById(id);
    }

    @Override
    public void delete(Bloc b) {
        blocRepository.delete(b);
    }
}
