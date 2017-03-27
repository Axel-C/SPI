package fr.iutinfo.skeleton.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import fr.iutinfo.skeleton.common.dto.CommandeDto;

@XmlRootElement
public class Commandes {
	/**
	 * id de la commande
	 */
	private int idc;
	/**
	 * id de l'utilisateur
	 */
	private int id;
	/**
	 * id du produit
	 */
	private int idp;
	
	private float prixTotal;
	
	public Commandes(){
		
	}
	
	

	public Commandes(int idc, int id, int idp, float prixTotal) {
		super();
		this.idc = idc;
		this.id = id;
		this.idp = idp;
		this.prixTotal = prixTotal;
	}

	@Override
	public String toString() {
		return "Commandes [idc=" + idc + ", id=" + id + ", idp=" + idp + ", prixTotal=" + prixTotal + "]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commandes other = (Commandes) obj;
		if (id != other.id)
			return false;
		if (idc != other.idc)
			return false;
		if (idp != other.idp)
			return false;
		if (Float.floatToIntBits(prixTotal) != Float.floatToIntBits(other.prixTotal))
			return false;
		return true;
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
	
	public void initFromDto(CommandeDto dto){
		this.setId(dto.getId());
		this.setIdc(dto.getIdc());
		this.setIdp(dto.getIdp());
		this.setPrixTotal(dto.getPrixTotal());
	}
	
	public CommandeDto convertToDto(){
		CommandeDto dto =new CommandeDto();
		dto.setId(this.getId());
		dto.setIdc(this.getIdc());
		dto.setIdp(this.getIdp());
		dto.setPrixTotal(this.getPrixTotal());
		return dto;
	}
	
	
	
	
	
	
	
	
	
}
