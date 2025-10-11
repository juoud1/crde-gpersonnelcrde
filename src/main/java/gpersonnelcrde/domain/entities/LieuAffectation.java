package gpersonnelcrde.domain.entities;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LieuAffectation {
	@Id
   	@GeneratedValue
   	private Long id;
	private String lieuAffectCode;
	private String lieuAffect;
	private LocalDateTime lieuAffectCreeLe;
	private String lieuAffectCreePar;
	private LocalDateTime lieuAffectModifieLe;
	private String lieuAffectModifiePar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLieuAffectCode() {
		return lieuAffectCode;
	}
	public void setLieuAffectCode(String lieuAffectCode) {
		this.lieuAffectCode = lieuAffectCode;
	}
	public String getLieuAffect() {
		return lieuAffect;
	}
	public void setLieuAffect(String lieuAffect) {
		this.lieuAffect = lieuAffect;
	}
	public LocalDateTime getLieuAffectCreeLe() {
		return lieuAffectCreeLe;
	}
	public void setLieuAffectCreeLe(LocalDateTime lieuAffectCreeLe) {
		this.lieuAffectCreeLe = lieuAffectCreeLe;
	}
	public String getLieuAffectCreePar() {
		return lieuAffectCreePar;
	}
	public void setLieuAffectCreePar(String lieuAffectCreePar) {
		this.lieuAffectCreePar = lieuAffectCreePar;
	}
	public LocalDateTime getLieuAffectModifieLe() {
		return lieuAffectModifieLe;
	}
	public void setLieuAffectModifieLe(LocalDateTime lieuAffectModifieLe) {
		this.lieuAffectModifieLe = lieuAffectModifieLe;
	}
	public String getLieuAffectModifiePar() {
		return lieuAffectModifiePar;
	}
	public void setLieuAffectModifiePar(String lieuAffectModifiePar) {
		this.lieuAffectModifiePar = lieuAffectModifiePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lieuAffectCode == null) ? 0 : lieuAffectCode.hashCode());
		result = prime * result + ((lieuAffect == null) ? 0 : lieuAffect.hashCode());
		result = prime * result + ((lieuAffectCreeLe == null) ? 0 : lieuAffectCreeLe.hashCode());
		result = prime * result + ((lieuAffectCreePar == null) ? 0 : lieuAffectCreePar.hashCode());
		result = prime * result + ((lieuAffectModifieLe == null) ? 0 : lieuAffectModifieLe.hashCode());
		result = prime * result + ((lieuAffectModifiePar == null) ? 0 : lieuAffectModifiePar.hashCode());
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
		LieuAffectation other = (LieuAffectation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lieuAffectCode == null) {
			if (other.lieuAffectCode != null)
				return false;
		} else if (!lieuAffectCode.equals(other.lieuAffectCode))
			return false;
		if (lieuAffect == null) {
			if (other.lieuAffect != null)
				return false;
		} else if (!lieuAffect.equals(other.lieuAffect))
			return false;
		if (lieuAffectCreeLe == null) {
			if (other.lieuAffectCreeLe != null)
				return false;
		} else if (!lieuAffectCreeLe.equals(other.lieuAffectCreeLe))
			return false;
		if (lieuAffectCreePar == null) {
			if (other.lieuAffectCreePar != null)
				return false;
		} else if (!lieuAffectCreePar.equals(other.lieuAffectCreePar))
			return false;
		if (lieuAffectModifieLe == null) {
			if (other.lieuAffectModifieLe != null)
				return false;
		} else if (!lieuAffectModifieLe.equals(other.lieuAffectModifieLe))
			return false;
		if (lieuAffectModifiePar == null) {
			if (other.lieuAffectModifiePar != null)
				return false;
		} else if (!lieuAffectModifiePar.equals(other.lieuAffectModifiePar))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LieuAffectation [id=" + id + ", lieuAffectCode=" + lieuAffectCode + ", lieuAffect=" + lieuAffect
				+ ", lieuAffectCreeLe=" + lieuAffectCreeLe + ", lieuAffectCreePar=" + lieuAffectCreePar
				+ ", lieuAffectModifieLe=" + lieuAffectModifieLe + ", lieuAffectModifiePar=" + lieuAffectModifiePar
				+ "]";
	}
	
}
