package fr.iutinfo.skeleton.common.dto;

public class MaintenanceDto {

	private String type;
	private int idM;
	private int idPro;
	private int idUser; 
	private String rapport;
	private String date;
	private String numPorte;
	
	public String getNumPorte() {
		return numPorte;
	}
	public void setNumPorte(String numPorte) {
		this.numPorte = numPorte;
	}

	
	@Override
	public String toString() {
		return "MaintenanceDto [type=" + type + ", idM=" + idM + ", idPro=" + idPro + ", idUser=" + idUser
				+ ", rapport=" + rapport + ", date=" + date + ", numPorte=" + numPorte + "]";
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


	
}
