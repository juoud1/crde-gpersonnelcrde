package gpersonnelcrde.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//@Table(schema = "crde", name = "autorisation_sortie")
public class AutorisationSortie {
	@Id
   	@GeneratedValue(strategy=GenerationType.IDENTITY)
   	private Long id;
	private LocalDate asDateDepart;
	private LocalDate asDateRetour;
	private String asVille;
	private String asPays;
	private String asNum;
	private String motifSortie;
	private String statusAs;
	private LocalDate dateStatusAs;
	
	@JsonIgnore
	@ManyToOne
	private Conge conge;
	
	private LocalDateTime asCreeLe;
	private String asCreePar;
	private LocalDateTime asModifieLe;
	private String asModifiePar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getAsDateDepart() {
		return asDateDepart;
	}
	public void setAsDateDepart(LocalDate asDateDepart) {
		this.asDateDepart = asDateDepart;
	}
	public LocalDate getAsDateRetour() {
		return asDateRetour;
	}
	public void setAsDateRetour(LocalDate asDateRetour) {
		this.asDateRetour = asDateRetour;
	}
	public String getAsVille() {
		return asVille;
	}
	public void setAsVille(String asVille) {
		this.asVille = asVille;
	}
	public String getAsPays() {
		return asPays;
	}
	public void setAsPays(String asPays) {
		this.asPays = asPays;
	}
	public String getAsNum() {
		return asNum;
	}
	public void setAsNum(String asNum) {
		this.asNum = asNum;
	}
	public String getMotifSortie() {
		return motifSortie;
	}
	public void setMotifSortie(String motifSortie) {
		this.motifSortie = motifSortie;
	}
	public String getStatusAs() {
		return statusAs;
	}
	public void setStatusAs(String statusAs) {
		this.statusAs = statusAs;
	}
	public LocalDate getDateStatusAs() {
		return dateStatusAs;
	}
	public void setDateStatusAs(LocalDate dateStatusAs) {
		this.dateStatusAs = dateStatusAs;
	}
	public Conge getConge() {
		return conge;
	}
	public void setConge(Conge conge) {
		this.conge = conge;
	}
	public LocalDateTime getAsCreeLe() {
		return asCreeLe;
	}
	public void setAsCreeLe(LocalDateTime asCreeLe) {
		this.asCreeLe = asCreeLe;
	}
	public String getAsCreePar() {
		return asCreePar;
	}
	public void setAsCreePar(String asCreePar) {
		this.asCreePar = asCreePar;
	}
	public LocalDateTime getAsModifieLe() {
		return asModifieLe;
	}
	public void setAsModifieLe(LocalDateTime asModifieLe) {
		this.asModifieLe = asModifieLe;
	}
	public String getAsModifiePar() {
		return asModifiePar;
	}
	public void setAsModifiePar(String asModifiePar) {
		this.asModifiePar = asModifiePar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((asDateDepart == null) ? 0 : asDateDepart.hashCode());
		result = prime * result + ((asDateRetour == null) ? 0 : asDateRetour.hashCode());
		result = prime * result + ((asVille == null) ? 0 : asVille.hashCode());
		result = prime * result + ((asPays == null) ? 0 : asPays.hashCode());
		result = prime * result + ((asNum == null) ? 0 : asNum.hashCode());
		result = prime * result + ((motifSortie == null) ? 0 : motifSortie.hashCode());
		result = prime * result + ((statusAs == null) ? 0 : statusAs.hashCode());
		result = prime * result + ((dateStatusAs == null) ? 0 : dateStatusAs.hashCode());
		result = prime * result + ((conge == null) ? 0 : conge.hashCode());
		result = prime * result + ((asCreeLe == null) ? 0 : asCreeLe.hashCode());
		result = prime * result + ((asCreePar == null) ? 0 : asCreePar.hashCode());
		result = prime * result + ((asModifieLe == null) ? 0 : asModifieLe.hashCode());
		result = prime * result + ((asModifiePar == null) ? 0 : asModifiePar.hashCode());
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
		AutorisationSortie other = (AutorisationSortie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (asDateDepart == null) {
			if (other.asDateDepart != null)
				return false;
		} else if (!asDateDepart.equals(other.asDateDepart))
			return false;
		if (asDateRetour == null) {
			if (other.asDateRetour != null)
				return false;
		} else if (!asDateRetour.equals(other.asDateRetour))
			return false;
		if (asVille == null) {
			if (other.asVille != null)
				return false;
		} else if (!asVille.equals(other.asVille))
			return false;
		if (asPays == null) {
			if (other.asPays != null)
				return false;
		} else if (!asPays.equals(other.asPays))
			return false;
		if (asNum == null) {
			if (other.asNum != null)
				return false;
		} else if (!asNum.equals(other.asNum))
			return false;
		if (motifSortie == null) {
			if (other.motifSortie != null)
				return false;
		} else if (!motifSortie.equals(other.motifSortie))
			return false;
		if (statusAs == null) {
			if (other.statusAs != null)
				return false;
		} else if (!statusAs.equals(other.statusAs))
			return false;
		if (dateStatusAs == null) {
			if (other.dateStatusAs != null)
				return false;
		} else if (!dateStatusAs.equals(other.dateStatusAs))
			return false;
		if (conge == null) {
			if (other.conge != null)
				return false;
		} else if (!conge.equals(other.conge))
			return false;
		if (asCreeLe == null) {
			if (other.asCreeLe != null)
				return false;
		} else if (!asCreeLe.equals(other.asCreeLe))
			return false;
		if (asCreePar == null) {
			if (other.asCreePar != null)
				return false;
		} else if (!asCreePar.equals(other.asCreePar))
			return false;
		if (asModifieLe == null) {
			if (other.asModifieLe != null)
				return false;
		} else if (!asModifieLe.equals(other.asModifieLe))
			return false;
		if (asModifiePar == null) {
			if (other.asModifiePar != null)
				return false;
		} else if (!asModifiePar.equals(other.asModifiePar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AutorisationSortie [id=" + id + ", asDateDepart=" + asDateDepart + ", asDateRetour=" + asDateRetour
				+ ", asVille=" + asVille + ", asPays=" + asPays + ", asNum=" + asNum + ", motifSortie=" + motifSortie
				+ ", statusAs=" + statusAs + ", dateStatusAs=" + dateStatusAs + ", conge=" + conge + ", asCreeLe="
				+ asCreeLe + ", asCreePar=" + asCreePar + ", asModifieLe=" + asModifieLe + ", asModifiePar="
				+ asModifiePar + "]";
	}
			
}
