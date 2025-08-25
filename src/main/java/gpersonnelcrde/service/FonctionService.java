package gpersonnelcrde.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.FonctionDto;
import gpersonnelcrde.domain.entities.Fonction;
import gpersonnelcrde.repository.FonctionRepository;

@Service
@Transactional
public class FonctionService {
	private FonctionRepository fonctionRepository;

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
}
