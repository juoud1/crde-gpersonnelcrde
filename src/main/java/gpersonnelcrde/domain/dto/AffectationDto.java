package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

public class AffectationDto {
	private String categorieAffect;
	private String villeResidence;
	private String paysResidence;
	private String referenceAffect;
	private String emplacementAffect;
	private LocalDate dateDebutAffect;
	private LocalDate dateFinAffect;
	private LocalDate datePriseService;
	private String infoSupplementaires;
	private String numNoteService;
	private String statusAffect;
	private LocalDate dateStatusAffect;
	private String employeMatricule;
	private String employeNom;
	private String employeCivilite;
	private String lieuAffectation;
	private String fonction;
	
	public String getCategorieAffect() {
		return categorieAffect;
	}
	public void setCategorieAffect(String categorieAffect) {
		this.categorieAffect = categorieAffect;
	}
	public String getVilleResidence() {
		return villeResidence;
	}
	public void setVilleResidence(String villeResidence) {
		this.villeResidence = villeResidence;
	}
	public String getPaysResidence() {
		return paysResidence;
	}
	public void setPaysResidence(String paysResidence) {
		this.paysResidence = paysResidence;
	}
	public String getReferenceAffect() {
		return referenceAffect;
	}
	public void setReferenceAffect(String referenceAffect) {
		this.referenceAffect = referenceAffect;
	}
	public String getEmplacementAffect() {
		return emplacementAffect;
	}
	public void setEmplacementAffect(String emplacementAffect) {
		this.emplacementAffect = emplacementAffect;
	}
	public LocalDate getDateDebutAffect() {
		return dateDebutAffect;
	}
	public void setDateDebutAffect(LocalDate dateDebutAffect) {
		this.dateDebutAffect = dateDebutAffect;
	}
	public LocalDate getDateFinAffect() {
		return dateFinAffect;
	}
	public void setDateFinAffect(LocalDate dateFinAffect) {
		this.dateFinAffect = dateFinAffect;
	}
	public LocalDate getDatePriseService() {
		return datePriseService;
	}
	public void setDatePriseService(LocalDate datePriseService) {
		this.datePriseService = datePriseService;
	}
	public String getInfoSupplementaires() {
		return infoSupplementaires;
	}
	public void setInfoSupplementaires(String infoSupplementaires) {
		this.infoSupplementaires = infoSupplementaires;
	}
	public String getNumNoteService() {
		return numNoteService;
	}
	public void setNumNoteService(String numNoteService) {
		this.numNoteService = numNoteService;
	}
	public String getStatusAffect() {
		return statusAffect;
	}
	public void setStatusAffect(String statusAffect) {
		this.statusAffect = statusAffect;
	}
	public LocalDate getDateStatusAffect() {
		return dateStatusAffect;
	}
	public void setDateStatusAffect(LocalDate dateStatusAffect) {
		this.dateStatusAffect = dateStatusAffect;
	}
	public String getEmployeMatricule() {
		return employeMatricule;
	}
	public void setEmployeMatricule(String employeMatricule) {
		this.employeMatricule = employeMatricule;
	}
	public String getEmployeNom() {
		return employeNom;
	}
	public void setEmployeNom(String employeNom) {
		this.employeNom = employeNom;
	}
	public String getEmployeCivilite() {
		return employeCivilite;
	}
	public void setEmployeCivilite(String employeCivilite) {
		this.employeCivilite = employeCivilite;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorieAffect == null) ? 0 : categorieAffect.hashCode());
		result = prime * result + ((villeResidence == null) ? 0 : villeResidence.hashCode());
		result = prime * result + ((paysResidence == null) ? 0 : paysResidence.hashCode());
		result = prime * result + ((referenceAffect == null) ? 0 : referenceAffect.hashCode());
		result = prime * result + ((emplacementAffect == null) ? 0 : emplacementAffect.hashCode());
		result = prime * result + ((dateDebutAffect == null) ? 0 : dateDebutAffect.hashCode());
		result = prime * result + ((dateFinAffect == null) ? 0 : dateFinAffect.hashCode());
		result = prime * result + ((datePriseService == null) ? 0 : datePriseService.hashCode());
		result = prime * result + ((infoSupplementaires == null) ? 0 : infoSupplementaires.hashCode());
		result = prime * result + ((numNoteService == null) ? 0 : numNoteService.hashCode());
		result = prime * result + ((statusAffect == null) ? 0 : statusAffect.hashCode());
		result = prime * result + ((dateStatusAffect == null) ? 0 : dateStatusAffect.hashCode());
		result = prime * result + ((employeMatricule == null) ? 0 : employeMatricule.hashCode());
		result = prime * result + ((employeNom == null) ? 0 : employeNom.hashCode());
		result = prime * result + ((employeCivilite == null) ? 0 : employeCivilite.hashCode());
		result = prime * result + ((lieuAffectation == null) ? 0 : lieuAffectation.hashCode());
		result = prime * result + ((fonction == null) ? 0 : fonction.hashCode());
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
		AffectationDto other = (AffectationDto) obj;
		if (categorieAffect == null) {
			if (other.categorieAffect != null)
				return false;
		} else if (!categorieAffect.equals(other.categorieAffect))
			return false;
		if (villeResidence == null) {
			if (other.villeResidence != null)
				return false;
		} else if (!villeResidence.equals(other.villeResidence))
			return false;
		if (paysResidence == null) {
			if (other.paysResidence != null)
				return false;
		} else if (!paysResidence.equals(other.paysResidence))
			return false;
		if (referenceAffect == null) {
			if (other.referenceAffect != null)
				return false;
		} else if (!referenceAffect.equals(other.referenceAffect))
			return false;
		if (emplacementAffect == null) {
			if (other.emplacementAffect != null)
				return false;
		} else if (!emplacementAffect.equals(other.emplacementAffect))
			return false;
		if (dateDebutAffect == null) {
			if (other.dateDebutAffect != null)
				return false;
		} else if (!dateDebutAffect.equals(other.dateDebutAffect))
			return false;
		if (dateFinAffect == null) {
			if (other.dateFinAffect != null)
				return false;
		} else if (!dateFinAffect.equals(other.dateFinAffect))
			return false;
		if (datePriseService == null) {
			if (other.datePriseService != null)
				return false;
		} else if (!datePriseService.equals(other.datePriseService))
			return false;
		if (infoSupplementaires == null) {
			if (other.infoSupplementaires != null)
				return false;
		} else if (!infoSupplementaires.equals(other.infoSupplementaires))
			return false;
		if (numNoteService == null) {
			if (other.numNoteService != null)
				return false;
		} else if (!numNoteService.equals(other.numNoteService))
			return false;
		if (statusAffect == null) {
			if (other.statusAffect != null)
				return false;
		} else if (!statusAffect.equals(other.statusAffect))
			return false;
		if (dateStatusAffect == null) {
			if (other.dateStatusAffect != null)
				return false;
		} else if (!dateStatusAffect.equals(other.dateStatusAffect))
			return false;
		if (employeMatricule == null) {
			if (other.employeMatricule != null)
				return false;
		} else if (!employeMatricule.equals(other.employeMatricule))
			return false;
		if (employeNom == null) {
			if (other.employeNom != null)
				return false;
		} else if (!employeNom.equals(other.employeNom))
			return false;
		if (employeCivilite == null) {
			if (other.employeCivilite != null)
				return false;
		} else if (!employeCivilite.equals(other.employeCivilite))
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
		return true;
	}
	
	@Override
	public String toString() {
		return "AffectationDto [categorieAffect=" + categorieAffect + ", villeResidence=" + villeResidence
				+ ", paysResidence=" + paysResidence + ", referenceAffect=" + referenceAffect + ", emplacementAffect="
				+ emplacementAffect + ", dateDebutAffect=" + dateDebutAffect + ", dateFinAffect=" + dateFinAffect
				+ ", datePriseService=" + datePriseService + ", infoSupplementaires=" + infoSupplementaires
				+ ", numNoteService=" + numNoteService + ", statusAffect=" + statusAffect + ", dateStatusAffect="
				+ dateStatusAffect + ", employeMatricule=" + employeMatricule + ", employeNom=" + employeNom
				+ ", employeCivilite=" + employeCivilite + ", lieuAffectation=" + lieuAffectation + ", fonction="
				+ fonction + "]";
	}
	
}
