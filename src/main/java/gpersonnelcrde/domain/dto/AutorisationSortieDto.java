package gpersonnelcrde.domain.dto;

import java.time.LocalDate;

public class AutorisationSortieDto {
	private LocalDate dateDepartAutorisatSortie;
	private LocalDate dateRetourAutorisatSortie;
	private String villeAutorisatSortie;
	private String paysAutorisatSortie;
	private String numAutorisatSortie;
	private String numAutSortie;
	private String motifSortie;
	
	private String statusAutorisatSortie;
	private LocalDate dateStatusAutorisatSortie;

	private String numNoteServiceConge;
	private String numConge;
	
	public LocalDate getDateDepartAutorisatSortie() {
		return dateDepartAutorisatSortie;
	}
	public void setDateDepartAutorisatSortie(LocalDate dateDepartAutorisatSortie) {
		this.dateDepartAutorisatSortie = dateDepartAutorisatSortie;
	}
	public LocalDate getDateRetourAutorisatSortie() {
		return dateRetourAutorisatSortie;
	}
	public void setDateRetourAutorisatSortie(LocalDate dateRetourAutorisatSortie) {
		this.dateRetourAutorisatSortie = dateRetourAutorisatSortie;
	}
	public String getVilleAutorisatSortie() {
		return villeAutorisatSortie;
	}
	public void setVilleAutorisatSortie(String villeAutorisatSortie) {
		this.villeAutorisatSortie = villeAutorisatSortie;
	}
	public String getPaysAutorisatSortie() {
		return paysAutorisatSortie;
	}
	public void setPaysAutorisatSortie(String paysAutorisatSortie) {
		this.paysAutorisatSortie = paysAutorisatSortie;
	}
	public String getNumAutorisatSortie() {
		return numAutorisatSortie;
	}
	public void setNumAutorisatSortie(String numAutorisatSortie) {
		this.numAutorisatSortie = numAutorisatSortie;
	}
	public String getNumAutSortie() {
		return numAutSortie;
	}
	public void setNumAutSortie(String numAutSortie) {
		this.numAutSortie = numAutSortie;
	}
	public String getMotifSortie() {
		return motifSortie;
	}
	public void setMotifSortie(String motifSortie) {
		this.motifSortie = motifSortie;
	}
	public String getStatusAutorisatSortie() {
		return statusAutorisatSortie;
	}
	public void setStatusAutorisatSortie(String statusAutorisatSortie) {
		this.statusAutorisatSortie = statusAutorisatSortie;
	}
	public LocalDate getDateStatusAutorisatSortie() {
		return dateStatusAutorisatSortie;
	}
	public void setDateStatusAutorisatSortie(LocalDate dateStatusAutorisatSortie) {
		this.dateStatusAutorisatSortie = dateStatusAutorisatSortie;
	}
	public String getNumNoteServiceConge() {
		return numNoteServiceConge;
	}
	public void setNumNoteServiceConge(String numNoteServiceConge) {
		this.numNoteServiceConge = numNoteServiceConge;
	}
	public String getNumConge() {
		return numConge;
	}
	public void setNumConge(String numConge) {
		this.numConge = numConge;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDepartAutorisatSortie == null) ? 0 : dateDepartAutorisatSortie.hashCode());
		result = prime * result + ((dateRetourAutorisatSortie == null) ? 0 : dateRetourAutorisatSortie.hashCode());
		result = prime * result + ((villeAutorisatSortie == null) ? 0 : villeAutorisatSortie.hashCode());
		result = prime * result + ((paysAutorisatSortie == null) ? 0 : paysAutorisatSortie.hashCode());
		result = prime * result + ((numAutorisatSortie == null) ? 0 : numAutorisatSortie.hashCode());
		result = prime * result + ((numAutSortie == null) ? 0 : numAutSortie.hashCode());
		result = prime * result + ((motifSortie == null) ? 0 : motifSortie.hashCode());
		result = prime * result + ((statusAutorisatSortie == null) ? 0 : statusAutorisatSortie.hashCode());
		result = prime * result + ((dateStatusAutorisatSortie == null) ? 0 : dateStatusAutorisatSortie.hashCode());
		result = prime * result + ((numNoteServiceConge == null) ? 0 : numNoteServiceConge.hashCode());
		result = prime * result + ((numConge == null) ? 0 : numConge.hashCode());
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
		AutorisationSortieDto other = (AutorisationSortieDto) obj;
		if (dateDepartAutorisatSortie == null) {
			if (other.dateDepartAutorisatSortie != null)
				return false;
		} else if (!dateDepartAutorisatSortie.equals(other.dateDepartAutorisatSortie))
			return false;
		if (dateRetourAutorisatSortie == null) {
			if (other.dateRetourAutorisatSortie != null)
				return false;
		} else if (!dateRetourAutorisatSortie.equals(other.dateRetourAutorisatSortie))
			return false;
		if (villeAutorisatSortie == null) {
			if (other.villeAutorisatSortie != null)
				return false;
		} else if (!villeAutorisatSortie.equals(other.villeAutorisatSortie))
			return false;
		if (paysAutorisatSortie == null) {
			if (other.paysAutorisatSortie != null)
				return false;
		} else if (!paysAutorisatSortie.equals(other.paysAutorisatSortie))
			return false;
		if (numAutorisatSortie == null) {
			if (other.numAutorisatSortie != null)
				return false;
		} else if (!numAutorisatSortie.equals(other.numAutorisatSortie))
			return false;
		if (numAutSortie == null) {
			if (other.numAutSortie != null)
				return false;
		} else if (!numAutSortie.equals(other.numAutSortie))
			return false;
		if (motifSortie == null) {
			if (other.motifSortie != null)
				return false;
		} else if (!motifSortie.equals(other.motifSortie))
			return false;
		if (statusAutorisatSortie == null) {
			if (other.statusAutorisatSortie != null)
				return false;
		} else if (!statusAutorisatSortie.equals(other.statusAutorisatSortie))
			return false;
		if (dateStatusAutorisatSortie == null) {
			if (other.dateStatusAutorisatSortie != null)
				return false;
		} else if (!dateStatusAutorisatSortie.equals(other.dateStatusAutorisatSortie))
			return false;
		if (numNoteServiceConge == null) {
			if (other.numNoteServiceConge != null)
				return false;
		} else if (!numNoteServiceConge.equals(other.numNoteServiceConge))
			return false;
		if (numConge == null) {
			if (other.numConge != null)
				return false;
		} else if (!numConge.equals(other.numConge))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "AutorisationSortieDto [dateDepartAutorisatSortie=" + dateDepartAutorisatSortie
				+ ", dateRetourAutorisatSortie=" + dateRetourAutorisatSortie + ", villeAutorisatSortie="
				+ villeAutorisatSortie + ", paysAutorisatSortie=" + paysAutorisatSortie + ", numAutorisatSortie="
				+ numAutorisatSortie + ", numAutSortie=" + numAutSortie + ", motifSortie=" + motifSortie
				+ ", statusAutorisatSortie=" + statusAutorisatSortie + ", dateStatusAutorisatSortie="
				+ dateStatusAutorisatSortie + ", numNoteServiceConge=" + numNoteServiceConge + ", numConge=" + numConge
				+ "]";
	}

}
