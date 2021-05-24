package fr.isika.cda10.annuaire.models;
import java.util.ArrayList;
import java.util.List;
import fr.isika.cda10.annuaire.models.*;

public class ModelePrincipal {
	// Ici on veut utiliser la classe stagiaire, donc on crée un attribut stagiaire
	private List<Stagiaire> listeStagiaire;
	/**
	 *
	 */
	public ModelePrincipal() {
		listeStagiaire = new ArrayList<>();
//		Stagiaire.setCompteur(0);
	}
	
	// Méthode qui retourne la liste
	public List<Stagiaire> getListeStagiaire(){
		return listeStagiaire;
	}
	
	// Ici méthode qui retourne un élément de la liste
	public Stagiaire getStagiaire(int index) {
		return listeStagiaire.get(index);
	}
	
	// On modifie un objet avec le constructeur setStagiaire, il va chercher un objet dans le tableau que l'on va modifier
	public Stagiaire getStagiaire(int index, Object[] donneesStagiaire) {
		Stagiaire s = getStagiaire(index);
		s.setNom((String) donneesStagiaire[0]);
		s.setPrenom((String) donneesStagiaire[1]);
		s.setDepartement((String) donneesStagiaire[2]);
		s.setPromotion((String) donneesStagiaire[3]);
		s.setAnneeObtention((Integer) donneesStagiaire[4]);
		return s;
	}
	
//	public long getCompteur() {
//		return Stagiaire.getCompteur();
//	}
	
	public Stagiaire addStagiaire(Object[] donneesStagiaire) {
		Stagiaire s = new Stagiaire((String) donneesStagiaire[0], (String) donneesStagiaire[1], (String) donneesStagiaire[2], (String) donneesStagiaire[3], (Integer) donneesStagiaire[4]);
		listeStagiaire.add(s);
		return s;
	}
	
	public void supprimerStagiaire(int index) {
		listeStagiaire.remove(index);
	}
}





