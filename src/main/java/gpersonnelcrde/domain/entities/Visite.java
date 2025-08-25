package gpersonnelcrde.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Visite {
	@Id
   	@GeneratedValue
   	private Long id;
	private String civiliteVisiteur;
	private String nomVisiteur;
	private String prenomVisiteur;
	private String fonctionVisiteur;
	private String paysVisiteur;
	private Integer duree;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String butVisite;
	private LocalDateTime visiteCreeeLe;
	private String visiteCreeePar;
	private LocalDateTime visiteModifieeLe;
	private String visiteModifieePar;
	public String getCiviliteVisiteur() {
		return civiliteVisiteur;
	}
	public void setCiviliteVisiteur(String civiliteVisiteur) {
		this.civiliteVisiteur = civiliteVisiteur;
	}
	public String getNomVisiteur() {
		return nomVisiteur;
	}
	public void setNomVisiteur(String nomVisiteur) {
		this.nomVisiteur = nomVisiteur;
	}
	public String getPrenomVisiteur() {
		return prenomVisiteur;
	}
	public void setPrenomVisiteur(String prenomVisiteur) {
		this.prenomVisiteur = prenomVisiteur;
	}
	public String getFonctionVisiteur() {
		return fonctionVisiteur;
	}
	public void setFonctionVisiteur(String fonctionVisiteur) {
		this.fonctionVisiteur = fonctionVisiteur;
	}
	public String getPaysVisiteur() {
		return paysVisiteur;
	}
	public void setPaysVisiteur(String paysVisiteur) {
		this.paysVisiteur = paysVisiteur;
	}
	public Integer getDuree() {
		return duree;
	}
	public void setDuree(Integer duree) {
		this.duree = duree;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	public String getButVisite() {
		return butVisite;
	}
	public void setButVisite(String butVisite) {
		this.butVisite = butVisite;
	}
	public LocalDateTime getVisiteCreeeLe() {
		return visiteCreeeLe;
	}
	public void setVisiteCreeeLe(LocalDateTime visiteCreeeLe) {
		this.visiteCreeeLe = visiteCreeeLe;
	}
	public String getVisiteCreeePar() {
		return visiteCreeePar;
	}
	public void setVisiteCreeePar(String visiteCreeePar) {
		this.visiteCreeePar = visiteCreeePar;
	}
	public LocalDateTime getVisiteModifieeLe() {
		return visiteModifieeLe;
	}
	public void setVisiteModifieeLe(LocalDateTime visiteModifieeLe) {
		this.visiteModifieeLe = visiteModifieeLe;
	}
	public String getVisiteModifieePar() {
		return visiteModifieePar;
	}
	public void setVisiteModifieePar(String visiteModifieePar) {
		this.visiteModifieePar = visiteModifieePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((civiliteVisiteur == null) ? 0 : civiliteVisiteur.hashCode());
		result = prime * result + ((nomVisiteur == null) ? 0 : nomVisiteur.hashCode());
		result = prime * result + ((prenomVisiteur == null) ? 0 : prenomVisiteur.hashCode());
		result = prime * result + ((fonctionVisiteur == null) ? 0 : fonctionVisiteur.hashCode());
		result = prime * result + ((paysVisiteur == null) ? 0 : paysVisiteur.hashCode());
		result = prime * result + ((duree == null) ? 0 : duree.hashCode());
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((butVisite == null) ? 0 : butVisite.hashCode());
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
		Visite other = (Visite) obj;
		if (civiliteVisiteur == null) {
			if (other.civiliteVisiteur != null)
				return false;
		} else if (!civiliteVisiteur.equals(other.civiliteVisiteur))
			return false;
		if (nomVisiteur == null) {
			if (other.nomVisiteur != null)
				return false;
		} else if (!nomVisiteur.equals(other.nomVisiteur))
			return false;
		if (prenomVisiteur == null) {
			if (other.prenomVisiteur != null)
				return false;
		} else if (!prenomVisiteur.equals(other.prenomVisiteur))
			return false;
		if (fonctionVisiteur == null) {
			if (other.fonctionVisiteur != null)
				return false;
		} else if (!fonctionVisiteur.equals(other.fonctionVisiteur))
			return false;
		if (paysVisiteur == null) {
			if (other.paysVisiteur != null)
				return false;
		} else if (!paysVisiteur.equals(other.paysVisiteur))
			return false;
		if (duree == null) {
			if (other.duree != null)
				return false;
		} else if (!duree.equals(other.duree))
			return false;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (butVisite == null) {
			if (other.butVisite != null)
				return false;
		} else if (!butVisite.equals(other.butVisite))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Visite [civiliteVisiteur=" + civiliteVisiteur + ", nomVisiteur=" + nomVisiteur + ", prenomVisiteur="
				+ prenomVisiteur + ", fonctionVisiteur=" + fonctionVisiteur + ", paysVisiteur=" + paysVisiteur
				+ ", duree=" + duree + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", butVisite=" + butVisite
				+ "]";
	}		
}
