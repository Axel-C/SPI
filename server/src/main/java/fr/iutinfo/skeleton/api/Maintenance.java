package fr.iutinfo.skeleton.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Maintenance {

	private String type;
	private int idM;
	private int idPro; // Id du produit
	private int idUser; // Id de l'utilisateur
	private String rapport;
	private Date date;

	public Maintenance() {

	}

	public Maintenance(String type, int idM, int idPro, int idUser, String rapport, Date date) {
		this.type = type;
		this.idM = idM;
		this.idPro = idPro;
		this.idUser = idUser;
		this.rapport = rapport;
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Maintenance [type=" + type + ", idM=" + idM + ", idPro=" + idPro + ", idUser=" + idUser + ", rapport="
				+ rapport + ", date=" + date + "]";
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

}
