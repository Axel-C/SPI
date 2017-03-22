package fr.iutinfo.skeleton.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

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
	
	private float prixtoltal;
	
	public Commandes(){
		
	}
	
	

	public Commandes(int idc, int id, int idp, float prixtoltal) {
		super();
		this.idc = idc;
		this.id = id;
		this.idp = idp;
		this.prixtoltal = prixtoltal;
	}

	@Override
	public String toString() {
		return "Commandes [idc=" + idc + ", id=" + id + ", idp=" + idp + ", prixtoltal=" + prixtoltal + "]";
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
		if (Float.floatToIntBits(prixtoltal) != Float.floatToIntBits(other.prixtoltal))
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

	public float getPrixtoltal() {
		return prixtoltal;
	}

	public void setPrixtoltal(float prixtoltal) {
		this.prixtoltal = prixtoltal;
	}
	
	
}
