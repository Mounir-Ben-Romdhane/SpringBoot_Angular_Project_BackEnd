package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.TypeChambre;

import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    // 1- Recherche par numéro de chambre
    Chambre findByNumeroChambre(long numeroChambre);

    // 2- Recherche par type de chambre
    List<Chambre> findByTypeChambre(TypeChambre typeChambre);

    // 3- Recherche des chambres par bloc
    List<Chambre> findByBloc(Bloc bloc);

    // 4- Recherche des chambres par bloc et type de chambre
    List<Chambre> findByBlocAndTypeChambre(Bloc bloc, TypeChambre typeChambre);

    // 5- Recherche des chambres par numéro de chambre et type de chambre
    List<Chambre> findByNumeroChambreAndTypeChambre(long numeroChambre, TypeChambre typeChambre);


    //Recherche par numero de chambre
    @Query("select c from Chambre c where c.numeroChambre=?1")
    List<Chambre> selectByNum(Long num);

    @Query("select c from Chambre c where c.numeroChambre=:num")
    List<Chambre> selectByNum2(@Param(value = "num") long num);

    @Query(value = "select * from chambre where numeroChambre=?1 ",nativeQuery = true)
    List<Chambre> selectByNumSQL(long num);

    @Query("SELECT c FROM Chambre c LEFT JOIN c.reservations r WHERE r IS NULL OR r.status <> 'ACTIVE'")
    List<Chambre> findUnreservedRooms();

}
