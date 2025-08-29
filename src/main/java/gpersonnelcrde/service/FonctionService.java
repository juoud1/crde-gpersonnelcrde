package gpersonnelcrde.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.FonctionDto;
import gpersonnelcrde.domain.entities.Fonction;
import gpersonnelcrde.repository.FonctionRepository;

@Service
@Transactional
public class FonctionService {
	private final FonctionRepository fonctionRepository;

	public FonctionService(FonctionRepository fonctionRepository) {
		this.fonctionRepository = fonctionRepository;
	}

	public List<FonctionDto> getAllFonction(){
		return fonctionRepository.findAll().stream()
				.map((Fonction fonct) -> {
					FonctionDto fDto = new FonctionDto();
					fDto.setFonctionCode(fonct.getFonctionCode());
					fDto.setFonction(fonct.getFonction());
					return fDto;
				})
				.toList();
	}

	public Optional<FonctionDto> getFonctionByCode(String fonctionCode){
		if (StringUtils.isBlank(fonctionCode)){
			return Optional.empty();
		}
		var optionalFonct = fonctionRepository.findByFonctionCode(fonctionCode);

		return fonctionMapper(optionalFonct);
	}

	private Optional<FonctionDto> fonctionMapper(Optional<Fonction> optFonction){
		if (optFonction.isPresent()){
			var fonct = optFonction.get();
			var fDto = new FonctionDto();
			fDto.setFonctionCode(fonct.getFonctionCode());
			fDto.setFonction(fonct.getFonction());

			return Optional.of(fDto);
		}

		return Optional.empty();
	}
}
