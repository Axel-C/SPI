package fr.iutinfo.skeleton.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Utilisateurs {
	
	private int id;
	private String nom;
	private String prenom;
	private String mdp;
	private String email;
	private String adresse;
	private boolean validation;
	private String  role;
	private String numSiret;
	private String telephone;
	
	public Utilisateurs() {
	}

	public Utilisateurs(int id, String nom, String prenom, String mdp, String email
			, String adresse, boolean validation,String role, String numSiret, String telephone) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mdp = mdp;
		this.email = email;
		this.adresse = adresse;
		this.validation = validation;
		this.role = role;
		this.numSiret = numSiret;
		this.telephone = telephone;
	}


	@Override
	public String toString() {
		return "Utilisateurs [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", email=" + email
				+ ", adresse=" + adresse + ", validation=" + validation + ", role=" + role + ", numSiret=" + numSiret
				+ ", telephone=" + telephone + "]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateurs other = (Utilisateurs) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (mdp == null) {
			if (other.mdp != null)
				return false;
		} else if (!mdp.equals(other.mdp))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numSiret == null) {
			if (other.numSiret != null)
				return false;
		} else if (!numSiret.equals(other.numSiret))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (validation != other.validation)
			return false;
		return true;
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

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	

}
