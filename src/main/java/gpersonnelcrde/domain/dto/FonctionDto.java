package gpersonnelcrde.domain.dto;

public class FonctionDto {
	private String fonctionCode;
	private String fonction;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fonctionCode == null) ? 0 : fonctionCode.hashCode());
		result = prime * result + ((fonction == null) ? 0 : fonction.hashCode());
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
		FonctionDto other = (FonctionDto) obj;
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
		return "FonctionDto [fonctionCode=" + fonctionCode + ", fonction=" + fonction + "]";
	}
}
