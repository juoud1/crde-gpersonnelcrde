package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

public class EmployeDto {
	private String empMatricule;
	private String empNom;
	private String empPren;
	private String empCivilite;
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

	private String empPhoto;
	private String empEmplacementPhoto;
	private String empSignature;
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
	public String getEmpPhoto() {
		return empPhoto;
	}
	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}
	public String getEmpEmplacementPhoto() {
		return empEmplacementPhoto;
	}
	public void setEmpEmplacementPhoto(String empEmplacementPhoto) {
		this.empEmplacementPhoto = empEmplacementPhoto;
	}
	public String getEmpSignature() {
		return empSignature;
	}
	public void setEmpSignature(String empSignature) {
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
		result = prime * result + ((empEmplacementPhoto == null) ? 0 : empEmplacementPhoto.hashCode());
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
		if (empEmplacementPhoto == null) {
			if (other.empEmplacementPhoto != null)
				return false;
		} else if (!empEmplacementPhoto.equals(other.empEmplacementPhoto))
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
				+ ", empCivilite=" + empCivilite + ", empTelephone=" + empTelephone + ", empEmail=" + empEmail
				+ ", typeEmploye=" + typeEmploye + ", status=" + status + ", lieuAffectation=" + lieuAffectation
				+ ", fonction=" + fonction + ", empNumInterne=" + empNumInterne + ", empDateDebutStatus="
				+ empDateDebutStatus + ", empDateFinStatus=" + empDateFinStatus + ", refDecretouArreteEntree="
				+ refDecretouArreteEntree + ", dateDecretouArreteEntree=" + dateDecretouArreteEntree
				+ ", refDecretouArreteDepart=" + refDecretouArreteDepart + ", dateDecretouArreteDepart="
				+ dateDecretouArreteDepart + ", empEmplacementPhoto=" + empEmplacementPhoto
				+ ", empEmplacementSignature=" + empEmplacementSignature + "]";
	}
		
}
