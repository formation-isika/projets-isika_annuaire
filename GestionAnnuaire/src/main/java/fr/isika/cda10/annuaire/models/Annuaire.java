package fr.isika.cda10.annuaire.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Annuaire {

	ArbreBinaire<Stagiaire> arbreStagiaire;
	BufferedReader bufferedReader = null;
	FileReader fileReader;
	private static String NOM_FICHIER = "STAGIAIRES.DON";
	//private static String NOM_FICHIER = "STAGIAIRES-COMPLET.DON";
	private static final int TAILLE = 5;

	public Annuaire() {
		arbreStagiaire = new ArbreBinaire<Stagiaire>();
	}

	/**
	 * initierArbreBinaireAPartirDuFichierStagiaires
	 */
	public void initierArbreBinaireAPartirDuFichierStagiaires() {
		File fichier = new File(NOM_FICHIER);
		try {
			List<Stagiaire> stagiaires = new ArrayList<>();
			//Lire le fichier
			fileReader = new FileReader(fichier);
			bufferedReader = new BufferedReader(fileReader);
			while(bufferedReader.ready()) {

				Object[] elementStagiaire = new Object[TAILLE];
				for(int i=0; i<elementStagiaire.length; i++) {
					if(i==4)
						elementStagiaire[i] = Integer.valueOf(bufferedReader.readLine().trim());
					else
						elementStagiaire[i] = bufferedReader.readLine().trim();
				}
				//Création de l'objet stagiaire
				Stagiaire stagiaire = new Stagiaire((String)elementStagiaire[0],
						(String)elementStagiaire[1],
						(String)elementStagiaire[2],
						(String)elementStagiaire[3],
						(Integer)(elementStagiaire[4]));
				//Ajouter le stagiaire dans la liste des stagiaires
				stagiaires.add(stagiaire);
				bufferedReader.readLine();
			}

			//Appelle de la méthode initierArbreBinaire pour l'ajout des stagiaires dans l'arbre binaire
			initierArbreBinaire(stagiaires);

			//Gestion des exceptions
		} catch(FileNotFoundException e) {
			System.err.printf("Le fichier %s n'a pas été trouver", fichier.toString()+"\n");
		}
		catch (IOException e) {
			System.err.printf("Impossible de lire le fichier", fichier.toString()+"\n");
			e.printStackTrace();
		}

		// Ouverture et fermeture du buffer
		try {
			bufferedReader.close();
		}catch (IOException e) {
			System.err.printf("Impossible de fermer le fichier", fichier.toString()+"\n");
			e.printStackTrace();
		}catch (NullPointerException e) {
			System.err.printf("Impossible d'ouvrir le fichier", fichier.toString()+"\n");
			e.printStackTrace();
		}

	}

	/**
	 * initierArbreBinaire
	 * @param elementsFichier
	 */
	private void initierArbreBinaire(List<Stagiaire> stagiaires) {
		// Parcourrir la liste des stagiaires
		stagiaires.forEach(stagiaire -> {

			//Ajout les stagiaires dans l'arbre binaire
			arbreStagiaire.ajoutNoeud(stagiaire);    
		});
		// Initialisation de l'arbre binaire à partir du fichier STAGIAIRES.DON
		System.out.println("====Parcours infixe initialisation de l'arbre binaire à partir du fichier DON=======");
		arbreStagiaire.parcoursInfixe(arbreStagiaire.getRacine());
		System.out.println("Nombre de noeud dans l'arbre : "+arbreStagiaire.nombreElement()+"\n");
		System.out.println("Nombre de feuille dans l'arbre : "+arbreStagiaire.nombreFeuilles()+"\n");
	}

	/**
	 * ajouter un stagiaire dans l'arbre binaire
	 * @param stagiaire
	 */
	public void ajouterStagiaireDansLeNoeud(Stagiaire stagiaire) {
		if(stagiaire != null)
			arbreStagiaire.ajoutNoeud(stagiaire);
	}

	/**
	 *
	 * @param stagiaires
	 * @param critereSuppression
	 */
	public void supprimerUnStagiaireDansArbreBinaire(List<Stagiaire> stagiaires, String critereSuppression) {
		Stagiaire stagiaireASupprimer = rechercherStagiaire(stagiaires, critereSuppression); //Initialiser le stagiaire à supprimer
		if(stagiaireASupprimer != null)
			arbreStagiaire.suppressionNoeud(stagiaireASupprimer);
	}

	/**
	 * rechercher dans l'arbre binaire un stagiaire
	 * @param stagiaire
	 * @return
	 */
	public Stagiaire rechercherStagiaire(List<Stagiaire> stagiaires, String critereRecherche) {
		Stagiaire stagiaireChercher = rechercherUnStagiaireDansLaListe(stagiaires, critereRecherche); //Initialiser le stagiaire à chercher
		return arbreStagiaire.rechercher(arbreStagiaire.getRacine(), stagiaireChercher);
	}

	public void mettreAjourUnStagiaire(Stagiaire stagiaire) {
		//TODO à compléter
	}

	/**
	 * rechercher un stagiaire dans la liste
	 * @param stagiaires
	 * @param critere
	 * @return
	 */
	public Stagiaire rechercherUnStagiaireDansLaListe(List<Stagiaire> stagiaires, String critere) {
		Stagiaire stagiaireAChercher = null; //Initialiser le stagiaire à chercher

		//Parcourrir la liste des stagiaires chercher le stagiaire à supprimer dans l'arbre
		for(Stagiaire stagiaire : stagiaires) {
			if((stagiaire.getNom().equalsIgnoreCase(critere))
					|| (stagiaire.getPrenom().equalsIgnoreCase(critere))
					|| (stagiaire.getDepartement().equalsIgnoreCase(critere))
					|| (stagiaire.getPromotion().equalsIgnoreCase(critere))){
				stagiaireAChercher = stagiaire;
			}
		}
		return stagiaireAChercher;
	}

}