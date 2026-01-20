package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

import org.springframework.core.io.Resource;

public class EmployeDto {
	private String empMatricule;
	private String empNom;
	private String empPren;
	private String empCivilite;

	private LocalDate empDateNsce;
	private String empLieuNsce;
	private String empNumActeNsce;

	private String empAdrQuartierResidce;
	private String empAdrVilleResidce;
	private String empAdrPrefResidce;
	private String empAdrRegionResidce;
	private String empTelephone;
	private String empEmail;

	private String typeEmploye;
	private String status;
	private String lieuAffectation;
	private String fonction;
	private String empNumInterne;
	private LocalDate empDateDebutStatus;
	private LocalDate empDateFinStatus;

	private String refDecretouArreteEntree;
	private LocalDate dateDecretouArreteEntree;
	private String refDecretouArreteDepart;
	private LocalDate dateDecretouArreteDepart;

	private Resource empPhoto;
	private String empEmplacementPhoto;
	private Resource empSignature;
	private String empEmplacementSignature;
	
	public String getEmpMatricule() {
		return empMatricule;
	}
	public void setEmpMatricule(String empMatricule) {
		this.empMatricule = empMatricule;
	}
	public String getEmpNom() {
		return empNom;
	}
	public void setEmpNom(String empNom) {
		this.empNom = empNom;
	}
	public String getEmpPren() {
		return empPren;
	}
	public void setEmpPren(String empPren) {
		this.empPren = empPren;
	}
	public String getEmpCivilite() {
		return empCivilite;
	}
	public void setEmpCivilite(String empCivilite) {
		this.empCivilite = empCivilite;
	}
	public LocalDate getEmpDateNsce() {
		return empDateNsce;
	}
	public void setEmpDateNsce(LocalDate empDateNsce) {
		this.empDateNsce = empDateNsce;
	}
	public String getEmpLieuNsce() {
		return empLieuNsce;
	}
	public void setEmpLieuNsce(String empLieuNsce) {
		this.empLieuNsce = empLieuNsce;
	}
	public String getEmpNumActeNsce() {
		return empNumActeNsce;
	}
	public void setEmpNumActeNsce(String empNumActeNsce) {
		this.empNumActeNsce = empNumActeNsce;
	}
	public String getEmpAdrQuartierResidce() {
		return empAdrQuartierResidce;
	}
	public void setEmpAdrQuartierResidce(String empAdrQuartierResidce) {
		this.empAdrQuartierResidce = empAdrQuartierResidce;
	}
	public String getEmpAdrVilleResidce() {
		return empAdrVilleResidce;
	}
	public void setEmpAdrVilleResidce(String empAdrVilleResidce) {
		this.empAdrVilleResidce = empAdrVilleResidce;
	}
	public String getEmpAdrPrefResidce() {
		return empAdrPrefResidce;
	}
	public void setEmpAdrPrefResidce(String empAdrPrefResidce) {
		this.empAdrPrefResidce = empAdrPrefResidce;
	}
	public String getEmpAdrRegionResidce() {
		return empAdrRegionResidce;
	}
	public void setEmpAdrRegionResidce(String empAdrRegionResidce) {
		this.empAdrRegionResidce = empAdrRegionResidce;
	}
	public String getEmpTelephone() {
		return empTelephone;
	}
	public void setEmpTelephone(String empTelephone) {
		this.empTelephone = empTelephone;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getTypeEmploye() {
		return typeEmploye;
	}
	public void setTypeEmploye(String typeEmploye) {
		this.typeEmploye = typeEmploye;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLieuAffectation() {
		return lieuAffectation;
	}
	public void setLieuAffectation(String lieuAffectation) {
		this.lieuAffectation = lieuAffectation;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getEmpNumInterne() {
		return empNumInterne;
	}
	public void setEmpNumInterne(String empNumInterne) {
		this.empNumInterne = empNumInterne;
	}
	public LocalDate getEmpDateDebutStatus() {
		return empDateDebutStatus;
	}
	public void setEmpDateDebutStatus(LocalDate empDateDebutStatus) {
		this.empDateDebutStatus = empDateDebutStatus;
	}
	public LocalDate getEmpDateFinStatus() {
		return empDateFinStatus;
	}
	public void setEmpDateFinStatus(LocalDate empDateFinStatus) {
		this.empDateFinStatus = empDateFinStatus;
	}
	public String getRefDecretouArreteEntree() {
		return refDecretouArreteEntree;
	}
	public void setRefDecretouArreteEntree(String refDecretouArreteEntree) {
		this.refDecretouArreteEntree = refDecretouArreteEntree;
	}
	public LocalDate getDateDecretouArreteEntree() {
		return dateDecretouArreteEntree;
	}
	public void setDateDecretouArreteEntree(LocalDate dateDecretouArreteEntree) {
		this.dateDecretouArreteEntree = dateDecretouArreteEntree;
	}
	public String getRefDecretouArreteDepart() {
		return refDecretouArreteDepart;
	}
	public void setRefDecretouArreteDepart(String refDecretouArreteDepart) {
		this.refDecretouArreteDepart = refDecretouArreteDepart;
	}
	public LocalDate getDateDecretouArreteDepart() {
		return dateDecretouArreteDepart;
	}
	public void setDateDecretouArreteDepart(LocalDate dateDecretouArreteDepart) {
		this.dateDecretouArreteDepart = dateDecretouArreteDepart;
	}
	public Resource getEmpPhoto() {
		return empPhoto;
	}
	public void setEmpPhoto(Resource empPhoto) {
		this.empPhoto = empPhoto;
	}
	public String getEmpEmplacementPhoto() {
		return empEmplacementPhoto;
	}
	public void setEmpEmplacementPhoto(String empEmplacementPhoto) {
		this.empEmplacementPhoto = empEmplacementPhoto;
	}
	public Resource getEmpSignature() {
		return empSignature;
	}
	public void setEmpSignature(Resource empSignature) {
		this.empSignature = empSignature;
	}
	public String getEmpEmplacementSignature() {
		return empEmplacementSignature;
	}
	public void setEmpEmplacementSignature(String empEmplacementSignature) {
		this.empEmplacementSignature = empEmplacementSignature;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empMatricule == null) ? 0 : empMatricule.hashCode());
		result = prime * result + ((empNom == null) ? 0 : empNom.hashCode());
		result = prime * result + ((empPren == null) ? 0 : empPren.hashCode());
		result = prime * result + ((empCivilite == null) ? 0 : empCivilite.hashCode());
		result = prime * result + ((empDateNsce == null) ? 0 : empDateNsce.hashCode());
		result = prime * result + ((empLieuNsce == null) ? 0 : empLieuNsce.hashCode());
		result = prime * result + ((empNumActeNsce == null) ? 0 : empNumActeNsce.hashCode());
		result = prime * result + ((empAdrQuartierResidce == null) ? 0 : empAdrQuartierResidce.hashCode());
		result = prime * result + ((empAdrVilleResidce == null) ? 0 : empAdrVilleResidce.hashCode());
		result = prime * result + ((empAdrPrefResidce == null) ? 0 : empAdrPrefResidce.hashCode());
		result = prime * result + ((empAdrRegionResidce == null) ? 0 : empAdrRegionResidce.hashCode());
		result = prime * result + ((empTelephone == null) ? 0 : empTelephone.hashCode());
		result = prime * result + ((empEmail == null) ? 0 : empEmail.hashCode());
		result = prime * result + ((typeEmploye == null) ? 0 : typeEmploye.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((lieuAffectation == null) ? 0 : lieuAffectation.hashCode());
		result = prime * result + ((fonction == null) ? 0 : fonction.hashCode());
		result = prime * result + ((empNumInterne == null) ? 0 : empNumInterne.hashCode());
		result = prime * result + ((empDateDebutStatus == null) ? 0 : empDateDebutStatus.hashCode());
		result = prime * result + ((empDateFinStatus == null) ? 0 : empDateFinStatus.hashCode());
		result = prime * result + ((refDecretouArreteEntree == null) ? 0 : refDecretouArreteEntree.hashCode());
		result = prime * result + ((dateDecretouArreteEntree == null) ? 0 : dateDecretouArreteEntree.hashCode());
		result = prime * result + ((refDecretouArreteDepart == null) ? 0 : refDecretouArreteDepart.hashCode());
		result = prime * result + ((dateDecretouArreteDepart == null) ? 0 : dateDecretouArreteDepart.hashCode());
		result = prime * result + ((empPhoto == null) ? 0 : empPhoto.hashCode());
		result = prime * result + ((empEmplacementPhoto == null) ? 0 : empEmplacementPhoto.hashCode());
		result = prime * result + ((empSignature == null) ? 0 : empSignature.hashCode());
		result = prime * result + ((empEmplacementSignature == null) ? 0 : empEmplacementSignature.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeDto other = (EmployeDto) obj;
		if (empMatricule == null) {
			if (other.empMatricule != null)
				return false;
		} else if (!empMatricule.equals(other.empMatricule))
			return false;
		if (empNom == null) {
			if (other.empNom != null)
				return false;
		} else if (!empNom.equals(other.empNom))
			return false;
		if (empPren == null) {
			if (other.empPren != null)
				return false;
		} else if (!empPren.equals(other.empPren))
			return false;
		if (empCivilite == null) {
			if (other.empCivilite != null)
				return false;
		} else if (!empCivilite.equals(other.empCivilite))
			return false;
		if (empDateNsce == null) {
			if (other.empDateNsce != null)
				return false;
		} else if (!empDateNsce.equals(other.empDateNsce))
			return false;
		if (empLieuNsce == null) {
			if (other.empLieuNsce != null)
				return false;
		} else if (!empLieuNsce.equals(other.empLieuNsce))
			return false;
		if (empNumActeNsce == null) {
			if (other.empNumActeNsce != null)
				return false;
		} else if (!empNumActeNsce.equals(other.empNumActeNsce))
			return false;
		if (empAdrQuartierResidce == null) {
			if (other.empAdrQuartierResidce != null)
				return false;
		} else if (!empAdrQuartierResidce.equals(other.empAdrQuartierResidce))
			return false;
		if (empAdrVilleResidce == null) {
			if (other.empAdrVilleResidce != null)
				return false;
		} else if (!empAdrVilleResidce.equals(other.empAdrVilleResidce))
			return false;
		if (empAdrPrefResidce == null) {
			if (other.empAdrPrefResidce != null)
				return false;
		} else if (!empAdrPrefResidce.equals(other.empAdrPrefResidce))
			return false;
		if (empAdrRegionResidce == null) {
			if (other.empAdrRegionResidce != null)
				return false;
		} else if (!empAdrRegionResidce.equals(other.empAdrRegionResidce))
			return false;
		if (empTelephone == null) {
			if (other.empTelephone != null)
				return false;
		} else if (!empTelephone.equals(other.empTelephone))
			return false;
		if (empEmail == null) {
			if (other.empEmail != null)
				return false;
		} else if (!empEmail.equals(other.empEmail))
			return false;
		if (typeEmploye == null) {
			if (other.typeEmploye != null)
				return false;
		} else if (!typeEmploye.equals(other.typeEmploye))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (lieuAffectation == null) {
			if (other.lieuAffectation != null)
				return false;
		} else if (!lieuAffectation.equals(other.lieuAffectation))
			return false;
		if (fonction == null) {
			if (other.fonction != null)
				return false;
		} else if (!fonction.equals(other.fonction))
			return false;
		if (empNumInterne == null) {
			if (other.empNumInterne != null)
				return false;
		} else if (!empNumInterne.equals(other.empNumInterne))
			return false;
		if (empDateDebutStatus == null) {
			if (other.empDateDebutStatus != null)
				return false;
		} else if (!empDateDebutStatus.equals(other.empDateDebutStatus))
			return false;
		if (empDateFinStatus == null) {
			if (other.empDateFinStatus != null)
				return false;
		} else if (!empDateFinStatus.equals(other.empDateFinStatus))
			return false;
		if (refDecretouArreteEntree == null) {
			if (other.refDecretouArreteEntree != null)
				return false;
		} else if (!refDecretouArreteEntree.equals(other.refDecretouArreteEntree))
			return false;
		if (dateDecretouArreteEntree == null) {
			if (other.dateDecretouArreteEntree != null)
				return false;
		} else if (!dateDecretouArreteEntree.equals(other.dateDecretouArreteEntree))
			return false;
		if (refDecretouArreteDepart == null) {
			if (other.refDecretouArreteDepart != null)
				return false;
		} else if (!refDecretouArreteDepart.equals(other.refDecretouArreteDepart))
			return false;
		if (dateDecretouArreteDepart == null) {
			if (other.dateDecretouArreteDepart != null)
				return false;
		} else if (!dateDecretouArreteDepart.equals(other.dateDecretouArreteDepart))
			return false;
		if (empPhoto == null) {
			if (other.empPhoto != null)
				return false;
		} else if (!empPhoto.equals(other.empPhoto))
			return false;
		if (empEmplacementPhoto == null) {
			if (other.empEmplacementPhoto != null)
				return false;
		} else if (!empEmplacementPhoto.equals(other.empEmplacementPhoto))
			return false;
		if (empSignature == null) {
			if (other.empSignature != null)
				return false;
		} else if (!empSignature.equals(other.empSignature))
			return false;
		if (empEmplacementSignature == null) {
			if (other.empEmplacementSignature != null)
				return false;
		} else if (!empEmplacementSignature.equals(other.empEmplacementSignature))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeDto [empMatricule=" + empMatricule + ", empNom=" + empNom + ", empPren=" + empPren
				+ ", empCivilite=" + empCivilite + ", empDateNsce=" + empDateNsce + ", empLieuNsce=" + empLieuNsce
				+ ", empNumActeNsce=" + empNumActeNsce + ", empAdrQuartierResidce=" + empAdrQuartierResidce
				+ ", empAdrVilleResidce=" + empAdrVilleResidce + ", empAdrPrefResidce=" + empAdrPrefResidce
				+ ", empAdrRegionResidce=" + empAdrRegionResidce + ", empTelephone=" + empTelephone + ", empEmail="
				+ empEmail + ", typeEmploye=" + typeEmploye + ", status=" + status + ", lieuAffectation="
				+ lieuAffectation + ", fonction=" + fonction + ", empNumInterne=" + empNumInterne
				+ ", empDateDebutStatus=" + empDateDebutStatus + ", empDateFinStatus=" + empDateFinStatus
				+ ", refDecretouArreteEntree=" + refDecretouArreteEntree + ", dateDecretouArreteEntree="
				+ dateDecretouArreteEntree + ", refDecretouArreteDepart=" + refDecretouArreteDepart
				+ ", dateDecretouArreteDepart=" + dateDecretouArreteDepart + ", empPhoto=" + empPhoto
				+ ", empEmplacementPhoto=" + empEmplacementPhoto + ", empSignature=" + empSignature
				+ ", empEmplacementSignature=" + empEmplacementSignature + "]";
	}

}
