package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToOne;

public class CongeDto {
	private LocalDate dateDebutConge;
	private LocalDate dateFinConge;
	private String infoSupplementaires;
	private String numNoteServiceConge;

	@JsonIgnore
	@ManyToOne
	private EmployeDto employe;

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
		result = prime * result + ((dateDebutConge == null) ? 0 : dateDebutConge.hashCode());
		result = prime * result + ((dateFinConge == null) ? 0 : dateFinConge.hashCode());
		result = prime * result + ((infoSupplementaires == null) ? 0 : infoSupplementaires.hashCode());
		result = prime * result + ((numNoteServiceConge == null) ? 0 : numNoteServiceConge.hashCode());
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
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CongeDto [dateDebutConge=" + dateDebutConge + ", dateFinConge=" + dateFinConge
				+ ", infoSupplementaires=" + infoSupplementaires + ", numNoteServiceConge=" + numNoteServiceConge
				+ ", employe=" + employe + "]";
	}
		
}
