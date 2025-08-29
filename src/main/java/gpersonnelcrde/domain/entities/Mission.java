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
public class Mission {
	@Id
   	@GeneratedValue
   	private Long id;
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
	private Employe employe;
	
	private LocalDateTime missionCreeeLe;
	private String missionCreeePar;
	private LocalDateTime missionModifieeLe;
	private String missionModifieePar;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public LocalDateTime getMissionCreeeLe() {
		return missionCreeeLe;
	}
	public void setMissionCreeeLe(LocalDateTime missionCreeeLe) {
		this.missionCreeeLe = missionCreeeLe;
	}
	public String getMissionCreeePar() {
		return missionCreeePar;
	}
	public void setMissionCreeePar(String missionCreeePar) {
		this.missionCreeePar = missionCreeePar;
	}
	public LocalDateTime getMissionModifieeLe() {
		return missionModifieeLe;
	}
	public void setMissionModifieeLe(LocalDateTime missionModifieeLe) {
		this.missionModifieeLe = missionModifieeLe;
	}
	public String getMissionModifieePar() {
		return missionModifieePar;
	}
	public void setMissionModifieePar(String missionModifieePar) {
		this.missionModifieePar = missionModifieePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((natureMission == null) ? 0 : natureMission.hashCode());
		result = prime * result + ((cadreMission == null) ? 0 : cadreMission.hashCode());
		result = prime * result + ((dateDepart == null) ? 0 : dateDepart.hashCode());
		result = prime * result + ((dateRetour == null) ? 0 : dateRetour.hashCode());
		result = prime * result + ((paysMission == null) ? 0 : paysMission.hashCode());
		result = prime * result + ((villeMission == null) ? 0 : villeMission.hashCode());
		result = prime * result + ((motifMission == null) ? 0 : motifMission.hashCode());
		result = prime * result + ((infoSupplementaires == null) ? 0 : infoSupplementaires.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((missionCreeeLe == null) ? 0 : missionCreeeLe.hashCode());
		result = prime * result + ((missionCreeePar == null) ? 0 : missionCreeePar.hashCode());
		result = prime * result + ((missionModifieeLe == null) ? 0 : missionModifieeLe.hashCode());
		result = prime * result + ((missionModifieePar == null) ? 0 : missionModifieePar.hashCode());
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
		Mission other = (Mission) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
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
		if (missionCreeeLe == null) {
			if (other.missionCreeeLe != null)
				return false;
		} else if (!missionCreeeLe.equals(other.missionCreeeLe))
			return false;
		if (missionCreeePar == null) {
			if (other.missionCreeePar != null)
				return false;
		} else if (!missionCreeePar.equals(other.missionCreeePar))
			return false;
		if (missionModifieeLe == null) {
			if (other.missionModifieeLe != null)
				return false;
		} else if (!missionModifieeLe.equals(other.missionModifieeLe))
			return false;
		if (missionModifieePar == null) {
			if (other.missionModifieePar != null)
				return false;
		} else if (!missionModifieePar.equals(other.missionModifieePar))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Mission [id=" + id + ", natureMission=" + natureMission + ", cadreMission=" + cadreMission
				+ ", dateDepart=" + dateDepart + ", dateRetour=" + dateRetour + ", paysMission=" + paysMission
				+ ", villeMission=" + villeMission + ", motifMission=" + motifMission + ", infoSupplementaires="
				+ infoSupplementaires + ", employe=" + employe + "]";
	}	
}
