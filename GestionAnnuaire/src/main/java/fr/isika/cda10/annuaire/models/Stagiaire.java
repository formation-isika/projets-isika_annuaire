package fr.isika.cda10.annuaire.models;

public class Stagiaire implements Comparable<Stagiaire>{
  
	private String nom;
	private String prenom;
	private String departement;
	private String  promotion;
	private Integer anneeObtention;
	
	public Stagiaire() {
		super();
	}

	public Stagiaire(String nom, String prenom, String departement, String promotion, Integer anneeObtention) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promotion = promotion;
		this.anneeObtention = anneeObtention;
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

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public Integer getAnneeObtention() {
		return anneeObtention;
	}

	public void setAnneeObtention(Integer anneeObtention) {
		this.anneeObtention = anneeObtention;
	}

	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promotion="
				+ promotion + ", anneeObtention=" + anneeObtention + "]";
	}

	@Override
	public int compareTo(Stagiaire o) {
		// TODO Auto-generated method stub
		return this.nom.compareToIgnoreCase(o.getNom());
	}
	
	
	
}
