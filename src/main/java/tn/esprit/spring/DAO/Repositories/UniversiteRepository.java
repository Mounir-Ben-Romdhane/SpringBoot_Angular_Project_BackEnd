package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Universite;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {
}
