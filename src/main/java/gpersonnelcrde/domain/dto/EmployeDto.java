package gpersonnelcrde.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToOne;

public class EmployeDto {
	private String empMatricule;
	private String empNom;
	private String empPren;
	private String empCivilite;

	@JsonIgnore
	@ManyToOne
	private TypeEmployeDto typeEmploye;

	@JsonIgnore
	@ManyToOne
	private StatusDto status;

	@JsonIgnore
	@ManyToOne
	private LieuAffectationDto lieuAffectation;

	@JsonIgnore
	@ManyToOne
	private FonctionDto fonction;

	public String getEmpMatricule() {
		return empMatricule;
	}

	public void setEmpMatricule(String empMatricule) {
		this.empMatricule = empMatricule;
	}

	public String getEmpNom() {
		return empNom;
	}

	public void setEmpNom(String empNom) {
		this.empNom = empNom;
	}

	public String getEmpPren() {
		return empPren;
	}

	public void setEmpPren(String empPren) {
		this.empPren = empPren;
	}

	public String getEmpCivilite() {
		return empCivilite;
	}

	public void setEmpCivilite(String empCivilite) {
		this.empCivilite = empCivilite;
	}

	public TypeEmployeDto getTypeEmploye() {
		return typeEmploye;
	}

	public void setTypeEmploye(TypeEmployeDto typeEmploye) {
		this.typeEmploye = typeEmploye;
	}

	public StatusDto getStatus() {
		return status;
	}

	public void setStatus(StatusDto status) {
		this.status = status;
	}

	public LieuAffectationDto getLieuAffectation() {
		return lieuAffectation;
	}

	public void setLieuAffectation(LieuAffectationDto lieuAffectation) {
		this.lieuAffectation = lieuAffectation;
	}

	public FonctionDto getFonction() {
		return fonction;
	}

	public void setFonction(FonctionDto fonction) {
		this.fonction = fonction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empMatricule == null) ? 0 : empMatricule.hashCode());
		result = prime * result + ((empNom == null) ? 0 : empNom.hashCode());
		result = prime * result + ((empPren == null) ? 0 : empPren.hashCode());
		result = prime * result + ((empCivilite == null) ? 0 : empCivilite.hashCode());
		result = prime * result + ((typeEmploye == null) ? 0 : typeEmploye.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((lieuAffectation == null) ? 0 : lieuAffectation.hashCode());
		result = prime * result + ((fonction == null) ? 0 : fonction.hashCode());
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
		EmployeDto other = (EmployeDto) obj;
		if (empMatricule == null) {
			if (other.empMatricule != null)
				return false;
		} else if (!empMatricule.equals(other.empMatricule))
			return false;
		if (empNom == null) {
			if (other.empNom != null)
				return false;
		} else if (!empNom.equals(other.empNom))
			return false;
		if (empPren == null) {
			if (other.empPren != null)
				return false;
		} else if (!empPren.equals(other.empPren))
			return false;
		if (empCivilite == null) {
			if (other.empCivilite != null)
				return false;
		} else if (!empCivilite.equals(other.empCivilite))
			return false;
		if (typeEmploye == null) {
			if (other.typeEmploye != null)
				return false;
		} else if (!typeEmploye.equals(other.typeEmploye))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (lieuAffectation == null) {
			if (other.lieuAffectation != null)
				return false;
		} else if (!lieuAffectation.equals(other.lieuAffectation))
			return false;
		if (fonction == null) {
			if (other.fonction != null)
				return false;
		} else if (!fonction.equals(other.fonction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeDto [empMatricule=" + empMatricule + ", empNom=" + empNom + ", empPren=" + empPren
				+ ", empCivilite=" + empCivilite + ", typeEmploye=" + typeEmploye + ", status=" + status
				+ ", lieuAffectation=" + lieuAffectation + ", fonction=" + fonction + "]";
	}
}
