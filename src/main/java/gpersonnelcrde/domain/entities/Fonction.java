package gpersonnelcrde.domain.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(schema = "crde", name = "fonction")
public class Fonction implements Comparable<Fonction> {
	@Id
   	@GeneratedValue(strategy=GenerationType.IDENTITY)
   	private Long id;
	private String fonctionCode;
	private String fonction;
	private LocalDateTime fonctionCreeLe;
	private String fonctionCreePar;
	private LocalDateTime fonctionModifieLe;
	private String fonctionModifiePar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((fonctionCode == null) ? 0 : fonctionCode.hashCode());
		result = prime * result + ((fonction == null) ? 0 : fonction.hashCode());
		result = prime * result + ((fonctionCreeLe == null) ? 0 : fonctionCreeLe.hashCode());
		result = prime * result + ((fonctionCreePar == null) ? 0 : fonctionCreePar.hashCode());
		result = prime * result + ((fonctionModifieLe == null) ? 0 : fonctionModifieLe.hashCode());
		result = prime * result + ((fonctionModifiePar == null) ? 0 : fonctionModifiePar.hashCode());
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
		Fonction other = (Fonction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
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
		if (fonctionCreeLe == null) {
			if (other.fonctionCreeLe != null)
				return false;
		} else if (!fonctionCreeLe.equals(other.fonctionCreeLe))
			return false;
		if (fonctionCreePar == null) {
			if (other.fonctionCreePar != null)
				return false;
		} else if (!fonctionCreePar.equals(other.fonctionCreePar))
			return false;
		if (fonctionModifieLe == null) {
			if (other.fonctionModifieLe != null)
				return false;
		} else if (!fonctionModifieLe.equals(other.fonctionModifieLe))
			return false;
		if (fonctionModifiePar == null) {
			if (other.fonctionModifiePar != null)
				return false;
		} else if (!fonctionModifiePar.equals(other.fonctionModifiePar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fonction [id=" + id + ", fonctionCode=" + fonctionCode + ", fonction=" + fonction + ", fonctionCreeLe="
				+ fonctionCreeLe + ", fonctionCreePar=" + fonctionCreePar + ", fonctionModifieLe=" + fonctionModifieLe
				+ ", fonctionModifiePar=" + fonctionModifiePar + "]";
	}
	
	@Override
	public int compareTo(Fonction o) {
		return id.compareTo(o.getId());
	}
	
}
