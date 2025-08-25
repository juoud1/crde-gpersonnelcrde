package gpersonnelcrde.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.LieuAffectationDto;
import gpersonnelcrde.domain.entities.LieuAffectation;
import gpersonnelcrde.repository.LieuAffectationRepository;

@Service
@Transactional
public class LieuAffectationService {
	private LieuAffectationRepository lieuAffectationRepository;

	public LieuAffectationService(LieuAffectationRepository lieuAffectationRepository) {
		this.lieuAffectationRepository = lieuAffectationRepository;
	}

	public List<LieuAffectationDto> getAllLieuAffect() {
		return lieuAffectationRepository.findAll().stream()
				.map((LieuAffectation lAffect) -> {
					var lDto = new LieuAffectationDto();
					lDto.setLieuAffectCode(lAffect.getLieuAffectCode());
					lDto.setLieuAffect(lAffect.getLieuAffect());
					return lDto; 
				})
				.toList();
	}
}
