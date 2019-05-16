package fr.epsi.jeeProject.beans;

import java.sql.Date;

public class Utilisateur {

	private String email;
	private String nom;
	private String password;
	private Date dateCreation;
	private Boolean admin;
	
	public Utilisateur() {
		this.admin  = false;
		long millis=System.currentTimeMillis();
		this.dateCreation = new java.sql.Date(millis);
	}
	
	/**
	 * 
	 * @param email
	 * @param nom
	 * @param password
	 */
	
	public Utilisateur(String email, String nom, String password) {
		this.admin  = false;
		long millis=System.currentTimeMillis();
		this.dateCreation = new java.sql.Date(millis);
		this.email = email;
		this.nom = nom;
		this.password = password;
	}
	
	public void GrantAdminPrivilege (Utilisateur peon)
	{
		peon.admin = true;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passord) {
		this.password = passord;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
}
