
package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
public class CommandeDto {

	final static Logger logger = LoggerFactory.getLogger(CommandeDto.class);
	private int idc;
	private int id;
	private int idp;
	private float prixTotal;
	
	@Override
	public String toString() {
		return "CommandeDto [idc=" + idc + ", id=" + id + ", idp=" + idp + ", prixTotal=" + prixTotal + "]";
	}


	public int getIdc() {
		return idc;
	}


	public void setIdc(int idc) {
		this.idc = idc;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdp() {
		return idp;
	}


	public void setIdp(int idp) {
		this.idp = idp;
	}


	public float getPrixTotal() {
		return prixTotal;
	}


	public void setPrixTotal(float prixTotal) {
		this.prixTotal = prixTotal;
	}




	
}
