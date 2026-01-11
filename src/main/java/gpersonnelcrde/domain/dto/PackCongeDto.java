package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

public class PackCongeDto {
	private String typeDemandeConge;
	private LocalDate dateDebutConge;
	private LocalDate dateFinConge;
	private String infoSupplementaires;
	private String numNoteServiceConge;
	private String numConge;
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
	private String numAutSortie;
	private String motifSortie;
	private String statusAutorisatSortie;
	private LocalDate dateStatusAutorisatSortie;

	private String employeMatriculeRemplacant;
	private String employeNomRemplacant;
	private String employeCiviliteRemplacant;
	private String employeFonctionRemplacant;
	
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
	public String getNumConge() {
		return numConge;
	}
	public void setNumConge(String numConge) {
		this.numConge = numConge;
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
	public String getNumAutSortie() {
		return numAutSortie;
	}
	public void setNumAutSortie(String numAutSortie) {
		this.numAutSortie = numAutSortie;
	}
	public String getMotifSortie() {
		return motifSortie;
	}
	public void setMotifSortie(String motifSortie) {
		this.motifSortie = motifSortie;
	}
	public String getStatusAutorisatSortie() {
		return statusAutorisatSortie;
	}
	public void setStatusAutorisatSortie(String statusAutorisatSortie) {
		this.statusAutorisatSortie = statusAutorisatSortie;
	}
	public LocalDate getDateStatusAutorisatSortie() {
		return dateStatusAutorisatSortie;
	}
	public void setDateStatusAutorisatSortie(LocalDate dateStatusAutorisatSortie) {
		this.dateStatusAutorisatSortie = dateStatusAutorisatSortie;
	}
	public String getEmployeMatriculeRemplacant() {
		return employeMatriculeRemplacant;
	}
	public void setEmployeMatriculeRemplacant(String employeMatriculeRemplacant) {
		this.employeMatriculeRemplacant = employeMatriculeRemplacant;
	}
	public String getEmployeNomRemplacant() {
		return employeNomRemplacant;
	}
	public void setEmployeNomRemplacant(String employeNomRemplacant) {
		this.employeNomRemplacant = employeNomRemplacant;
	}
	public String getEmployeCiviliteRemplacant() {
		return employeCiviliteRemplacant;
	}
	public void setEmployeCiviliteRemplacant(String employeCiviliteRemplacant) {
		this.employeCiviliteRemplacant = employeCiviliteRemplacant;
	}
	public String getEmployeFonctionRemplacant() {
		return employeFonctionRemplacant;
	}
	public void setEmployeFonctionRemplacant(String employeFonctionRemplacant) {
		this.employeFonctionRemplacant = employeFonctionRemplacant;
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
		result = prime * result + ((numConge == null) ? 0 : numConge.hashCode());
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
		result = prime * result + ((numAutSortie == null) ? 0 : numAutSortie.hashCode());
		result = prime * result + ((motifSortie == null) ? 0 : motifSortie.hashCode());
		result = prime * result + ((statusAutorisatSortie == null) ? 0 : statusAutorisatSortie.hashCode());
		result = prime * result + ((dateStatusAutorisatSortie == null) ? 0 : dateStatusAutorisatSortie.hashCode());
		result = prime * result + ((employeMatriculeRemplacant == null) ? 0 : employeMatriculeRemplacant.hashCode());
		result = prime * result + ((employeNomRemplacant == null) ? 0 : employeNomRemplacant.hashCode());
		result = prime * result + ((employeCiviliteRemplacant == null) ? 0 : employeCiviliteRemplacant.hashCode());
		result = prime * result + ((employeFonctionRemplacant == null) ? 0 : employeFonctionRemplacant.hashCode());
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
		PackCongeDto other = (PackCongeDto) obj;
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
		if (numConge == null) {
			if (other.numConge != null)
				return false;
		} else if (!numConge.equals(other.numConge))
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
		if (numAutSortie == null) {
			if (other.numAutSortie != null)
				return false;
		} else if (!numAutSortie.equals(other.numAutSortie))
			return false;
		if (motifSortie == null) {
			if (other.motifSortie != null)
				return false;
		} else if (!motifSortie.equals(other.motifSortie))
			return false;
		if (statusAutorisatSortie == null) {
			if (other.statusAutorisatSortie != null)
				return false;
		} else if (!statusAutorisatSortie.equals(other.statusAutorisatSortie))
			return false;
		if (dateStatusAutorisatSortie == null) {
			if (other.dateStatusAutorisatSortie != null)
				return false;
		} else if (!dateStatusAutorisatSortie.equals(other.dateStatusAutorisatSortie))
			return false;
		if (employeMatriculeRemplacant == null) {
			if (other.employeMatriculeRemplacant != null)
				return false;
		} else if (!employeMatriculeRemplacant.equals(other.employeMatriculeRemplacant))
			return false;
		if (employeNomRemplacant == null) {
			if (other.employeNomRemplacant != null)
				return false;
		} else if (!employeNomRemplacant.equals(other.employeNomRemplacant))
			return false;
		if (employeCiviliteRemplacant == null) {
			if (other.employeCiviliteRemplacant != null)
				return false;
		} else if (!employeCiviliteRemplacant.equals(other.employeCiviliteRemplacant))
			return false;
		if (employeFonctionRemplacant == null) {
			if (other.employeFonctionRemplacant != null)
				return false;
		} else if (!employeFonctionRemplacant.equals(other.employeFonctionRemplacant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PackCongeDto [typeDemandeConge=" + typeDemandeConge + ", dateDebutConge=" + dateDebutConge
				+ ", dateFinConge=" + dateFinConge + ", infoSupplementaires=" + infoSupplementaires
				+ ", numNoteServiceConge=" + numNoteServiceConge + ", numConge=" + numConge + ", statusConge="
				+ statusConge + ", dateStatusConge=" + dateStatusConge + ", employeMatricule=" + employeMatricule
				+ ", employeNom=" + employeNom + ", employeCivilite=" + employeCivilite + ", employeFonction="
				+ employeFonction + ", dateDepartAutorisatSortie=" + dateDepartAutorisatSortie
				+ ", dateRetourAutorisatSortie=" + dateRetourAutorisatSortie + ", villeAutorisatSortie="
				+ villeAutorisatSortie + ", paysAutorisatSortie=" + paysAutorisatSortie + ", numAutorisatSortie="
				+ numAutorisatSortie + ", numAutSortie=" + numAutSortie + ", motifSortie=" + motifSortie
				+ ", statusAutorisatSortie=" + statusAutorisatSortie + ", dateStatusAutorisatSortie="
				+ dateStatusAutorisatSortie + ", employeMatriculeRemplacant=" + employeMatriculeRemplacant
				+ ", employeNomRemplacant=" + employeNomRemplacant + ", employeCiviliteRemplacant="
				+ employeCiviliteRemplacant + ", employeFonctionRemplacant=" + employeFonctionRemplacant + "]";
	}	
}
