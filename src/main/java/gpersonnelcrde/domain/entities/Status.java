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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((statusCreeLe == null) ? 0 : statusCreeLe.hashCode());
		result = prime * result + ((statusCreePar == null) ? 0 : statusCreePar.hashCode());
		result = prime * result + ((statusModifieLe == null) ? 0 : statusModifieLe.hashCode());
		result = prime * result + ((statusModifiePar == null) ? 0 : statusModifiePar.hashCode());
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
		Status other = (Status) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
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
		if (statusCreeLe == null) {
			if (other.statusCreeLe != null)
				return false;
		} else if (!statusCreeLe.equals(other.statusCreeLe))
			return false;
		if (statusCreePar == null) {
			if (other.statusCreePar != null)
				return false;
		} else if (!statusCreePar.equals(other.statusCreePar))
			return false;
		if (statusModifieLe == null) {
			if (other.statusModifieLe != null)
				return false;
		} else if (!statusModifieLe.equals(other.statusModifieLe))
			return false;
		if (statusModifiePar == null) {
			if (other.statusModifiePar != null)
				return false;
		} else if (!statusModifiePar.equals(other.statusModifiePar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", statusCode=" + statusCode + ", status=" + status + ", statusCreeLe="
				+ statusCreeLe + ", statusCreePar=" + statusCreePar + ", statusModifieLe=" + statusModifieLe
				+ ", statusModifiePar=" + statusModifiePar + "]";
	}
		
}
