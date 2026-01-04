package gpersonnelcrde.domain.entities;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@Table(schema = "crde", name = "mission_employe")
public class MissionEmploye extends AbstractPersistable<MissionEmployePk> {

	@EmbeddedId
	private MissionEmployePk id;
	private boolean isEmployeChefMission;

	private LocalDateTime missEmpCreeeLe;
	private String missEmpCreeePar;
	private LocalDateTime missEmpModifieeLe;
	private String missEmpModifieePar;
	
	public MissionEmployePk getId() {
		return id;
	}
	public void setId(MissionEmployePk id) {
		this.id = id;
	}
	public boolean isEmployeChefMission() {
		return isEmployeChefMission;
	}
	public void setEmployeChefMission(boolean isEmployeChefMission) {
		this.isEmployeChefMission = isEmployeChefMission;
	}
	public LocalDateTime getMissEmpCreeeLe() {
		return missEmpCreeeLe;
	}
	public void setMissEmpCreeeLe(LocalDateTime missEmpCreeeLe) {
		this.missEmpCreeeLe = missEmpCreeeLe;
	}
	public String getMissEmpCreeePar() {
		return missEmpCreeePar;
	}
	public void setMissEmpCreeePar(String missEmpCreeePar) {
		this.missEmpCreeePar = missEmpCreeePar;
	}
	public LocalDateTime getMissEmpModifieeLe() {
		return missEmpModifieeLe;
	}
	public void setMissEmpModifieeLe(LocalDateTime missEmpModifieeLe) {
		this.missEmpModifieeLe = missEmpModifieeLe;
	}
	public String getMissEmpModifieePar() {
		return missEmpModifieePar;
	}
	public void setMissEmpModifieePar(String missEmpModifieePar) {
		this.missEmpModifieePar = missEmpModifieePar;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isEmployeChefMission ? 1231 : 1237);
		result = prime * result + ((missEmpCreeeLe == null) ? 0 : missEmpCreeeLe.hashCode());
		result = prime * result + ((missEmpCreeePar == null) ? 0 : missEmpCreeePar.hashCode());
		result = prime * result + ((missEmpModifieeLe == null) ? 0 : missEmpModifieeLe.hashCode());
		result = prime * result + ((missEmpModifieePar == null) ? 0 : missEmpModifieePar.hashCode());
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
		MissionEmploye other = (MissionEmploye) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isEmployeChefMission != other.isEmployeChefMission)
			return false;
		if (missEmpCreeeLe == null) {
			if (other.missEmpCreeeLe != null)
				return false;
		} else if (!missEmpCreeeLe.equals(other.missEmpCreeeLe))
			return false;
		if (missEmpCreeePar == null) {
			if (other.missEmpCreeePar != null)
				return false;
		} else if (!missEmpCreeePar.equals(other.missEmpCreeePar))
			return false;
		if (missEmpModifieeLe == null) {
			if (other.missEmpModifieeLe != null)
				return false;
		} else if (!missEmpModifieeLe.equals(other.missEmpModifieeLe))
			return false;
		if (missEmpModifieePar == null) {
			if (other.missEmpModifieePar != null)
				return false;
		} else if (!missEmpModifieePar.equals(other.missEmpModifieePar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MissionEmploye [id=" + id + ", isEmployeChefMission=" + isEmployeChefMission + ", missEmpCreeeLe="
				+ missEmpCreeeLe + ", missEmpCreeePar=" + missEmpCreeePar + ", missEmpModifieeLe=" + missEmpModifieeLe
				+ ", missEmpModifieePar=" + missEmpModifieePar + "]";
	}

	
}
