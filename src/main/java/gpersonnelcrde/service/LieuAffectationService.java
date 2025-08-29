package gpersonnelcrde.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.LieuAffectationDto;
import gpersonnelcrde.domain.entities.LieuAffectation;
import gpersonnelcrde.repository.LieuAffectationRepository;

@Service
@Transactional
public class LieuAffectationService {
	private final LieuAffectationRepository lieuAffectationRepository;

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

	public Optional<LieuAffectationDto> getLieuAffectByCode(String lieuAffectCode){
		if (StringUtils.isBlank(lieuAffectCode)){
			return Optional.empty();
		}
		var optionalLieuAffect = lieuAffectationRepository.findByLieuAffectCode(lieuAffectCode);

		return lieuAffectMapper(optionalLieuAffect); 
	}

	private Optional<LieuAffectationDto> lieuAffectMapper(Optional<LieuAffectation> optLieuAffect){
		if (optLieuAffect.isPresent()){
			var lAffect = optLieuAffect.get();
			var lDto = new LieuAffectationDto();
			lDto.setLieuAffectCode(lAffect.getLieuAffectCode());
			lDto.setLieuAffect(lAffect.getLieuAffect());

			return Optional.of(lDto);
		}
		return Optional.empty();
	}
}
