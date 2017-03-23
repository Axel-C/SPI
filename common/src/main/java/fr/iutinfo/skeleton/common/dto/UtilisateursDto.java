package fr.iutinfo.skeleton.common.dto;

public class UtilisateursDto {

	private int id;
	private String nom;
	private String mdp;
	private String email;
	private String adresse;
	private String telephone;

	public UtilisateursDto() {

	}

	public UtilisateursDto(int id, String nom, String mdp, String email, String adresse, String telephone) {
		super();
		this.id = id;
		this.nom = nom;
		this.mdp = mdp;
		this.email = email;
		this.adresse = adresse;
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "UtilisateursDto [id=" + id + ", nom=" + nom + ", mdp=" + mdp + ", email=" + email + ", adresse="
				+ adresse + ", telephone=" + telephone + "]";
	}

}
