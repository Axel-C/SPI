package fr.iutinfo.skeleton.api;

import javax.xml.bind.annotation.XmlRootElement;

import fr.iutinfo.skeleton.common.dto.CommandeDto;
import fr.iutinfo.skeleton.common.dto.MaintenanceDto;
import fr.iutinfo.skeleton.common.dto.UserDto;

@XmlRootElement
public class Maintenance {

	private String type;
	private int idM;
	private int idPro; // Id du produit
	private int idUser; // Id de l'utilisateur
	private String rapport;
	private String date;
	private String numPorte;

	public Maintenance() {

	}

	public Maintenance(String type, int idM, int idPro, int idUser, String rapport, String date,String numPorte) {
		this.type = type;
		this.idM = idM;
		this.idPro = idPro;
		this.idUser = idUser;
		this.rapport = rapport;
		this.date = date;
		this.numPorte=numPorte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIdM() {
		return idM;
	}

	public void setIdM(int idM) {
		this.idM = idM;
	}

	public int getIdPro() {
		return idPro;
	}

	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getRapport() {
		return rapport;
	}

	public void setRapport(String rapport) {
		this.rapport = rapport;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Maintenance [type=" + type + ", idM=" + idM + ", idPro=" + idPro + ", idUser=" + idUser + ", rapport="
				+ rapport + ", date=" + date + ", numnumPorte="+numPorte+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maintenance other = (Maintenance) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idM != other.idM)
			return false;
		if (idPro != other.idPro)
			return false;
		if (idUser != other.idUser)
			return false;
		if (rapport == null) {
			if (other.rapport != null)
				return false;
		} else if (!rapport.equals(other.rapport))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
    public void initFromDto(Maintenance dto) {
      this.setIdM(dto.getIdM());
      this.setDate(dto.getDate());
      this.setIdPro(dto.getIdPro());
      this.setIdUser(dto.getIdUser());
      this.setRapport(dto.getRapport());
      this.setType(dto.getType());
      this.setnumPorte(dto.getnumPorte());
    }

    public MaintenanceDto convertToDto() {
        MaintenanceDto dto = new MaintenanceDto();
        dto.setDate(this.getDate());
        dto.setIdM(dto.getIdM());
        dto.setIdPro(this.getIdPro());
        dto.setIdUser(this.getIdUser());
        dto.setRapport(this.getRapport());
        dto.setType(this.getType());
        dto.setNumPorte(this.getnumPorte());
        return dto;
    }

	public String getnumPorte() {
		return numPorte;
	}

	public void setnumPorte(String numPorte) {
		this.numPorte = numPorte;
	}

}
