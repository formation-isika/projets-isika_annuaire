package fr.isika.cda10.annuaire.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Arbre Binaire sans doublon
 * Les types d'éléments de l'arbre doivent être Comparable
 * 
 * @author Aissatou
 *
 */
public class ArbreBinaire <E extends Comparable<E>>{
	
	public static class Noeud<T> {
		private T valeur;
		private Noeud<T> filsGauche;
		private Noeud<T> filsDroit;
		private int index;
		
		public Noeud(T t, int index) {
			this.valeur = t;
			this.filsGauche = null;
			this.filsDroit = null;
			this.index = index;
		}		
		
		@Override
		public String toString() {
			return  "Index : " + index + " - Valeur : "
					+ valeur +" - "
					+ " FG= Valeur : " + (filsGauche == null ? "" : filsGauche)
					+ " FD= Valeur : " +  (filsDroit == null ? "" : filsDroit);		
		}
	}
	/**
	 *  Attrbuts d'Arbre binaire
	 */
	private Noeud<E> racine = null;
	public Noeud<E> index = null;

	/**
	 * @return the racine
	 */
	public Noeud<E> getRacine() {
		return racine;
	}

	/**Parcours infixe
	 * @param noeud
	 */
	public void parcoursInfixe(Noeud<E> noeud) {
		if (noeud != null) {
			parcoursInfixe(noeud.filsGauche);
			String affichage = noeud.toString();		
			System.out.println(affichage);
			parcoursInfixe(noeud.filsDroit);
		}
	}
	
	
	
	
	/**
	 * /**
	 * Permet d'ajouter un noeud � la bonne place
	 * suivant la relation d'ordre du E consid�r�
	 *
	 * @param element
	 */
	public void ajouterNoeud(E element, int index) {
		Noeud<E> courant = racine;
		if (racine == null) racine = new Noeud<E>(element, index);
		else {
			boolean ok = false;
			while(!ok) {
				int test = element.compareTo(courant.valeur);
				if (test < 0) {
					if (courant.filsGauche == null) {
						courant.filsGauche = new Noeud<E>(element, index);
						ok = true;
					}
					else {
						courant =courant.filsGauche;
					}
				} else if (test > 0) {
					if (courant.filsDroit == null) {
						courant.filsDroit = new Noeud<E>(element, index);
						ok = true;
					}
					else {
						courant = courant.filsDroit;
					}
				}
				else {
					ok = true;
				}
			}
		}
	}
	 /**
	 * @param noeud
	 * @param element
	 * @return
	 */
	public Noeud<E> rechercheRecursif(Noeud<E >noeud, E element) {
		if (noeud == null ) return null;
		// On l'a trouvé
		if (element.compareTo(noeud.valeur) == 0) return noeud;
		if (element.compareTo(noeud.valeur) > 0) return rechercheRecursif(noeud.filsDroit, element);
		return rechercheRecursif(noeud.filsGauche, element);
	}
	
}