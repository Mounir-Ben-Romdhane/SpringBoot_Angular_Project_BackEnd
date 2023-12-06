package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.DAO.Entities.Universite;

import java.util.List;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {


     Universite findUniversiteByFoyer_NomFoyer(String nomFoyer);
     Universite findByNomUniversite(String nomUniversite);

     @Query("select u from Universite u where u.adresse=?1")
     List<Universite> selectByAdresse(String adress);

     // calculer le nombre total de chambres pour une université spécifique
     @Query("select count (c) from Chambre c where c.bloc.foyer.universite.nomUniversite = :nomUniversite")
     Long countChambresByNomUniversite(String nomUniversite);

     // récupère les universités qui ont un nombre de chambres supérieur ou égal à la valeur spécifiée (nombreMinChambres
     @Query("select u from Universite u join u.foyer f join f.blocs b join b.chambres c  group by u.idUniversite having count (c) >= :nombreMinChambres")
     List<Universite> findByNombreMinChambres(int nombreMinChambres);}
    Universite findByNomUniversite(String nomUniversite);
    Universite findByIdUniversite(long id);
}
