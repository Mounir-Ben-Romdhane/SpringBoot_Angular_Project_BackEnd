package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.DAO.Entities.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
    select t from Token t inner join Etudiant e on t.etudiant.idEtudiant = e.idEtudiant
    where e.idEtudiant = :id and (t.expired = false or t.revoked = false)
    """)
    List<Token> findAllValidTokensByEtudiant(Integer id);

    Optional<Token> findByToken(String token);
}
