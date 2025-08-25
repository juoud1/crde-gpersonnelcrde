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
	public void setCongeCreeLe(LocalDateTime affectCreeLe) {
		this.congeCreeLe = affectCreeLe;
	}
	public String getCongeCreePar() {
		return congeCreePar;
	}
	public void setCongeCreePar(String affectCreePar) {
		this.congeCreePar = affectCreePar;
	}
	public LocalDateTime getCongeModifieLe() {
		return congeModifieLe;
	}
	public void setCongeModifieLe(LocalDateTime affectModifieLe) {
		this.congeModifieLe = affectModifieLe;
	}
	public String getCongeModifiePar() {
		return congeModifiePar;
	}
	public void setCongeModifiePar(String affectModifiePar) {
		this.congeModifiePar = affectModifiePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateDebutConge == null) ? 0 : dateDebutConge.hashCode());
		result = prime * result + ((dateFinConge == null) ? 0 : dateFinConge.hashCode());
		result = prime * result + ((infoSupplementaires == null) ? 0 : infoSupplementaires.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
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
		Conge other = (Conge) obj;
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
		return true;
	}
	@Override
	public String toString() {
		return "Conge [dateDebutConge=" + dateDebutConge + ", dateFinConge=" + dateFinConge + ", infoSupplementaires="
				+ infoSupplementaires + ", employe=" + employe + "]";
	}
}
