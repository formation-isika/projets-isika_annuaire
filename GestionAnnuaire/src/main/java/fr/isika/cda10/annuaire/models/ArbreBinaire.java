package fr.isika.cda10.annuaire.models;

/**
 * Arbre Binaire sans doublon
 * Les types d'éléments de l'arbre doivent être Comparable
 * https://miashs-www.u-ga.fr/prevert/Prog/Java/CoursJava/arbresBinaires.html
 * @author Aissatou
 *
 */
public class ArbreBinaire <E extends Comparable<E>>{
	private static class Noeud<T> {
		private T valeur;
		private Noeud<T> filsGauche;
		private Noeud<T> filsDroit;
		public Noeud(T t) {
			this.valeur = t;
			this.filsGauche = null;
			this.filsDroit = null;
		}
		
		public Noeud() {
			this.valeur = null;
			this.filsGauche = null;
			this.filsDroit = null;
		}
		
		public Noeud(T t, Noeud<T> filsGauche, Noeud<T> filsDroit) {
			this.valeur = t;
			this.filsGauche = filsGauche;
			this.filsDroit = filsDroit;
		}
		
		public Object clone() throws CloneNotSupportedException{
	      // copie en profondeur d’un noeud
	      Noeud<T> fg = null;
	      if( filsGauche != null ) fg = (Noeud<T>)filsGauche.clone();
	      Noeud<T> fd = null;
	      if( filsDroit != null ) fd = (Noeud<T>)filsDroit.clone();
	      return new Noeud<T>(valeur, fg, fd);
	   }
		@Override
		public String toString() {
			return "Noeud [valeur=" + valeur + ", fils gauche=" + filsGauche + ", fils droit=" + filsDroit + "]";
		}
	}
	
	/**
	 *  Attrbuts d'Arbre binaire
	 */
	private Noeud<E> racine = null;
	
	public ArbreBinaire() {
		super();
	}
	/**
	 * @return the racine
	 */
	public Noeud<E> getRacine() {
		return racine;
	}
	
	/**
	 * return l'arbre avec ses fils avec des parenthèses
	 * @return
	 */
	public String parenthesee(){
	   return parenthesee(racine);
	}
	
	 String parenthesee(Noeud<E> r){
	   if (r==null) return "";
	   else return r.valeur.toString()+"("+ parenthesee(r.filsGauche)+", "+parenthesee(r.filsDroit)+")";
	 }
	
	
	/**
	 * @param noeud
	 */
	public void parcoursInfixe(Noeud<E> noeud) {
		if (noeud != null) {
			parcoursInfixe(noeud.filsGauche);
			System.out.println(noeud.valeur.toString()+" - "
					+ "FG=" + (noeud.filsGauche == null ? "" : noeud.filsGauche.valeur)
					+ "FD=" + (noeud.filsDroit == null ? "" : noeud.filsDroit.valeur));
			parcoursInfixe(noeud.filsDroit);
		}
	}
	
	/**
	 * Permet d'ajouter un noeud � la bonne place
	 * suivant la relation d'ordre du E consid�r�
	 *
	 */
	public void ajouterNoeud(E element) {
		Noeud<E> courant = racine;
		if (racine == null) racine = new Noeud<E>(element);
		else {
			boolean ok = false;
			while(!ok) {
				int test = element.compareTo(courant.valeur);
				if (test < 0) {
					if (courant.filsGauche == null) {
						courant.filsGauche = new Noeud<E>(element);
						ok = true;
					}
					else {
						courant =courant.filsGauche;
					}
				} else if (test > 0) {
					if (courant.filsDroit == null) {
						courant.filsDroit = new Noeud<E>(element);
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
	 * Ajouter un noeud dans l'arbre
	 * @param element
	 */
	public void ajoutNoeud(E element){  // element n'est pas ajouté s'il est déjà présent
	    racine = ajoutNoeud(racine, element);
	 }
	
	 Noeud ajoutNoeud ( Noeud<E> r, E element){
	    if(r==null)
	       return new Noeud<E> (element, null, null);
	   else
	       if (r.valeur.compareTo(element)<0) r.filsDroit = ajoutNoeud(r.filsDroit, element);
	       else if (r.valeur.compareTo(element)>0) r.filsGauche = ajoutNoeud(r.filsGauche, element);
	   return r;
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
	public Noeud<E> rechercheRecursif(Noeud<E >noeud, E element) {
		if (noeud == null ) return null;
		// On l'a trouvé
		if (element.compareTo(noeud.valeur) == 0) return noeud;
		if (element.compareTo(noeud.valeur) > 0) return rechercheRecursif(noeud.filsDroit, element);
		return rechercheRecursif(noeud.filsGauche, element);
	}
	
	/**
	 *
	 * @return nombre element dans un arbre
	 */
	public int nombreElement(){
	   return nombreElement(racine);
	}
	
	public int nombreElement(Noeud<E> racine){
	     if (racine == null) return 0;
	     else return nombreElement(racine.filsGauche) + 1 + nombreElement(racine.filsDroit);
	}
	
	
	/**
	 *
	 * @return nombre de feuille dans un arbre
	 */
	public int nombreFeuilles(){
	    return nombreFeuilles(racine);
	}
	
	public int nombreFeuilles(Noeud<E> racine){
	    if (racine == null) return 0;
	    else
	      if(racine.filsGauche==null && racine.filsDroit==null) return 1;
	      else return nombreFeuilles(racine.filsGauche) + nombreFeuilles(racine.filsDroit);
	}
	
}