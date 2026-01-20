package gpersonnelcrde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.AdresseEmploye;
import gpersonnelcrde.domain.entities.Employe;

@Repository
@Transactional
public interface AdresseEmployeRepository extends JpaRepository<AdresseEmploye, Long> {
	List<AdresseEmploye> findByEmploye(Employe employe);
	List<AdresseEmploye> findByEmployeAndEstAdresseActive(Employe employe, boolean estAdresseActive);
	List<AdresseEmploye> findByVilleResidence(String villeResidence);
	List<AdresseEmploye> findByPrefectureResidence(String prefectureResidence);
	List<AdresseEmploye> findByRegionResidence(String regionResidence);
	List<AdresseEmploye> findByVilleResidenceAndPrefectureResidence(String villeResidence, String prefectureResidence);
	List<AdresseEmploye> findByPrefectureResidenceAndRegionResidence(String prefectureResidence, String regionResidence);
	List<AdresseEmploye> findByVilleResidenceAndPrefectureResidenceAndRegionResidence(String villeResidence, String prefectureResidence, String regionResidence);
}
