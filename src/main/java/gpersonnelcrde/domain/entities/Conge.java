package gpersonnelcrde.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
//@Table(schema = "crde", name = "conge")
public class Conge implements Comparable<Conge> {
	@Id
   	@GeneratedValue(strategy=GenerationType.IDENTITY)
   	private Long id;
	private LocalDate dateDebutConge;
	private LocalDate dateFinConge;
	private String infoSupplementaires;
	private String statusConge;
	private LocalDate dateStatusConge;
	private String numNoteServiceConge;
	private String typeDemandeConge;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Employe employe;

	@JsonIgnore
	@ManyToOne
	private Employe employeRemplacant;

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
	public String getStatusConge() {
		return statusConge;
	}
	public void setStatusConge(String statusConge) {
		this.statusConge = statusConge;
	}
	public LocalDate getDateStatusConge() {
		return dateStatusConge;
	}
	public void setDateStatusConge(LocalDate dateStatusConge) {
		this.dateStatusConge = dateStatusConge;
	}
	public String getNumNoteServiceConge() {
		return numNoteServiceConge;
	}
	public void setNumNoteServiceConge(String numNoteServiceConge) {
		this.numNoteServiceConge = numNoteServiceConge;
	}
	public String getTypeDemandeConge() {
		return typeDemandeConge;
	}
	public void setTypeDemandeConge(String typeDemandeConge) {
		this.typeDemandeConge = typeDemandeConge;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public Employe getEmployeRemplacant() {
		return employeRemplacant;
	}
	public void setEmployeRemplacant(Employe employeRemplacant) {
		this.employeRemplacant = employeRemplacant;
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
		result = prime * result + ((statusConge == null) ? 0 : statusConge.hashCode());
		result = prime * result + ((dateStatusConge == null) ? 0 : dateStatusConge.hashCode());
		result = prime * result + ((numNoteServiceConge == null) ? 0 : numNoteServiceConge.hashCode());
		result = prime * result + ((typeDemandeConge == null) ? 0 : typeDemandeConge.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((employeRemplacant == null) ? 0 : employeRemplacant.hashCode());
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
		if (statusConge == null) {
			if (other.statusConge != null)
				return false;
		} else if (!statusConge.equals(other.statusConge))
			return false;
		if (dateStatusConge == null) {
			if (other.dateStatusConge != null)
				return false;
		} else if (!dateStatusConge.equals(other.dateStatusConge))
			return false;
		if (numNoteServiceConge == null) {
			if (other.numNoteServiceConge != null)
				return false;
		} else if (!numNoteServiceConge.equals(other.numNoteServiceConge))
			return false;
		if (typeDemandeConge == null) {
			if (other.typeDemandeConge != null)
				return false;
		} else if (!typeDemandeConge.equals(other.typeDemandeConge))
			return false;
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		if (employeRemplacant == null) {
			if (other.employeRemplacant != null)
				return false;
		} else if (!employeRemplacant.equals(other.employeRemplacant))
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
				+ ", infoSupplementaires=" + infoSupplementaires + ", statusConge=" + statusConge + ", dateStatusConge="
				+ dateStatusConge + ", numNoteServiceConge=" + numNoteServiceConge + ", typeDemandeConge="
				+ typeDemandeConge + ", employe=" + employe + ", employeRemplacant=" + employeRemplacant
				+ ", congeCreeLe=" + congeCreeLe + ", congeCreePar=" + congeCreePar + ", congeModifieLe="
				+ congeModifieLe + ", congeModifiePar=" + congeModifiePar + "]";
	}
	@Override
	public int compareTo(Conge o) {
		return id.compareTo(o.getId());
	}
	
}
