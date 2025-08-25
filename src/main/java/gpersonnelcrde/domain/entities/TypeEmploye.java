package gpersonnelcrde.domain.entities;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TypeEmploye {
	@Id
   	@GeneratedValue
   	private Long id;
	private String typeEmpCode;
	private String typeEmp;
	private LocalDateTime typeEmpCreeLe;
	private String typeEmpCreePar;
	private LocalDateTime typeEmpModifieLe;
	private String typeEmpModifiePar;
	public String getTypeEmpCode() {
		return typeEmpCode;
	}
	public void setTypeEmpCode(String typeEmpCode) {
		this.typeEmpCode = typeEmpCode;
	}
	public String getTypeEmp() {
		return typeEmp;
	}
	public void setTypeEmp(String typeEmp) {
		this.typeEmp = typeEmp;
	}
	public LocalDateTime getTypeEmpCreeLe() {
		return typeEmpCreeLe;
	}
	public void setTypeEmpCreeLe(LocalDateTime typeEmpCreeLe) {
		this.typeEmpCreeLe = typeEmpCreeLe;
	}
	public String getTypeEmpCreePar() {
		return typeEmpCreePar;
	}
	public void setTypeEmpCreePar(String typeEmpCreePar) {
		this.typeEmpCreePar = typeEmpCreePar;
	}
	public LocalDateTime getTypeEmpModifieLe() {
		return typeEmpModifieLe;
	}
	public void setTypeEmpModifieLe(LocalDateTime typeEmpModifieLe) {
		this.typeEmpModifieLe = typeEmpModifieLe;
	}
	public String getTypeEmpModifiePar() {
		return typeEmpModifiePar;
	}
	public void setTypeEmpModifiePar(String typeEmpModifiePar) {
		this.typeEmpModifiePar = typeEmpModifiePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeEmpCode == null) ? 0 : typeEmpCode.hashCode());
		result = prime * result + ((typeEmp == null) ? 0 : typeEmp.hashCode());
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
		TypeEmploye other = (TypeEmploye) obj;
		if (typeEmpCode == null) {
			if (other.typeEmpCode != null)
				return false;
		} else if (!typeEmpCode.equals(other.typeEmpCode))
			return false;
		if (typeEmp == null) {
			if (other.typeEmp != null)
				return false;
		} else if (!typeEmp.equals(other.typeEmp))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TypeEmploye [typeEmpCode=" + typeEmpCode + ", typeEmp=" + typeEmp + "]";
	}
}
