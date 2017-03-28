package fr.iutinfo.skeleton.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Porte {
	
	private int id;
	private int idUser;
	private String description;
	private String dateLastMaintenance;
	private String descLastMaintenance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateLastMaintenance() {
		return dateLastMaintenance;
	}
	public void setDateLastMaintenance(String dateLastMaintenance) {
		this.dateLastMaintenance = dateLastMaintenance;
	}
	public String getDescLastMaintenance() {
		return descLastMaintenance;
	}
	public void setDescLastMaintenance(String descLastMaintenance) {
		this.descLastMaintenance = descLastMaintenance;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Porte other = (Porte) obj;
		if (dateLastMaintenance == null) {
			if (other.dateLastMaintenance != null)
				return false;
		} else if (!dateLastMaintenance.equals(other.dateLastMaintenance))
			return false;
		if (descLastMaintenance == null) {
			if (other.descLastMaintenance != null)
				return false;
		} else if (!descLastMaintenance.equals(other.descLastMaintenance))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Porte [id=" + id + ", idUser=" + idUser + ", description=" + description + ", dateLastMaintenance="
				+ dateLastMaintenance + ", descLastMaintenance=" + descLastMaintenance + "]";
	}
	public Porte(int id, int idUser, String description, String dateLastMaintenance, String descLastMaintenance) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.description = description;
		this.dateLastMaintenance = dateLastMaintenance;
		this.descLastMaintenance = descLastMaintenance;
	}
	
	
	
	
}
