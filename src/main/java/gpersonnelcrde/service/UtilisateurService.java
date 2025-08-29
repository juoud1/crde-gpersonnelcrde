package gpersonnelcrde.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.UtilisateurDto;
import gpersonnelcrde.domain.entities.Utilisateur;
import gpersonnelcrde.repository.UtilisateurRepository;

@Service
@Transactional
public class UtilisateurService {
	private final UtilisateurRepository utilisateurRepository;

	public UtilisateurService(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	public List<UtilisateurDto> getAllUtilisateur() {
		return utilisateurRepository.findAll().stream()
				.map((Utilisateur util) -> {
					var uDto = new UtilisateurDto();
					uDto.setUtilisateurFirstname(util.getUtilisateurFirstname());
					uDto.setUtilisateurLastname(util.getUtilisateurLastname());
					uDto.setUtilisateurEmail(util.getUtilisateurEmail());
					uDto.setUtilisateurPhoneNumber(util.getUtilisateurPhoneNumber());
					uDto.setUtilisateurNomConnexion(util.getUtilisateurNomConnexion());
					uDto.setUtilisateurRole(util.getUtilisateurRole());
					uDto.setUtilisateurStatus(util.getUtilisateurStatus());
					return uDto;
				})
				.toList();
	}
}
