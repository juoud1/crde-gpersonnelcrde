package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

public class MissionDto {
	private String natureMission;
	private String cadreMission;
	private LocalDate dateDepart;
	private LocalDate dateRetour;

	private String paysMission;
	private String villeMission;
	private String motifMission;
	private String infoSupplementaires;
	private String numOrdreMission;
	private String typeOrdreMission;
	private String statusMission;
	private LocalDate dateStatusMission;

	private String employeMatricule;
	private String employeNom;
	private String employeCivilite;
	private String employeFonction;
	public String getNatureMission() {
		return natureMission;
	}
	public void setNatureMission(String natureMission) {
		this.natureMission = natureMission;
	}
	public String getCadreMission() {
		return cadreMission;
	}
	public void setCadreMission(String cadreMission) {
		this.cadreMission = cadreMission;
	}
	public LocalDate getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}
	public LocalDate getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
	public String getPaysMission() {
		return paysMission;
	}
	public void setPaysMission(String paysMission) {
		this.paysMission = paysMission;
	}
	public String getVilleMission() {
		return villeMission;
	}
	public void setVilleMission(String villeMission) {
		this.villeMission = villeMission;
	}
	public String getMotifMission() {
		return motifMission;
	}
	public void setMotifMission(String motifMission) {
		this.motifMission = motifMission;
	}
	public String getInfoSupplementaires() {
		return infoSupplementaires;
	}
	public void setInfoSupplementaires(String infoSupplementaires) {
		this.infoSupplementaires = infoSupplementaires;
	}
	public String getNumOrdreMission() {
		return numOrdreMission;
	}
	public void setNumOrdreMission(String numOrdreMission) {
		this.numOrdreMission = numOrdreMission;
	}
	public String getTypeOrdreMission() {
		return typeOrdreMission;
	}
	public void setTypeOrdreMission(String typeOrdreMission) {
		this.typeOrdreMission = typeOrdreMission;
	}
	public String getStatusMission() {
		return statusMission;
	}
	public void setStatusMission(String statusMission) {
		this.statusMission = statusMission;
	}
	public LocalDate getDateStatusMission() {
		return dateStatusMission;
	}
	public void setDateStatusMission(LocalDate dateStatusMission) {
		this.dateStatusMission = dateStatusMission;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((natureMission == null) ? 0 : natureMission.hashCode());
		result = prime * result + ((cadreMission == null) ? 0 : cadreMission.hashCode());
		result = prime * result + ((dateDepart == null) ? 0 : dateDepart.hashCode());
		result = prime * result + ((dateRetour == null) ? 0 : dateRetour.hashCode());
		result = prime * result + ((paysMission == null) ? 0 : paysMission.hashCode());
		result = prime * result + ((villeMission == null) ? 0 : villeMission.hashCode());
		result = prime * result + ((motifMission == null) ? 0 : motifMission.hashCode());
		result = prime * result + ((infoSupplementaires == null) ? 0 : infoSupplementaires.hashCode());
		result = prime * result + ((numOrdreMission == null) ? 0 : numOrdreMission.hashCode());
		result = prime * result + ((typeOrdreMission == null) ? 0 : typeOrdreMission.hashCode());
		result = prime * result + ((statusMission == null) ? 0 : statusMission.hashCode());
		result = prime * result + ((dateStatusMission == null) ? 0 : dateStatusMission.hashCode());
		result = prime * result + ((employeMatricule == null) ? 0 : employeMatricule.hashCode());
		result = prime * result + ((employeNom == null) ? 0 : employeNom.hashCode());
		result = prime * result + ((employeCivilite == null) ? 0 : employeCivilite.hashCode());
		result = prime * result + ((employeFonction == null) ? 0 : employeFonction.hashCode());
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
		MissionDto other = (MissionDto) obj;
		if (natureMission == null) {
			if (other.natureMission != null)
				return false;
		} else if (!natureMission.equals(other.natureMission))
			return false;
		if (cadreMission == null) {
			if (other.cadreMission != null)
				return false;
		} else if (!cadreMission.equals(other.cadreMission))
			return false;
		if (dateDepart == null) {
			if (other.dateDepart != null)
				return false;
		} else if (!dateDepart.equals(other.dateDepart))
			return false;
		if (dateRetour == null) {
			if (other.dateRetour != null)
				return false;
		} else if (!dateRetour.equals(other.dateRetour))
			return false;
		if (paysMission == null) {
			if (other.paysMission != null)
				return false;
		} else if (!paysMission.equals(other.paysMission))
			return false;
		if (villeMission == null) {
			if (other.villeMission != null)
				return false;
		} else if (!villeMission.equals(other.villeMission))
			return false;
		if (motifMission == null) {
			if (other.motifMission != null)
				return false;
		} else if (!motifMission.equals(other.motifMission))
			return false;
		if (infoSupplementaires == null) {
			if (other.infoSupplementaires != null)
				return false;
		} else if (!infoSupplementaires.equals(other.infoSupplementaires))
			return false;
		if (numOrdreMission == null) {
			if (other.numOrdreMission != null)
				return false;
		} else if (!numOrdreMission.equals(other.numOrdreMission))
			return false;
		if (typeOrdreMission == null) {
			if (other.typeOrdreMission != null)
				return false;
		} else if (!typeOrdreMission.equals(other.typeOrdreMission))
			return false;
		if (statusMission == null) {
			if (other.statusMission != null)
				return false;
		} else if (!statusMission.equals(other.statusMission))
			return false;
		if (dateStatusMission == null) {
			if (other.dateStatusMission != null)
				return false;
		} else if (!dateStatusMission.equals(other.dateStatusMission))
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
		return true;
	}
	@Override
	public String toString() {
		return "MissionDto [natureMission=" + natureMission + ", cadreMission=" + cadreMission + ", dateDepart="
				+ dateDepart + ", dateRetour=" + dateRetour + ", paysMission=" + paysMission + ", villeMission="
				+ villeMission + ", motifMission=" + motifMission + ", infoSupplementaires=" + infoSupplementaires
				+ ", numOrdreMission=" + numOrdreMission + ", typeOrdreMission=" + typeOrdreMission + ", statusMission="
				+ statusMission + ", dateStatusMission=" + dateStatusMission + ", employeMatricule=" + employeMatricule
				+ ", employeNom=" + employeNom + ", employeCivilite=" + employeCivilite + ", employeFonction="
				+ employeFonction + "]";
	}
	
		
}
