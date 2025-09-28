package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

public class VisiteDto {
	private String numVisite;
	private String civiliteVisiteur;
	private String nomVisiteur;
	private String prenomVisiteur;
	private String fonctionVisiteur;
	private String paysVisiteur;
	private Integer dureeVisite;
	private LocalDate dateDebutVisite;
	private LocalDate dateFinVisite;
	private String butVisite;
	private String statusVisite;
	private LocalDate dateStatusVisite;
	private String employeSolliteMatricule;
	private String employeSolliteNom;
	private String employeSolliteCivilite;
	private String employeSolliteFonction;

	public String getNumVisite() {
		return numVisite;
	}
	public void setNumVisite(String numVisite) {
		this.numVisite = numVisite;
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
	public String getEmployeSolliteMatricule() {
		return employeSolliteMatricule;
	}
	public void setEmployeSolliteMatricule(String employeSolliteMatricule) {
		this.employeSolliteMatricule = employeSolliteMatricule;
	}
	public String getEmployeSolliteNom() {
		return employeSolliteNom;
	}
	public void setEmployeSolliteNom(String employeSolliteNom) {
		this.employeSolliteNom = employeSolliteNom;
	}
	public String getEmployeSolliteCivilite() {
		return employeSolliteCivilite;
	}
	public void setEmployeSolliteCivilite(String employeSolliteCivilite) {
		this.employeSolliteCivilite = employeSolliteCivilite;
	}
	public String getEmployeSolliteFonction() {
		return employeSolliteFonction;
	}
	public void setEmployeSolliteFonction(String employeSolliteFonction) {
		this.employeSolliteFonction = employeSolliteFonction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numVisite == null) ? 0 : numVisite.hashCode());
		result = prime * result + ((civiliteVisiteur == null) ? 0 : civiliteVisiteur.hashCode());
		result = prime * result + ((nomVisiteur == null) ? 0 : nomVisiteur.hashCode());
		result = prime * result + ((prenomVisiteur == null) ? 0 : prenomVisiteur.hashCode());
		result = prime * result + ((fonctionVisiteur == null) ? 0 : fonctionVisiteur.hashCode());
		result = prime * result + ((paysVisiteur == null) ? 0 : paysVisiteur.hashCode());
		result = prime * result + ((dureeVisite == null) ? 0 : dureeVisite.hashCode());
		result = prime * result + ((dateDebutVisite == null) ? 0 : dateDebutVisite.hashCode());
		result = prime * result + ((dateFinVisite == null) ? 0 : dateFinVisite.hashCode());
		result = prime * result + ((butVisite == null) ? 0 : butVisite.hashCode());
		result = prime * result + ((statusVisite == null) ? 0 : statusVisite.hashCode());
		result = prime * result + ((dateStatusVisite == null) ? 0 : dateStatusVisite.hashCode());
		result = prime * result + ((employeSolliteMatricule == null) ? 0 : employeSolliteMatricule.hashCode());
		result = prime * result + ((employeSolliteNom == null) ? 0 : employeSolliteNom.hashCode());
		result = prime * result + ((employeSolliteCivilite == null) ? 0 : employeSolliteCivilite.hashCode());
		result = prime * result + ((employeSolliteFonction == null) ? 0 : employeSolliteFonction.hashCode());
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
		VisiteDto other = (VisiteDto) obj;
		if (numVisite == null) {
			if (other.numVisite != null)
				return false;
		} else if (!numVisite.equals(other.numVisite))
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
		if (employeSolliteMatricule == null) {
			if (other.employeSolliteMatricule != null)
				return false;
		} else if (!employeSolliteMatricule.equals(other.employeSolliteMatricule))
			return false;
		if (employeSolliteNom == null) {
			if (other.employeSolliteNom != null)
				return false;
		} else if (!employeSolliteNom.equals(other.employeSolliteNom))
			return false;
		if (employeSolliteCivilite == null) {
			if (other.employeSolliteCivilite != null)
				return false;
		} else if (!employeSolliteCivilite.equals(other.employeSolliteCivilite))
			return false;
		if (employeSolliteFonction == null) {
			if (other.employeSolliteFonction != null)
				return false;
		} else if (!employeSolliteFonction.equals(other.employeSolliteFonction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VisiteDto [numVisite=" + numVisite + ", civiliteVisiteur=" + civiliteVisiteur + ", nomVisiteur="
				+ nomVisiteur + ", prenomVisiteur=" + prenomVisiteur + ", fonctionVisiteur=" + fonctionVisiteur
				+ ", paysVisiteur=" + paysVisiteur + ", dureeVisite=" + dureeVisite + ", dateDebutVisite="
				+ dateDebutVisite + ", dateFinVisite=" + dateFinVisite + ", butVisite=" + butVisite + ", statusVisite="
				+ statusVisite + ", dateStatusVisite=" + dateStatusVisite + ", employeSolliteMatricule="
				+ employeSolliteMatricule + ", employeSolliteNom=" + employeSolliteNom + ", employeSolliteCivilite="
				+ employeSolliteCivilite + ", employeSolliteFonction=" + employeSolliteFonction + "]";
	}
		
}
