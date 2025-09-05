package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

public class CongeDto {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebutConge == null) ? 0 : dateDebutConge.hashCode());
		result = prime * result + ((dateFinConge == null) ? 0 : dateFinConge.hashCode());
		result = prime * result + ((infoSupplementaires == null) ? 0 : infoSupplementaires.hashCode());
		result = prime * result + ((numNoteServiceConge == null) ? 0 : numNoteServiceConge.hashCode());
		result = prime * result + ((employeMatricule == null) ? 0 : employeMatricule.hashCode());
		result = prime * result + ((employeNom == null) ? 0 : employeNom.hashCode());
		result = prime * result + ((employeCivilite == null) ? 0 : employeCivilite.hashCode());
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
		return true;
	}
	@Override
	public String toString() {
		return "CongeDto [dateDebutConge=" + dateDebutConge + ", dateFinConge=" + dateFinConge
				+ ", infoSupplementaires=" + infoSupplementaires + ", numNoteServiceConge=" + numNoteServiceConge
				+ ", employeMatricule=" + employeMatricule + ", employeNom=" + employeNom + ", employeCivilite="
				+ employeCivilite + "]";
	}
	public String getEmployeFonction() {
		return employeFonction;
	}
	public void setEmployeFonction(String employeFonction) {
		this.employeFonction = employeFonction;
	} 
}
