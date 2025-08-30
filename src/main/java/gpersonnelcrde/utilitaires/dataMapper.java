package gpersonnelcrde.utilitaires;

import gpersonnelcrde.domain.dto.FonctionDto;
import gpersonnelcrde.domain.dto.LieuAffectationDto;
import gpersonnelcrde.domain.dto.StatusDto;
import gpersonnelcrde.domain.dto.TypeEmployeDto;
import gpersonnelcrde.domain.entities.Fonction;
import gpersonnelcrde.domain.entities.LieuAffectation;
import gpersonnelcrde.domain.entities.Status;
import gpersonnelcrde.domain.entities.TypeEmploye;

public final class dataMapper {
	private dataMapper(){}

	public static TypeEmploye typeEmployeDto (TypeEmployeDto typeEmployeDto) {
		var typeEmploye = new TypeEmploye();
		typeEmploye.setTypeEmpCode(typeEmployeDto.getTypeEmpCode());
		typeEmploye.setTypeEmp(typeEmploye.getTypeEmp()); 

		return typeEmploye;
	}

	public static Fonction fonctionDtoMapper(FonctionDto fonctionDto) {
		var fonction = new Fonction();
		fonction.setFonctionCode(fonctionDto.getFonctionCode());
		fonction.setFonction(fonctionDto.getFonction());

		return fonction;
	}

	public static LieuAffectation lieuAffectationDtoMapper(LieuAffectationDto lieuAffectationDto) {
		var lieuAffectation = new LieuAffectation();
		lieuAffectation.setLieuAffectCode(lieuAffectationDto.getLieuAffectCode());
		lieuAffectation.setLieuAffect(lieuAffectationDto.getLieuAffect());

		return lieuAffectation;	
	}

	public static Status statusDtoMapper (StatusDto statusDto) {
		var status = new Status();
		status.setStatusCode(statusDto.getStatusCode());
		status.setStatus(statusDto.getStatus());

		return status;
	}
}
