package fr.iutinfo.skeleton.common.dto;

public class ProduitsDto {
	private String libelle;
	private int idp;
	private String reference;
	private float prix;
	private String description;
	private String categorie;
	private String urlImage;
	
	@Override
	public String toString() {
		return "ProduitsDto [libelle=" + libelle + ", idp=" + idp + ", reference=" + reference + ", prix=" + prix
				+ ", description=" + description + ", categorie=" + categorie + ", urlImage=" + urlImage + "]";
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getIdp() {
		return idp;
	}
	public void setIdp(int idp) {
		this.idp = idp;
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
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
}
