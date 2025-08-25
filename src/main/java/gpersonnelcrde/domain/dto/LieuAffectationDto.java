package gpersonnelcrde.domain.dto;

public class LieuAffectationDto {
	private String lieuAffectCode;
	private String lieuAffect;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lieuAffectCode == null) ? 0 : lieuAffectCode.hashCode());
		result = prime * result + ((lieuAffect == null) ? 0 : lieuAffect.hashCode());
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
		LieuAffectationDto other = (LieuAffectationDto) obj;
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
		return true;
	}
	@Override
	public String toString() {
		return "LieuAffectationDto [lieuAffectCode=" + lieuAffectCode + ", lieuAffect=" + lieuAffect + "]";
	}	
}
