package gpersonnelcrde.domain.entities;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Utilisateur {
	@Id
   	@GeneratedValue
   	private Long id;
	//@NotBlank(message = "required.memberfirstname.not.blank")
	//@Size(min = 3, max = 50, message = "required.memberfirstname.size")
	private String utilisateurFirstname;

	//@NotBlank(message = "required.memberlastName.not.blank")
	//@Size(min = 3, max = 50, message = "required.memberlastName.size")
	private String utilisateurLastname;

	//@Email(message = "required.memberemail.email")
	private String utilisateurEmail;

	private String utilisateurPhoneNumber;

	//@NotBlank(message = "required.memberusername.not.blank")
	//@Size(min = 3, max = 20, message = "required.memberusername.size")
	private String utilisateurNomConnexion;

	//@NotBlank(message = "required.memberpassword.not.blank")
	//@Size(min = 6, max = 12, message = "required.memberpassword.size")
	private String utilisateurMotDePasse;

	//@NotBlank(message = "required.memberrole.not.blank")
	//@Size(min = 3, max = 20, message = "required.memberrole.size")
	private String utilisateurRole;

	//@NotNull(message = "required.memberstatus.not.null")
	//@Size(min = 4, max = 15, message = "required.memberstatus.size")
	private String utilisateurStatus; // accountLocked, disabled, creadentialsExpired, ... À PRENDRE EN COMPTE
	private LocalDateTime utilisateurCreeLe;
	private String utilisateurCreePar;
	private LocalDateTime utilisateurModifieLe;
	private String utilisateurModifiePar;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUtilisateurFirstname() {
		return utilisateurFirstname;
	}
	public void setUtilisateurFirstname(String utilisateurFirstname) {
		this.utilisateurFirstname = utilisateurFirstname;
	}
	public String getUtilisateurLastname() {
		return utilisateurLastname;
	}
	public void setUtilisateurLastname(String utilisateurLastname) {
		this.utilisateurLastname = utilisateurLastname;
	}
	public String getUtilisateurEmail() {
		return utilisateurEmail;
	}
	public void setUtilisateurEmail(String utilisateurEmail) {
		this.utilisateurEmail = utilisateurEmail;
	}
	public String getUtilisateurPhoneNumber() {
		return utilisateurPhoneNumber;
	}
	public void setUtilisateurPhoneNumber(String utilisateurPhoneNumber) {
		this.utilisateurPhoneNumber = utilisateurPhoneNumber;
	}
	public String getUtilisateurNomConnexion() {
		return utilisateurNomConnexion;
	}
	public void setUtilisateurNomConnexion(String utilisateurNomConnexion) {
		this.utilisateurNomConnexion = utilisateurNomConnexion;
	}
	public String getUtilisateurMotDePasse() {
		return utilisateurMotDePasse;
	}
	public void setUtilisateurMotDePasse(String utilisateurMotDePasse) {
		this.utilisateurMotDePasse = utilisateurMotDePasse;
	}
	public String getUtilisateurRole() {
		return utilisateurRole;
	}
	public void setUtilisateurRole(String utilisateurRole) {
		this.utilisateurRole = utilisateurRole;
	}
	public String getUtilisateurStatus() {
		return utilisateurStatus;
	}
	public void setUtilisateurStatus(String utilisateurStatus) {
		this.utilisateurStatus = utilisateurStatus;
	}
	public LocalDateTime getUtilisateurCreeLe() {
		return utilisateurCreeLe;
	}
	public void setUtilisateurCreeLe(LocalDateTime utilisateurCreeLe) {
		this.utilisateurCreeLe = utilisateurCreeLe;
	}
	public String getUtilisateurCreePar() {
		return utilisateurCreePar;
	}
	public void setUtilisateurCreePar(String utilisateurCreePar) {
		this.utilisateurCreePar = utilisateurCreePar;
	}
	public LocalDateTime getUtilisateurModifieLe() {
		return utilisateurModifieLe;
	}
	public void setUtilisateurModifieLe(LocalDateTime utilisateurModifieLe) {
		this.utilisateurModifieLe = utilisateurModifieLe;
	}
	public String getUtilisateurModifiePar() {
		return utilisateurModifiePar;
	}
	public void setUtilisateurModifiePar(String utilisateurModifiePar) {
		this.utilisateurModifiePar = utilisateurModifiePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((utilisateurFirstname == null) ? 0 : utilisateurFirstname.hashCode());
		result = prime * result + ((utilisateurLastname == null) ? 0 : utilisateurLastname.hashCode());
		result = prime * result + ((utilisateurEmail == null) ? 0 : utilisateurEmail.hashCode());
		result = prime * result + ((utilisateurPhoneNumber == null) ? 0 : utilisateurPhoneNumber.hashCode());
		result = prime * result + ((utilisateurNomConnexion == null) ? 0 : utilisateurNomConnexion.hashCode());
		result = prime * result + ((utilisateurRole == null) ? 0 : utilisateurRole.hashCode());
		result = prime * result + ((utilisateurStatus == null) ? 0 : utilisateurStatus.hashCode());
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
		Utilisateur other = (Utilisateur) obj;
		if (utilisateurFirstname == null) {
			if (other.utilisateurFirstname != null)
				return false;
		} else if (!utilisateurFirstname.equals(other.utilisateurFirstname))
			return false;
		if (utilisateurLastname == null) {
			if (other.utilisateurLastname != null)
				return false;
		} else if (!utilisateurLastname.equals(other.utilisateurLastname))
			return false;
		if (utilisateurEmail == null) {
			if (other.utilisateurEmail != null)
				return false;
		} else if (!utilisateurEmail.equals(other.utilisateurEmail))
			return false;
		if (utilisateurPhoneNumber == null) {
			if (other.utilisateurPhoneNumber != null)
				return false;
		} else if (!utilisateurPhoneNumber.equals(other.utilisateurPhoneNumber))
			return false;
		if (utilisateurNomConnexion == null) {
			if (other.utilisateurNomConnexion != null)
				return false;
		} else if (!utilisateurNomConnexion.equals(other.utilisateurNomConnexion))
			return false;
		if (utilisateurRole == null) {
			if (other.utilisateurRole != null)
				return false;
		} else if (!utilisateurRole.equals(other.utilisateurRole))
			return false;
		if (utilisateurStatus == null) {
			if (other.utilisateurStatus != null)
				return false;
		} else if (!utilisateurStatus.equals(other.utilisateurStatus))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Utilisateur [utilisateurFirstname=" + utilisateurFirstname + ", utilisateurLastname="
				+ utilisateurLastname + ", utilisateurEmail=" + utilisateurEmail + ", utilisateurPhoneNumber="
				+ utilisateurPhoneNumber + ", utilisateurNomConnexion=" + utilisateurNomConnexion + ", utilisateurRole="
				+ utilisateurRole + ", utilisateurStatus=" + utilisateurStatus + "]";
	}		
}
