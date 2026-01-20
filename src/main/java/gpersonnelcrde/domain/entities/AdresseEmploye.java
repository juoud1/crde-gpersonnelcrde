package gpersonnelcrde.domain.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AdresseEmploye implements Comparable<AdresseEmploye> {
	@Id
   	@GeneratedValue(strategy=GenerationType.IDENTITY)
   	private Long id;

	private String quartierResidence;
	private String villeResidence;
	private String prefectureResidence;
	private String regionResidence;
	private boolean estAdresseActive;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Employe employe;

	private LocalDateTime adresseCreeLe;
	private String adresseCreePar;
	private LocalDateTime adresseModifieLe;
	private String adresseModifiePar;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuartierResidence() {
		return quartierResidence;
	}

	public void setQuartierResidence(String quartierResidence) {
		this.quartierResidence = quartierResidence;
	}

	public String getVilleResidence() {
		return villeResidence;
	}

	public void setVilleResidence(String villeResidence) {
		this.villeResidence = villeResidence;
	}

	public String getPrefectureResidence() {
		return prefectureResidence;
	}

	public void setPrefectureResidence(String prefectureResidence) {
		this.prefectureResidence = prefectureResidence;
	}

	public String getRegionResidence() {
		return regionResidence;
	}

	public void setRegionResidence(String regionResidence) {
		this.regionResidence = regionResidence;
	}

	public boolean isEstAdresseActive() {
		return estAdresseActive;
	}

	public void setEstAdresseActive(boolean estAdresseActive) {
		this.estAdresseActive = estAdresseActive;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public LocalDateTime getAdresseCreeLe() {
		return adresseCreeLe;
	}

	public void setAdresseCreeLe(LocalDateTime adresseCreeLe) {
		this.adresseCreeLe = adresseCreeLe;
	}

	public String getAdresseCreePar() {
		return adresseCreePar;
	}

	public void setAdresseCreePar(String adresseCreePar) {
		this.adresseCreePar = adresseCreePar;
	}

	public LocalDateTime getAdresseModifieLe() {
		return adresseModifieLe;
	}

	public void setAdresseModifieLe(LocalDateTime adresseModifieLe) {
		this.adresseModifieLe = adresseModifieLe;
	}

	public String getAdresseModifiePar() {
		return adresseModifiePar;
	}

	public void setAdresseModifiePar(String adresseModifiePar) {
		this.adresseModifiePar = adresseModifiePar;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quartierResidence == null) ? 0 : quartierResidence.hashCode());
		result = prime * result + ((villeResidence == null) ? 0 : villeResidence.hashCode());
		result = prime * result + ((prefectureResidence == null) ? 0 : prefectureResidence.hashCode());
		result = prime * result + ((regionResidence == null) ? 0 : regionResidence.hashCode());
		result = prime * result + (estAdresseActive ? 1231 : 1237);
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((adresseCreeLe == null) ? 0 : adresseCreeLe.hashCode());
		result = prime * result + ((adresseCreePar == null) ? 0 : adresseCreePar.hashCode());
		result = prime * result + ((adresseModifieLe == null) ? 0 : adresseModifieLe.hashCode());
		result = prime * result + ((adresseModifiePar == null) ? 0 : adresseModifiePar.hashCode());
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
		AdresseEmploye other = (AdresseEmploye) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quartierResidence == null) {
			if (other.quartierResidence != null)
				return false;
		} else if (!quartierResidence.equals(other.quartierResidence))
			return false;
		if (villeResidence == null) {
			if (other.villeResidence != null)
				return false;
		} else if (!villeResidence.equals(other.villeResidence))
			return false;
		if (prefectureResidence == null) {
			if (other.prefectureResidence != null)
				return false;
		} else if (!prefectureResidence.equals(other.prefectureResidence))
			return false;
		if (regionResidence == null) {
			if (other.regionResidence != null)
				return false;
		} else if (!regionResidence.equals(other.regionResidence))
			return false;
		if (estAdresseActive != other.estAdresseActive)
			return false;
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		if (adresseCreeLe == null) {
			if (other.adresseCreeLe != null)
				return false;
		} else if (!adresseCreeLe.equals(other.adresseCreeLe))
			return false;
		if (adresseCreePar == null) {
			if (other.adresseCreePar != null)
				return false;
		} else if (!adresseCreePar.equals(other.adresseCreePar))
			return false;
		if (adresseModifieLe == null) {
			if (other.adresseModifieLe != null)
				return false;
		} else if (!adresseModifieLe.equals(other.adresseModifieLe))
			return false;
		if (adresseModifiePar == null) {
			if (other.adresseModifiePar != null)
				return false;
		} else if (!adresseModifiePar.equals(other.adresseModifiePar))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "AdresseEmploye [id=" + id + ", quartierResidence=" + quartierResidence + ", villeResidence="
				+ villeResidence + ", prefectureResidence=" + prefectureResidence + ", regionResidence="
				+ regionResidence + ", estAdresseActive=" + estAdresseActive + ", employe=" + employe
				+ ", adresseCreeLe=" + adresseCreeLe + ", adresseCreePar=" + adresseCreePar + ", adresseModifieLe="
				+ adresseModifieLe + ", adresseModifiePar=" + adresseModifiePar + "]";
	}

	@Override
	public int compareTo(AdresseEmploye o) {
		return id.compareTo(o.getId());
	}
}
