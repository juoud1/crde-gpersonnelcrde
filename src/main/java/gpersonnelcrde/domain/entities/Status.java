package gpersonnelcrde.domain.entities;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Status {
	@Id
   	@GeneratedValue
   	private Long id;
	private String statusCode;
	private String status;
	private LocalDateTime statusCreeLe;
	private String statusCreePar;
	private LocalDateTime statusModifieLe;
	private String statusModifiePar;

	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getStatusCreeLe() {
		return statusCreeLe;
	}
	public void setStatusCreeLe(LocalDateTime statusCreeLe) {
		this.statusCreeLe = statusCreeLe;
	}
	public String getStatusCreePar() {
		return statusCreePar;
	}
	public void setStatusCreePar(String statusCreePar) {
		this.statusCreePar = statusCreePar;
	}
	public LocalDateTime getStatusModifieLe() {
		return statusModifieLe;
	}
	public void setStatusModifieLe(LocalDateTime statusModifieLe) {
		this.statusModifieLe = statusModifieLe;
	}
	public String getStatusModifiePar() {
		return statusModifiePar;
	}
	public void setStatusModifiePar(String statusModifiePar) {
		this.statusModifiePar = statusModifiePar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Status other = (Status) obj;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [statusCode=" + statusCode + ", status=" + status + "]";
	}
	
}
