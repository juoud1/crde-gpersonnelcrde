package gpersonnelcrde.domain.dto;

public class UtilisateurDto {
	private String utilisateurFirstname;
	private String utilisateurLastname;
	private String utilisateurEmail;
	private String utilisateurPhoneNumber;
	private String utilisateurNomConnexion;
	private String utilisateurMotDePasse;
	private String utilisateurRole;
	private String utilisateurStatus;
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
		UtilisateurDto other = (UtilisateurDto) obj;
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
		return "UtilisateurDto [utilisateurFirstname=" + utilisateurFirstname + ", utilisateurLastname="
				+ utilisateurLastname + ", utilisateurEmail=" + utilisateurEmail + ", utilisateurPhoneNumber="
				+ utilisateurPhoneNumber + ", utilisateurNomConnexion=" + utilisateurNomConnexion + ", utilisateurRole="
				+ utilisateurRole + ", utilisateurStatus=" + utilisateurStatus + "]";
	}			
}
