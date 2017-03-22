package fr.iutinfo.skeleton.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Produits {

	private String libelle;
	private int idp;
	private String reference;
	private float prix;
	private String description;
	private String categorie;
	
	public Produits(){
		
	}
	
	public Produits(String libelle, int id, String reference, float prix, String description, String categorie){
		this.libelle = libelle;
		this.idp = id;
		this.reference = reference;
		this.prix = prix;
		this.description = description;
		this.categorie = categorie;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getId() {
		return idp;
	}
	public void setId(int id) {
		this.idp = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produits other = (Produits) obj;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idp != other.idp)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (Float.floatToIntBits(prix) != Float.floatToIntBits(other.prix))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produits [libelle=" + libelle + ", id=" + idp + ", reference=" + reference + ", prix=" + prix
				+ ", description=" + description + ", categorie=" + categorie + "]";
	}
	
	
	
}
