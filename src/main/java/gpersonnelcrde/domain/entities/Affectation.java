package gpersonnelcrde.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Affectation {
	@Id
   	@GeneratedValue(strategy=GenerationType.IDENTITY)
   	private Long id;
	private String referenceAffect;
	private String categorieAffect;
	private String villeResidence;
	private String paysResidence;
	private String EmplacementAffect;
	private LocalDate dateDebutAffect;
	private LocalDate dateFinAffect;
	private LocalDate datePriseService;
	private String infoSupplementaires;
	private String statusAffect;
	private LocalDate dateStatusAffect;
	private String numNoteService;
	
	@JsonIgnore
	@ManyToOne
	private Employe employe;

	@JsonIgnore
	@ManyToOne
	private LieuAffectation lieuAffectation;

	@JsonIgnore
	@ManyToOne
	private Fonction fonction;
	
	private LocalDateTime affectCreeeLe;
	private String affectCreeePar;
	private LocalDateTime affectModifieeLe;
	private String affectModifieePar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReferenceAffect() {
		return referenceAffect;
	}
	public void setReferenceAffect(String referenceAffect) {
		this.referenceAffect = referenceAffect;
	}
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
	public String getEmplacementAffect() {
		return EmplacementAffect;
	}
	public void setEmplacementAffect(String emplacementAffect) {
		EmplacementAffect = emplacementAffect;
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
	public String getNumNoteService() {
		return numNoteService;
	}
	public void setNumNoteService(String numNoteService) {
		this.numNoteService = numNoteService;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public LieuAffectation getLieuAffectation() {
		return lieuAffectation;
	}
	public void setLieuAffectation(LieuAffectation lieuAffectation) {
		this.lieuAffectation = lieuAffectation;
	}
	public Fonction getFonction() {
		return fonction;
	}
	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}
	public LocalDateTime getAffectCreeeLe() {
		return affectCreeeLe;
	}
	public void setAffectCreeeLe(LocalDateTime affectCreeeLe) {
		this.affectCreeeLe = affectCreeeLe;
	}
	public String getAffectCreeePar() {
		return affectCreeePar;
	}
	public void setAffectCreeePar(String affectCreeePar) {
		this.affectCreeePar = affectCreeePar;
	}
	public LocalDateTime getAffectModifieeLe() {
		return affectModifieeLe;
	}
	public void setAffectModifieeLe(LocalDateTime affectModifieeLe) {
		this.affectModifieeLe = affectModifieeLe;
	}
	public String getAffectModifieePar() {
		return affectModifieePar;
	}
	public void setAffectModifieePar(String affectModifieePar) {
		this.affectModifieePar = affectModifieePar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((referenceAffect == null) ? 0 : referenceAffect.hashCode());
		result = prime * result + ((categorieAffect == null) ? 0 : categorieAffect.hashCode());
		result = prime * result + ((villeResidence == null) ? 0 : villeResidence.hashCode());
		result = prime * result + ((paysResidence == null) ? 0 : paysResidence.hashCode());
		result = prime * result + ((EmplacementAffect == null) ? 0 : EmplacementAffect.hashCode());
		result = prime * result + ((dateDebutAffect == null) ? 0 : dateDebutAffect.hashCode());
		result = prime * result + ((dateFinAffect == null) ? 0 : dateFinAffect.hashCode());
		result = prime * result + ((datePriseService == null) ? 0 : datePriseService.hashCode());
		result = prime * result + ((infoSupplementaires == null) ? 0 : infoSupplementaires.hashCode());
		result = prime * result + ((statusAffect == null) ? 0 : statusAffect.hashCode());
		result = prime * result + ((dateStatusAffect == null) ? 0 : dateStatusAffect.hashCode());
		result = prime * result + ((numNoteService == null) ? 0 : numNoteService.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((lieuAffectation == null) ? 0 : lieuAffectation.hashCode());
		result = prime * result + ((fonction == null) ? 0 : fonction.hashCode());
		result = prime * result + ((affectCreeeLe == null) ? 0 : affectCreeeLe.hashCode());
		result = prime * result + ((affectCreeePar == null) ? 0 : affectCreeePar.hashCode());
		result = prime * result + ((affectModifieeLe == null) ? 0 : affectModifieeLe.hashCode());
		result = prime * result + ((affectModifieePar == null) ? 0 : affectModifieePar.hashCode());
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
		Affectation other = (Affectation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (referenceAffect == null) {
			if (other.referenceAffect != null)
				return false;
		} else if (!referenceAffect.equals(other.referenceAffect))
			return false;
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
		if (EmplacementAffect == null) {
			if (other.EmplacementAffect != null)
				return false;
		} else if (!EmplacementAffect.equals(other.EmplacementAffect))
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
		if (numNoteService == null) {
			if (other.numNoteService != null)
				return false;
		} else if (!numNoteService.equals(other.numNoteService))
			return false;
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
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
		if (affectCreeeLe == null) {
			if (other.affectCreeeLe != null)
				return false;
		} else if (!affectCreeeLe.equals(other.affectCreeeLe))
			return false;
		if (affectCreeePar == null) {
			if (other.affectCreeePar != null)
				return false;
		} else if (!affectCreeePar.equals(other.affectCreeePar))
			return false;
		if (affectModifieeLe == null) {
			if (other.affectModifieeLe != null)
				return false;
		} else if (!affectModifieeLe.equals(other.affectModifieeLe))
			return false;
		if (affectModifieePar == null) {
			if (other.affectModifieePar != null)
				return false;
		} else if (!affectModifieePar.equals(other.affectModifieePar))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Affectation [id=" + id + ", referenceAffect=" + referenceAffect + ", categorieAffect=" + categorieAffect
				+ ", villeResidence=" + villeResidence + ", paysResidence=" + paysResidence + ", EmplacementAffect="
				+ EmplacementAffect + ", dateDebutAffect=" + dateDebutAffect + ", dateFinAffect=" + dateFinAffect
				+ ", datePriseService=" + datePriseService + ", infoSupplementaires=" + infoSupplementaires
				+ ", statusAffect=" + statusAffect + ", dateStatusAffect=" + dateStatusAffect + ", numNoteService="
				+ numNoteService + ", employe=" + employe + ", lieuAffectation=" + lieuAffectation + ", fonction="
				+ fonction + ", affectCreeeLe=" + affectCreeeLe + ", affectCreeePar=" + affectCreeePar
				+ ", affectModifieeLe=" + affectModifieeLe + ", affectModifieePar=" + affectModifieePar + "]";
	}

}
