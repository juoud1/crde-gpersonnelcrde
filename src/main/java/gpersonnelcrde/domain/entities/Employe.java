package gpersonnelcrde.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Employe {
	@Id
   	@GeneratedValue(strategy=GenerationType.IDENTITY)
   	private Long id;
	private String empMatricule;
	private String empNom;
	private String empPren;
	private String empCivilite;
	private String empUrlphoto;
	private String empUrlsignature;
	private String empTelephone;
	private String empEmail;
	private String numNoteService;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private TypeEmploye typeEmploye;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Status empStatus;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private LieuAffectation empLieuAffectation;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Fonction empFonction;

	private String referenceDecretEntree;
	private LocalDate dateDecretEntree;
	private String referenceDecretSortie;
	private LocalDate dateDecretSortie;

	@JsonIgnore
	@OneToMany(mappedBy = "id.employe", cascade = {jakarta.persistence.CascadeType.PERSIST, jakarta.persistence.CascadeType.MERGE})
	@Cascade(CascadeType.REFRESH)
	private List<MissionEmploye> empMissions = new ArrayList<>();

	private LocalDateTime empCreeLe;
	private String empCreePar;
	private LocalDateTime empModifieLe;
	private String empModifiePar;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getEmpUrlphoto() {
		return empUrlphoto;
	}
	public void setEmpUrlphoto(String empUrlphoto) {
		this.empUrlphoto = empUrlphoto;
	}
	public String getEmpUrlsignature() {
		return empUrlsignature;
	}
	public void setEmpUrlsignature(String empUrlsignature) {
		this.empUrlsignature = empUrlsignature;
	}
	public String getEmpTelephone() {
		return empTelephone;
	}
	public void setEmpTelephone(String empTelephone) {
		this.empTelephone = empTelephone;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getNumNoteService() {
		return numNoteService;
	}
	public void setNumNoteService(String numNoteService) {
		this.numNoteService = numNoteService;
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
	public String getReferenceDecretEntree() {
		return referenceDecretEntree;
	}
	public void setReferenceDecretEntree(String referenceDecretEntree) {
		this.referenceDecretEntree = referenceDecretEntree;
	}
	public LocalDate getDateDecretEntree() {
		return dateDecretEntree;
	}
	public void setDateDecretEntree(LocalDate dateDecretEntree) {
		this.dateDecretEntree = dateDecretEntree;
	}
	public String getReferenceDecretSortie() {
		return referenceDecretSortie;
	}
	public void setReferenceDecretSortie(String referenceDecretSortie) {
		this.referenceDecretSortie = referenceDecretSortie;
	}
	public LocalDate getDateDecretSortie() {
		return dateDecretSortie;
	}
	public void setDateDecretSortie(LocalDate dateDecretSortie) {
		this.dateDecretSortie = dateDecretSortie;
	}
	public List<MissionEmploye> getEmpMissions() {
		return empMissions;
	}
	public void setEmpMissions(List<MissionEmploye> empMissions) {
		this.empMissions = empMissions;
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
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((empMatricule == null) ? 0 : empMatricule.hashCode());
		result = prime * result + ((empNom == null) ? 0 : empNom.hashCode());
		result = prime * result + ((empPren == null) ? 0 : empPren.hashCode());
		result = prime * result + ((empCivilite == null) ? 0 : empCivilite.hashCode());
		result = prime * result + ((empUrlphoto == null) ? 0 : empUrlphoto.hashCode());
		result = prime * result + ((empUrlsignature == null) ? 0 : empUrlsignature.hashCode());
		result = prime * result + ((empTelephone == null) ? 0 : empTelephone.hashCode());
		result = prime * result + ((empEmail == null) ? 0 : empEmail.hashCode());
		result = prime * result + ((numNoteService == null) ? 0 : numNoteService.hashCode());
		result = prime * result + ((typeEmploye == null) ? 0 : typeEmploye.hashCode());
		result = prime * result + ((empStatus == null) ? 0 : empStatus.hashCode());
		result = prime * result + ((empLieuAffectation == null) ? 0 : empLieuAffectation.hashCode());
		result = prime * result + ((empFonction == null) ? 0 : empFonction.hashCode());
		result = prime * result + ((referenceDecretEntree == null) ? 0 : referenceDecretEntree.hashCode());
		result = prime * result + ((dateDecretEntree == null) ? 0 : dateDecretEntree.hashCode());
		result = prime * result + ((referenceDecretSortie == null) ? 0 : referenceDecretSortie.hashCode());
		result = prime * result + ((dateDecretSortie == null) ? 0 : dateDecretSortie.hashCode());
		result = prime * result + ((empMissions == null) ? 0 : empMissions.hashCode());
		result = prime * result + ((empCreeLe == null) ? 0 : empCreeLe.hashCode());
		result = prime * result + ((empCreePar == null) ? 0 : empCreePar.hashCode());
		result = prime * result + ((empModifieLe == null) ? 0 : empModifieLe.hashCode());
		result = prime * result + ((empModifiePar == null) ? 0 : empModifiePar.hashCode());
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
		Employe other = (Employe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
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
		if (empUrlphoto == null) {
			if (other.empUrlphoto != null)
				return false;
		} else if (!empUrlphoto.equals(other.empUrlphoto))
			return false;
		if (empUrlsignature == null) {
			if (other.empUrlsignature != null)
				return false;
		} else if (!empUrlsignature.equals(other.empUrlsignature))
			return false;
		if (empTelephone == null) {
			if (other.empTelephone != null)
				return false;
		} else if (!empTelephone.equals(other.empTelephone))
			return false;
		if (empEmail == null) {
			if (other.empEmail != null)
				return false;
		} else if (!empEmail.equals(other.empEmail))
			return false;
		if (numNoteService == null) {
			if (other.numNoteService != null)
				return false;
		} else if (!numNoteService.equals(other.numNoteService))
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
		if (referenceDecretEntree == null) {
			if (other.referenceDecretEntree != null)
				return false;
		} else if (!referenceDecretEntree.equals(other.referenceDecretEntree))
			return false;
		if (dateDecretEntree == null) {
			if (other.dateDecretEntree != null)
				return false;
		} else if (!dateDecretEntree.equals(other.dateDecretEntree))
			return false;
		if (referenceDecretSortie == null) {
			if (other.referenceDecretSortie != null)
				return false;
		} else if (!referenceDecretSortie.equals(other.referenceDecretSortie))
			return false;
		if (dateDecretSortie == null) {
			if (other.dateDecretSortie != null)
				return false;
		} else if (!dateDecretSortie.equals(other.dateDecretSortie))
			return false;
		if (empMissions == null) {
			if (other.empMissions != null)
				return false;
		} else if (!empMissions.equals(other.empMissions))
			return false;
		if (empCreeLe == null) {
			if (other.empCreeLe != null)
				return false;
		} else if (!empCreeLe.equals(other.empCreeLe))
			return false;
		if (empCreePar == null) {
			if (other.empCreePar != null)
				return false;
		} else if (!empCreePar.equals(other.empCreePar))
			return false;
		if (empModifieLe == null) {
			if (other.empModifieLe != null)
				return false;
		} else if (!empModifieLe.equals(other.empModifieLe))
			return false;
		if (empModifiePar == null) {
			if (other.empModifiePar != null)
				return false;
		} else if (!empModifiePar.equals(other.empModifiePar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employe [id=" + id + ", empMatricule=" + empMatricule + ", empNom=" + empNom + ", empPren=" + empPren
				+ ", empCivilite=" + empCivilite + ", empUrlphoto=" + empUrlphoto + ", empUrlsignature="
				+ empUrlsignature + ", empTelephone=" + empTelephone + ", empEmail=" + empEmail + ", numNoteService="
				+ numNoteService + ", typeEmploye=" + typeEmploye + ", empStatus=" + empStatus + ", empLieuAffectation="
				+ empLieuAffectation + ", empFonction=" + empFonction + ", referenceDecretEntree="
				+ referenceDecretEntree + ", dateDecretEntree=" + dateDecretEntree + ", referenceDecretSortie="
				+ referenceDecretSortie + ", dateDecretSortie=" + dateDecretSortie + ", empMissions=" + empMissions
				+ ", empCreeLe=" + empCreeLe + ", empCreePar=" + empCreePar + ", empModifieLe=" + empModifieLe
				+ ", empModifiePar=" + empModifiePar + "]";
	}
					
}
