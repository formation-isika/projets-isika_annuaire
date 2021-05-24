package fr.isika.cda10.annuaire.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Arbre Binaire sans doublon
 * Les types d'éléments de l'arbre doivent être Comparable
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
					+ " FD= Valeur : " + (filsDroit == null ? "" : filsDroit);		

		}
	}

	private static String STAGIAIRES_BIN= "STAGIAIRES.bin";

	/**
	 *  Attrbuts d'Arbre binaire
	 */
	private Noeud<E> racine = null;

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
	 * supprimer un noeud dans un arbre
	 * @param element
	 */
	public void suppressionNoeud(E element){
		racine = suppression(racine, element);
	}

	Noeud suppression(Noeud<E> r, E element){
		if (r==null) return r;// l’objet o n’est pas trouvé
		else{
			if (r.valeur.compareTo(element)==0) return supp(r);
			else if(r.valeur.compareTo(element)>0) r.filsGauche = suppression(r.filsGauche, element);
			else r.filsDroit = suppression(r.filsDroit, element);
			return r;
		}
	}

	Noeud<E> supp (Noeud<E> r){
		if(r.filsGauche==null) return r = r.filsDroit;
		else
			if(r.filsDroit==null) return r = r.filsGauche;
			else{
				Noeud<E> r1 = r.filsGauche;
				Noeud<E> pere = r;
				while(r1.filsDroit!=null) {
					pere = r1;
					r1 = r1.filsDroit;
				}
				r.valeur = r1.valeur;
				if(pere == r) pere.filsGauche = r1.filsGauche;
				else pere.filsDroit = r1.filsGauche;
				return r;
			}
	}

	/**
	 * Recherche un élément recursivement
	 * Retourne l'élément si trouvé sinon null
	 */
	public E rechercher(Noeud<E> noeud, E element) {
		Noeud<E> n = rechercheRecursif(noeud, element);
		if (n != null) return n.valeur;
		return null;
	}

	/**
	 *
	 * @param noeud
	 * @param element
	 * @return
	 */
	public Noeud<E> rechercheRecursif(Noeud<E> noeud, E element) {
		if (noeud == null ) return null;
		// On l'a trouvé
		if (element.compareTo(noeud.valeur) == 0) return noeud;
		if (element.compareTo(noeud.valeur) > 0) return rechercheRecursif(noeud.filsDroit, element);
		return rechercheRecursif(noeud.filsGauche, element);
	}
	
	/**
	 * return le nombre d'elements dans l'arbre binaire
	 * 
	 * @param elements
	 * @param noeud
	 */
	public void getElementsArbreBinaire(List<E> elements, Noeud<E> noeud) {
		if(noeud.filsGauche != null) {
			getElementsArbreBinaire(elements, noeud.filsGauche);
		}
		if(noeud.filsDroit != null ) {
			getElementsArbreBinaire(elements, noeud.filsDroit);
		}
		 elements.add(noeud.valeur);
	}

}