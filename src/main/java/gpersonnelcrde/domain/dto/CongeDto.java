package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

public class CongeDto {
	private String typeDemandeConge;
	private LocalDate dateDebutConge;
	private LocalDate dateFinConge;
	private String infoSupplementaires;
	private String numNoteServiceConge;
	private String statusConge;
	private LocalDate dateStatusConge;

	private String employeMatricule;
	private String employeNom;
	private String employeCivilite;
	private String employeFonction;

	private LocalDate dateDepartAutorisatSortie;
	private LocalDate dateRetourAutorisatSortie;
	private String villeAutorisatSortie;
	private String paysAutorisatSortie;
	private String numAutorisatSortie;
	private String motifSortie;
	
	public String getTypeDemandeConge() {
		return typeDemandeConge;
	}
	public void setTypeDemandeConge(String typeDemandeConge) {
		this.typeDemandeConge = typeDemandeConge;
	}
	public LocalDate getDateDebutConge() {
		return dateDebutConge;
	}
	public void setDateDebutConge(LocalDate dateDebutConge) {
		this.dateDebutConge = dateDebutConge;
	}
	public LocalDate getDateFinConge() {
		return dateFinConge;
	}
	public void setDateFinConge(LocalDate dateFinConge) {
		this.dateFinConge = dateFinConge;
	}
	public String getInfoSupplementaires() {
		return infoSupplementaires;
	}
	public void setInfoSupplementaires(String infoSupplementaires) {
		this.infoSupplementaires = infoSupplementaires;
	}
	public String getNumNoteServiceConge() {
		return numNoteServiceConge;
	}
	public void setNumNoteServiceConge(String numNoteServiceConge) {
		this.numNoteServiceConge = numNoteServiceConge;
	}
	public String getStatusConge() {
		return statusConge;
	}
	public void setStatusConge(String statusConge) {
		this.statusConge = statusConge;
	}
	public LocalDate getDateStatusConge() {
		return dateStatusConge;
	}
	public void setDateStatusConge(LocalDate dateStatusConge) {
		this.dateStatusConge = dateStatusConge;
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
	public String getEmployeFonction() {
		return employeFonction;
	}
	public void setEmployeFonction(String employeFonction) {
		this.employeFonction = employeFonction;
	}
	public LocalDate getDateDepartAutorisatSortie() {
		return dateDepartAutorisatSortie;
	}
	public void setDateDepartAutorisatSortie(LocalDate dateDepartAutorisatSortie) {
		this.dateDepartAutorisatSortie = dateDepartAutorisatSortie;
	}
	public LocalDate getDateRetourAutorisatSortie() {
		return dateRetourAutorisatSortie;
	}
	public void setDateRetourAutorisatSortie(LocalDate dateRetourAutorisatSortie) {
		this.dateRetourAutorisatSortie = dateRetourAutorisatSortie;
	}
	public String getVilleAutorisatSortie() {
		return villeAutorisatSortie;
	}
	public void setVilleAutorisatSortie(String villeAutorisatSortie) {
		this.villeAutorisatSortie = villeAutorisatSortie;
	}
	public String getPaysAutorisatSortie() {
		return paysAutorisatSortie;
	}
	public void setPaysAutorisatSortie(String paysAutorisatSortie) {
		this.paysAutorisatSortie = paysAutorisatSortie;
	}
	public String getNumAutorisatSortie() {
		return numAutorisatSortie;
	}
	public void setNumAutorisatSortie(String numAutorisatSortie) {
		this.numAutorisatSortie = numAutorisatSortie;
	}
	public String getMotifSortie() {
		return motifSortie;
	}
	public void setMotifSortie(String motifSortie) {
		this.motifSortie = motifSortie;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeDemandeConge == null) ? 0 : typeDemandeConge.hashCode());
		result = prime * result + ((dateDebutConge == null) ? 0 : dateDebutConge.hashCode());
		result = prime * result + ((dateFinConge == null) ? 0 : dateFinConge.hashCode());
		result = prime * result + ((infoSupplementaires == null) ? 0 : infoSupplementaires.hashCode());
		result = prime * result + ((numNoteServiceConge == null) ? 0 : numNoteServiceConge.hashCode());
		result = prime * result + ((statusConge == null) ? 0 : statusConge.hashCode());
		result = prime * result + ((dateStatusConge == null) ? 0 : dateStatusConge.hashCode());
		result = prime * result + ((employeMatricule == null) ? 0 : employeMatricule.hashCode());
		result = prime * result + ((employeNom == null) ? 0 : employeNom.hashCode());
		result = prime * result + ((employeCivilite == null) ? 0 : employeCivilite.hashCode());
		result = prime * result + ((employeFonction == null) ? 0 : employeFonction.hashCode());
		result = prime * result + ((dateDepartAutorisatSortie == null) ? 0 : dateDepartAutorisatSortie.hashCode());
		result = prime * result + ((dateRetourAutorisatSortie == null) ? 0 : dateRetourAutorisatSortie.hashCode());
		result = prime * result + ((villeAutorisatSortie == null) ? 0 : villeAutorisatSortie.hashCode());
		result = prime * result + ((paysAutorisatSortie == null) ? 0 : paysAutorisatSortie.hashCode());
		result = prime * result + ((numAutorisatSortie == null) ? 0 : numAutorisatSortie.hashCode());
		result = prime * result + ((motifSortie == null) ? 0 : motifSortie.hashCode());
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
		CongeDto other = (CongeDto) obj;
		if (typeDemandeConge == null) {
			if (other.typeDemandeConge != null)
				return false;
		} else if (!typeDemandeConge.equals(other.typeDemandeConge))
			return false;
		if (dateDebutConge == null) {
			if (other.dateDebutConge != null)
				return false;
		} else if (!dateDebutConge.equals(other.dateDebutConge))
			return false;
		if (dateFinConge == null) {
			if (other.dateFinConge != null)
				return false;
		} else if (!dateFinConge.equals(other.dateFinConge))
			return false;
		if (infoSupplementaires == null) {
			if (other.infoSupplementaires != null)
				return false;
		} else if (!infoSupplementaires.equals(other.infoSupplementaires))
			return false;
		if (numNoteServiceConge == null) {
			if (other.numNoteServiceConge != null)
				return false;
		} else if (!numNoteServiceConge.equals(other.numNoteServiceConge))
			return false;
		if (statusConge == null) {
			if (other.statusConge != null)
				return false;
		} else if (!statusConge.equals(other.statusConge))
			return false;
		if (dateStatusConge == null) {
			if (other.dateStatusConge != null)
				return false;
		} else if (!dateStatusConge.equals(other.dateStatusConge))
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
		if (employeFonction == null) {
			if (other.employeFonction != null)
				return false;
		} else if (!employeFonction.equals(other.employeFonction))
			return false;
		if (dateDepartAutorisatSortie == null) {
			if (other.dateDepartAutorisatSortie != null)
				return false;
		} else if (!dateDepartAutorisatSortie.equals(other.dateDepartAutorisatSortie))
			return false;
		if (dateRetourAutorisatSortie == null) {
			if (other.dateRetourAutorisatSortie != null)
				return false;
		} else if (!dateRetourAutorisatSortie.equals(other.dateRetourAutorisatSortie))
			return false;
		if (villeAutorisatSortie == null) {
			if (other.villeAutorisatSortie != null)
				return false;
		} else if (!villeAutorisatSortie.equals(other.villeAutorisatSortie))
			return false;
		if (paysAutorisatSortie == null) {
			if (other.paysAutorisatSortie != null)
				return false;
		} else if (!paysAutorisatSortie.equals(other.paysAutorisatSortie))
			return false;
		if (numAutorisatSortie == null) {
			if (other.numAutorisatSortie != null)
				return false;
		} else if (!numAutorisatSortie.equals(other.numAutorisatSortie))
			return false;
		if (motifSortie == null) {
			if (other.motifSortie != null)
				return false;
		} else if (!motifSortie.equals(other.motifSortie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CongeDto [typeDemandeConge=" + typeDemandeConge + ", dateDebutConge=" + dateDebutConge
				+ ", dateFinConge=" + dateFinConge + ", infoSupplementaires=" + infoSupplementaires
				+ ", numNoteServiceConge=" + numNoteServiceConge + ", statusConge=" + statusConge + ", dateStatusConge="
				+ dateStatusConge + ", employeMatricule=" + employeMatricule + ", employeNom=" + employeNom
				+ ", employeCivilite=" + employeCivilite + ", employeFonction=" + employeFonction
				+ ", dateDepartAutorisatSortie=" + dateDepartAutorisatSortie + ", dateRetourAutorisatSortie="
				+ dateRetourAutorisatSortie + ", villeAutorisatSortie=" + villeAutorisatSortie
				+ ", paysAutorisatSortie=" + paysAutorisatSortie + ", numAutorisatSortie=" + numAutorisatSortie
				+ ", motifSortie=" + motifSortie + "]";
	}
	
}
