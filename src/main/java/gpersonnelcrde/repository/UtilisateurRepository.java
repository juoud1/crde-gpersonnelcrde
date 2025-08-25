package gpersonnelcrde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Utilisateur;

@Repository
@Transactional
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
