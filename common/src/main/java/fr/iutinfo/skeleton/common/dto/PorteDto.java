package fr.iutinfo.skeleton.common.dto;

public class PorteDto {
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
	
	
	
}
