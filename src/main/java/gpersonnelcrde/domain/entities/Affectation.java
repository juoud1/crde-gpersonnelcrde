package gpersonnelcrde.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Affectation {
	@Id
   	@GeneratedValue
   	private Long id;
	private String referenceAffect;
	private String EmplacementAffect;
	private LocalDate dateDebutAffect;
	private LocalDate dateFinAffect;
	private LocalDate datePriseService;
	private String infoSupplementaires;

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
	public String getReferenceAffect() {
		return referenceAffect;
	}
	public void setReferenceAffect(String referenceAffect) {
		this.referenceAffect = referenceAffect;
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
	public void setAffectModifieePar(String affectModifiePar) {
		this.affectModifieePar = affectModifiePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((referenceAffect == null) ? 0 : referenceAffect.hashCode());
		result = prime * result + ((EmplacementAffect == null) ? 0 : EmplacementAffect.hashCode());
		result = prime * result + ((dateDebutAffect == null) ? 0 : dateDebutAffect.hashCode());
		result = prime * result + ((dateFinAffect == null) ? 0 : dateFinAffect.hashCode());
		result = prime * result + ((datePriseService == null) ? 0 : datePriseService.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((lieuAffectation == null) ? 0 : lieuAffectation.hashCode());
		result = prime * result + ((fonction == null) ? 0 : fonction.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Affectation other = (Affectation) obj;
		if (referenceAffect == null) {
			if (other.referenceAffect != null)
				return false;
		} else if (!referenceAffect.equals(other.referenceAffect))
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
		return true;
	}
	@Override
	public String toString() {
		return "Affectation [referenceAffect=" + referenceAffect + ", EmplacementAffect=" + EmplacementAffect
				+ ", dateDebutAffect=" + dateDebutAffect + ", dateFinAffect=" + dateFinAffect + ", datePriseService="
				+ datePriseService + ", infoSupplementaires=" + infoSupplementaires + ", employe=" + employe
				+ ", lieuAffectation=" + lieuAffectation + ", fonction=" + fonction + "]";
	}	
}
