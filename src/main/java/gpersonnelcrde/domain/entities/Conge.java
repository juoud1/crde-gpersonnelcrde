package gpersonnelcrde.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Conge {
	@Id
   	@GeneratedValue
   	private Long id;
	private LocalDate dateDebutConge;
	private LocalDate dateFinConge;
	private String infoSupplementaires;

	@JsonIgnore
	@ManyToOne
	private Employe employe;

	private LocalDateTime congeCreeLe;
	private String congeCreePar;
	private LocalDateTime congeModifieLe;
	private String congeModifiePar;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public LocalDateTime getCongeCreeLe() {
		return congeCreeLe;
	}
	public void setCongeCreeLe(LocalDateTime congeCreeLe) {
		this.congeCreeLe = congeCreeLe;
	}
	public String getCongeCreePar() {
		return congeCreePar;
	}
	public void setCongeCreePar(String congeCreePar) {
		this.congeCreePar = congeCreePar;
	}
	public LocalDateTime getCongeModifieLe() {
		return congeModifieLe;
	}
	public void setCongeModifieLe(LocalDateTime congeModifieLe) {
		this.congeModifieLe = congeModifieLe;
	}
	public String getCongeModifiePar() {
		return congeModifiePar;
	}
	public void setCongeModifiePar(String congeModifiePar) {
		this.congeModifiePar = congeModifiePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((dateDebutConge == null) ? 0 : dateDebutConge.hashCode());
		result = prime * result + ((dateFinConge == null) ? 0 : dateFinConge.hashCode());
		result = prime * result + ((infoSupplementaires == null) ? 0 : infoSupplementaires.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((congeCreeLe == null) ? 0 : congeCreeLe.hashCode());
		result = prime * result + ((congeCreePar == null) ? 0 : congeCreePar.hashCode());
		result = prime * result + ((congeModifieLe == null) ? 0 : congeModifieLe.hashCode());
		result = prime * result + ((congeModifiePar == null) ? 0 : congeModifiePar.hashCode());
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
		Conge other = (Conge) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		if (congeCreeLe == null) {
			if (other.congeCreeLe != null)
				return false;
		} else if (!congeCreeLe.equals(other.congeCreeLe))
			return false;
		if (congeCreePar == null) {
			if (other.congeCreePar != null)
				return false;
		} else if (!congeCreePar.equals(other.congeCreePar))
			return false;
		if (congeModifieLe == null) {
			if (other.congeModifieLe != null)
				return false;
		} else if (!congeModifieLe.equals(other.congeModifieLe))
			return false;
		if (congeModifiePar == null) {
			if (other.congeModifiePar != null)
				return false;
		} else if (!congeModifiePar.equals(other.congeModifiePar))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Conge [id=" + id + ", dateDebutConge=" + dateDebutConge + ", dateFinConge=" + dateFinConge
				+ ", infoSupplementaires=" + infoSupplementaires + ", employe=" + employe + "]";
	}
}
