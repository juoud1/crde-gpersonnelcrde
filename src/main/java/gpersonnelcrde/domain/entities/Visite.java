package gpersonnelcrde.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//@Table(schema = "crde", name = "visite")
public class Visite implements Comparable<Visite> {
	@Id
   	@GeneratedValue(strategy=GenerationType.IDENTITY)
   	private Long id;
	private String civiliteVisiteur;
	private String nomVisiteur;
	private String prenomVisiteur;
	private String fonctionVisiteur;
	private String paysVisiteur;
	private Integer dureeVisite;
	private LocalDate dateDebutVisite;
	private LocalDate dateFinVisite;
	private String butVisite;
	
	@JsonIgnore
	@ManyToOne
	private Employe employe;
	
	private String statusVisite;
	private LocalDate dateStatusVisite;
	private LocalDateTime visiteCreeeLe;
	private String visiteCreeePar;
	private LocalDateTime visiteModifieeLe;
	private String visiteModifieePar;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Integer getDureeVisite() {
		return dureeVisite;
	}
	public void setDureeVisite(Integer dureeVisite) {
		this.dureeVisite = dureeVisite;
	}
	public LocalDate getDateDebutVisite() {
		return dateDebutVisite;
	}
	public void setDateDebutVisite(LocalDate dateDebutVisite) {
		this.dateDebutVisite = dateDebutVisite;
	}
	public LocalDate getDateFinVisite() {
		return dateFinVisite;
	}
	public void setDateFinVisite(LocalDate dateFinVisite) {
		this.dateFinVisite = dateFinVisite;
	}
	public String getButVisite() {
		return butVisite;
	}
	public void setButVisite(String butVisite) {
		this.butVisite = butVisite;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public String getStatusVisite() {
		return statusVisite;
	}
	public void setStatusVisite(String statusVisite) {
		this.statusVisite = statusVisite;
	}
	public LocalDate getDateStatusVisite() {
		return dateStatusVisite;
	}
	public void setDateStatusVisite(LocalDate dateStatusVisite) {
		this.dateStatusVisite = dateStatusVisite;
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
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((civiliteVisiteur == null) ? 0 : civiliteVisiteur.hashCode());
		result = prime * result + ((nomVisiteur == null) ? 0 : nomVisiteur.hashCode());
		result = prime * result + ((prenomVisiteur == null) ? 0 : prenomVisiteur.hashCode());
		result = prime * result + ((fonctionVisiteur == null) ? 0 : fonctionVisiteur.hashCode());
		result = prime * result + ((paysVisiteur == null) ? 0 : paysVisiteur.hashCode());
		result = prime * result + ((dureeVisite == null) ? 0 : dureeVisite.hashCode());
		result = prime * result + ((dateDebutVisite == null) ? 0 : dateDebutVisite.hashCode());
		result = prime * result + ((dateFinVisite == null) ? 0 : dateFinVisite.hashCode());
		result = prime * result + ((butVisite == null) ? 0 : butVisite.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((statusVisite == null) ? 0 : statusVisite.hashCode());
		result = prime * result + ((dateStatusVisite == null) ? 0 : dateStatusVisite.hashCode());
		result = prime * result + ((visiteCreeeLe == null) ? 0 : visiteCreeeLe.hashCode());
		result = prime * result + ((visiteCreeePar == null) ? 0 : visiteCreeePar.hashCode());
		result = prime * result + ((visiteModifieeLe == null) ? 0 : visiteModifieeLe.hashCode());
		result = prime * result + ((visiteModifieePar == null) ? 0 : visiteModifieePar.hashCode());
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
		Visite other = (Visite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
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
		if (dureeVisite == null) {
			if (other.dureeVisite != null)
				return false;
		} else if (!dureeVisite.equals(other.dureeVisite))
			return false;
		if (dateDebutVisite == null) {
			if (other.dateDebutVisite != null)
				return false;
		} else if (!dateDebutVisite.equals(other.dateDebutVisite))
			return false;
		if (dateFinVisite == null) {
			if (other.dateFinVisite != null)
				return false;
		} else if (!dateFinVisite.equals(other.dateFinVisite))
			return false;
		if (butVisite == null) {
			if (other.butVisite != null)
				return false;
		} else if (!butVisite.equals(other.butVisite))
			return false;
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		if (statusVisite == null) {
			if (other.statusVisite != null)
				return false;
		} else if (!statusVisite.equals(other.statusVisite))
			return false;
		if (dateStatusVisite == null) {
			if (other.dateStatusVisite != null)
				return false;
		} else if (!dateStatusVisite.equals(other.dateStatusVisite))
			return false;
		if (visiteCreeeLe == null) {
			if (other.visiteCreeeLe != null)
				return false;
		} else if (!visiteCreeeLe.equals(other.visiteCreeeLe))
			return false;
		if (visiteCreeePar == null) {
			if (other.visiteCreeePar != null)
				return false;
		} else if (!visiteCreeePar.equals(other.visiteCreeePar))
			return false;
		if (visiteModifieeLe == null) {
			if (other.visiteModifieeLe != null)
				return false;
		} else if (!visiteModifieeLe.equals(other.visiteModifieeLe))
			return false;
		if (visiteModifieePar == null) {
			if (other.visiteModifieePar != null)
				return false;
		} else if (!visiteModifieePar.equals(other.visiteModifieePar))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Visite [id=" + id + ", civiliteVisiteur=" + civiliteVisiteur + ", nomVisiteur=" + nomVisiteur
				+ ", prenomVisiteur=" + prenomVisiteur + ", fonctionVisiteur=" + fonctionVisiteur + ", paysVisiteur="
				+ paysVisiteur + ", dureeVisite=" + dureeVisite + ", dateDebutVisite=" + dateDebutVisite
				+ ", dateFinVisite=" + dateFinVisite + ", butVisite=" + butVisite + ", employe=" + employe
				+ ", statusVisite=" + statusVisite + ", dateStatusVisite=" + dateStatusVisite + ", visiteCreeeLe="
				+ visiteCreeeLe + ", visiteCreeePar=" + visiteCreeePar + ", visiteModifieeLe=" + visiteModifieeLe
				+ ", visiteModifieePar=" + visiteModifieePar + "]";
	}
	@Override
	public int compareTo(Visite o) {
		return o.getId().compareTo(id);
	}
	
}
