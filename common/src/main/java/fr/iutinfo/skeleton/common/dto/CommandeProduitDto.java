package fr.iutinfo.skeleton.common.dto;

public class CommandeProduitDto {

	private int idc;
	private int idp;
	private int nombre;
	public int getIdc() {
		return idc;
	}
	public void setIdc(int idc) {
		this.idc = idc;
	}
	public int getIdp() {
		return idp;
	}
	public void setIdp(int idp) {
		this.idp = idp;
	}
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	public CommandeProduitDto(int idc, int idp, int nombre) {
		super();
		this.idc = idc;
		this.idp = idp;
		this.nombre = nombre;
	}
	public CommandeProduitDto() {
		
	}
}
