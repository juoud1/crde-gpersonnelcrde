package gpersonnelcrde.domain.entities;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Employe {
	@Id
   	@GeneratedValue
   	private Long id;
	private String empMatricule;
	private String empNom;
	private String empPren;
	private String empCivilite;

	@JsonIgnore
	@ManyToOne
	private TypeEmploye typeEmploye;

	@JsonIgnore
	@ManyToOne
	private Status empStatus;

	@JsonIgnore
	@ManyToOne
	private LieuAffectation empLieuAffectation;

	@JsonIgnore
	@ManyToOne
	private Fonction empFonction;

	private LocalDateTime empCreeLe;
	private String empCreePar;
	private LocalDateTime empModifieLe;
	private String empModifiePar;
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
	public TypeEmploye getTypeEmploye() {
		return typeEmploye;
	}
	public void setTypeEmploye(TypeEmploye typeEmploye) {
		this.typeEmploye = typeEmploye;
	}
	public Status getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(Status empStatus) {
		this.empStatus = empStatus;
	}
	public LieuAffectation getEmpLieuAffectation() {
		return empLieuAffectation;
	}
	public void setEmpLieuAffectation(LieuAffectation empLieuAffectation) {
		this.empLieuAffectation = empLieuAffectation;
	}
	public Fonction getEmpFonction() {
		return empFonction;
	}
	public void setEmpFonction(Fonction empFonction) {
		this.empFonction = empFonction;
	}
	public LocalDateTime getEmpCreeLe() {
		return empCreeLe;
	}
	public void setEmpCreeLe(LocalDateTime empCreeLe) {
		this.empCreeLe = empCreeLe;
	}
	public String getEmpCreePar() {
		return empCreePar;
	}
	public void setEmpCreePar(String empCreePar) {
		this.empCreePar = empCreePar;
	}
	public LocalDateTime getEmpModifieLe() {
		return empModifieLe;
	}
	public void setEmpModifieLe(LocalDateTime empModifieLe) {
		this.empModifieLe = empModifieLe;
	}
	public String getEmpModifiePar() {
		return empModifiePar;
	}
	public void setEmpModifiePar(String empModifiePar) {
		this.empModifiePar = empModifiePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((empMatricule == null) ? 0 : empMatricule.hashCode());
		result = prime * result + ((empNom == null) ? 0 : empNom.hashCode());
		result = prime * result + ((empPren == null) ? 0 : empPren.hashCode());
		result = prime * result + ((empCivilite == null) ? 0 : empCivilite.hashCode());
		result = prime * result + ((typeEmploye == null) ? 0 : typeEmploye.hashCode());
		result = prime * result + ((empStatus == null) ? 0 : empStatus.hashCode());
		result = prime * result + ((empLieuAffectation == null) ? 0 : empLieuAffectation.hashCode());
		result = prime * result + ((empFonction == null) ? 0 : empFonction.hashCode());
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
		Employe other = (Employe) obj;
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
		if (empStatus == null) {
			if (other.empStatus != null)
				return false;
		} else if (!empStatus.equals(other.empStatus))
			return false;
		if (empLieuAffectation == null) {
			if (other.empLieuAffectation != null)
				return false;
		} else if (!empLieuAffectation.equals(other.empLieuAffectation))
			return false;
		if (empFonction == null) {
			if (other.empFonction != null)
				return false;
		} else if (!empFonction.equals(other.empFonction))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employe [empMatricule=" + empMatricule + ", empNom=" + empNom + ", empPren=" + empPren
				+ ", empCivilite=" + empCivilite + ", typeEmploye=" + typeEmploye + ", empStatus=" + empStatus
				+ ", empLieuAffectation=" + empLieuAffectation + ", empFonction=" + empFonction + "]";
	}		
}
