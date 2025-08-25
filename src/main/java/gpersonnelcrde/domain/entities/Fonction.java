package gpersonnelcrde.domain.entities;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Fonction {
	@Id
   	@GeneratedValue
   	private Long id;
	private String fonctionCode;
	private String fonction;
	private LocalDateTime fonctionCreeLe;
	private String fonctionCreePar;
	private LocalDateTime fonctionModifieLe;
	private String fonctionModifiePar;
	
	public String getFonctionCode() {
		return fonctionCode;
	}
	public void setFonctionCode(String fonctionCode) {
		this.fonctionCode = fonctionCode;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public LocalDateTime getFonctionCreeLe() {
		return fonctionCreeLe;
	}
	public void setFonctionCreeLe(LocalDateTime fonctionCreeLe) {
		this.fonctionCreeLe = fonctionCreeLe;
	}
	public String getFonctionCreePar() {
		return fonctionCreePar;
	}
	public void setFonctionCreePar(String fonctionCreePar) {
		this.fonctionCreePar = fonctionCreePar;
	}
	public LocalDateTime getFonctionModifieLe() {
		return fonctionModifieLe;
	}
	public void setFonctionModifieLe(LocalDateTime fonctionModifieLe) {
		this.fonctionModifieLe = fonctionModifieLe;
	}
	public String getFonctionModifiePar() {
		return fonctionModifiePar;
	}
	public void setFonctionModifiePar(String fonctionModifiePar) {
		this.fonctionModifiePar = fonctionModifiePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fonctionCode == null) ? 0 : fonctionCode.hashCode());
		result = prime * result + ((fonction == null) ? 0 : fonction.hashCode());
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
		Fonction other = (Fonction) obj;
		if (fonctionCode == null) {
			if (other.fonctionCode != null)
				return false;
		} else if (!fonctionCode.equals(other.fonctionCode))
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
		return "Fonction [fonctionCode=" + fonctionCode + ", fonction=" + fonction + "]";
	}
}
