package gpersonnelcrde.domain.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Embeddable
public class MissionEmployePk implements Serializable, Comparable<MissionEmployePk> {
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employe employe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Mission mission;

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((mission == null) ? 0 : mission.hashCode());
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
		MissionEmployePk other = (MissionEmployePk) obj;
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		if (mission == null) {
			if (other.mission != null)
				return false;
		} else if (!mission.equals(other.mission))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeMissionPk [employe=" + employe + ", mission=" + mission + "]";
	}

	@Override
	public int compareTo(MissionEmployePk o) {
		return o.getEmploye().compareTo(employe);
	}

}
