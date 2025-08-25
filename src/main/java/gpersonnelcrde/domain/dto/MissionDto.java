package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToOne;

public class MissionDto {
	private String natureMission;
	private String cadreMission;
	private LocalDate dateDepart;
	private LocalDate dateRetour;
	private String paysMission;
	private String villeMission;
	private String motifMission;
	private String infoSupplementaires;

	@JsonIgnore
	@ManyToOne
	private EmployeDto employe;

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

	public EmployeDto getEmploye() {
		return employe;
	}

	public void setEmploye(EmployeDto employe) {
		this.employe = employe;
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
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
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
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MissionDto [natureMission=" + natureMission + ", cadreMission=" + cadreMission + ", dateDepart="
				+ dateDepart + ", dateRetour=" + dateRetour + ", paysMission=" + paysMission + ", villeMission="
				+ villeMission + ", motifMission=" + motifMission + ", infoSupplementaires=" + infoSupplementaires
				+ ", employe=" + employe + "]";
	}
}
