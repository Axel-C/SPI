package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.CommandeDto;
import fr.iutinfo.skeleton.common.dto.CommandeProduitDto;

public class CommandeProduits {

	
	private int idc;
	private int idp;
	private int nombre;
	
	
	
	public CommandeProduits(int idc, int idp, int nombre) {
		super();
		this.idc = idc;
		this.idp = idp;
		this.nombre = nombre;
	}
	
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
	public void initFromDto(CommandeProduitDto dto){
		this.setIdc(dto.getIdc());
		this.setIdp(dto.getIdp());
		this.setNombre(dto.getNombre());
	}
	
	public CommandeProduitDto convertToDto(){
		CommandeProduitDto dto =new CommandeProduitDto();
		dto.setIdc(this.getIdc());
		dto.setIdp(this.getIdp());
		dto.setNombre(this.getNombre());
		return dto;
	}
}
