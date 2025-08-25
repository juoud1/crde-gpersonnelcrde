package gpersonnelcrde.domain.dto;

public class TypeEmployeDto {
	private String typeEmpCode;
	private String typeEmp;
	public String getTypeEmpCode() {
		return typeEmpCode;
	}
	public void setTypeEmpCode(String typeEmpCode) {
		this.typeEmpCode = typeEmpCode;
	}
	public String getTypeEmp() {
		return typeEmp;
	}
	public void setTypeEmp(String typeEmp) {
		this.typeEmp = typeEmp;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeEmpCode == null) ? 0 : typeEmpCode.hashCode());
		result = prime * result + ((typeEmp == null) ? 0 : typeEmp.hashCode());
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
		TypeEmployeDto other = (TypeEmployeDto) obj;
		if (typeEmpCode == null) {
			if (other.typeEmpCode != null)
				return false;
		} else if (!typeEmpCode.equals(other.typeEmpCode))
			return false;
		if (typeEmp == null) {
			if (other.typeEmp != null)
				return false;
		} else if (!typeEmp.equals(other.typeEmp))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TypeEmployeDto [typeEmpCode=" + typeEmpCode + ", typeEmp=" + typeEmp + "]";
	}
}
