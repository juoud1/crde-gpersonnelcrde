package gpersonnelcrde.domain.dto;

public class AdresseEmpDto {
	private String adrId;
	private String adrQuartierResidceEmp;
	private String adrVilleResidceEmp;
	private String adrPrefResidceEmp;
	private String adrRegionResidceEmp;
	private String empMatricule;
	private boolean estAdrResidceEmpActive;
	
	public String getAdrId() {
		return adrId;
	}
	public void setAdrId(String adrId) {
		this.adrId = adrId;
	}
	public String getAdrQuartierResidceEmp() {
		return adrQuartierResidceEmp;
	}
	public void setAdrQuartierResidceEmp(String adrQuartierResidceEmp) {
		this.adrQuartierResidceEmp = adrQuartierResidceEmp;
	}
	public String getAdrVilleResidceEmp() {
		return adrVilleResidceEmp;
	}
	public void setAdrVilleResidceEmp(String adrVilleResidceEmp) {
		this.adrVilleResidceEmp = adrVilleResidceEmp;
	}
	public String getAdrPrefResidceEmp() {
		return adrPrefResidceEmp;
	}
	public void setAdrPrefResidceEmp(String adrPrefResidceEmp) {
		this.adrPrefResidceEmp = adrPrefResidceEmp;
	}
	public String getAdrRegionResidceEmp() {
		return adrRegionResidceEmp;
	}
	public void setAdrRegionResidceEmp(String adrRegionResidceEmp) {
		this.adrRegionResidceEmp = adrRegionResidceEmp;
	}
	public String getEmpMatricule() {
		return empMatricule;
	}
	public void setEmpMatricule(String empMatricule) {
		this.empMatricule = empMatricule;
	}
	public boolean isEstAdrResidceEmpActive() {
		return estAdrResidceEmpActive;
	}
	public void setEstAdrResidceEmpActive(boolean estAdrResidceEmpActive) {
		this.estAdrResidceEmpActive = estAdrResidceEmpActive;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adrId == null) ? 0 : adrId.hashCode());
		result = prime * result + ((adrQuartierResidceEmp == null) ? 0 : adrQuartierResidceEmp.hashCode());
		result = prime * result + ((adrVilleResidceEmp == null) ? 0 : adrVilleResidceEmp.hashCode());
		result = prime * result + ((adrPrefResidceEmp == null) ? 0 : adrPrefResidceEmp.hashCode());
		result = prime * result + ((adrRegionResidceEmp == null) ? 0 : adrRegionResidceEmp.hashCode());
		result = prime * result + ((empMatricule == null) ? 0 : empMatricule.hashCode());
		result = prime * result + (estAdrResidceEmpActive ? 1231 : 1237);
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
		AdresseEmpDto other = (AdresseEmpDto) obj;
		if (adrId == null) {
			if (other.adrId != null)
				return false;
		} else if (!adrId.equals(other.adrId))
			return false;
		if (adrQuartierResidceEmp == null) {
			if (other.adrQuartierResidceEmp != null)
				return false;
		} else if (!adrQuartierResidceEmp.equals(other.adrQuartierResidceEmp))
			return false;
		if (adrVilleResidceEmp == null) {
			if (other.adrVilleResidceEmp != null)
				return false;
		} else if (!adrVilleResidceEmp.equals(other.adrVilleResidceEmp))
			return false;
		if (adrPrefResidceEmp == null) {
			if (other.adrPrefResidceEmp != null)
				return false;
		} else if (!adrPrefResidceEmp.equals(other.adrPrefResidceEmp))
			return false;
		if (adrRegionResidceEmp == null) {
			if (other.adrRegionResidceEmp != null)
				return false;
		} else if (!adrRegionResidceEmp.equals(other.adrRegionResidceEmp))
			return false;
		if (empMatricule == null) {
			if (other.empMatricule != null)
				return false;
		} else if (!empMatricule.equals(other.empMatricule))
			return false;
		if (estAdrResidceEmpActive != other.estAdrResidceEmpActive)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "AdresseEmpDto [adrId=" + adrId + ", adrQuartierResidceEmp=" + adrQuartierResidceEmp
				+ ", adrVilleResidceEmp=" + adrVilleResidceEmp + ", adrPrefResidceEmp=" + adrPrefResidceEmp
				+ ", adrRegionResidceEmp=" + adrRegionResidceEmp + ", empMatricule=" + empMatricule
				+ ", estAdrResidceEmpActive=" + estAdrResidceEmpActive + "]";
	}
		
}
