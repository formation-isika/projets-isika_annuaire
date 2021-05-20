package fr.isika.cda10.annuaire.models;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.isika.cda10.annuaire.models.ArbreBinaire.Noeud;

public class Annuaire {
	
	List<Stagiaire> stagiaires;
	ArbreBinaire<Stagiaire> arbreStagiaire;
	BufferedReader bufferedReader = null;
	FileReader fileReader;
	//private static String NOM_FICHIER = "STAGIAIRES.DON";
	private static String NOM_SAUVEGARDE_TRI= "SAUVEGARDE.bin";
	private static String NOM_FICHIER = "STAGIAIRES-COMPLET.DON";
	private static final int TAILLE = 5;
	
	public Annuaire() {
		arbreStagiaire = new ArbreBinaire<Stagiaire>();
	}

	/**Permet de lire le fichier STAGIAIRES.DON
	 * initierArbreBinaireAPartirDuFichierStagiairesDon
	 */
	public void initierArbreBinaireAPartirDuFichierStagiairesDon() {
		File fichier = new File(NOM_FICHIER);
		try {
			stagiaires = new ArrayList<>();
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
				Stagiaire stagiaire = new Stagiaire((String)elementStagiaire[0],
													(String)elementStagiaire[1],
													(String)elementStagiaire[2],
													(String)elementStagiaire[3],
													(Integer)(elementStagiaire[4])
												   );
						
				stagiaires.add(stagiaire);
				bufferedReader.readLine();
			}
			initierArbreBinaireAPartirDeLaListeDesStagiaires(stagiaires);
		} catch(FileNotFoundException e) {
			System.err.printf("Le fichier %s n'a pas été trouver", fichier.toString()+"\n");
		}
		catch (IOException e) {
			System.err.printf("Impossible de lire le fichier", fichier.toString()+"\n");
			e.printStackTrace();
		}
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
	 * initierArbreBinaire permet de rajouter les stagiaires dans l'arbre binaire
	 * @param elementsFichier
	 */
	int index = 0;
	private void initierArbreBinaireAPartirDeLaListeDesStagiaires(List<Stagiaire> stagiaires) {
		stagiaires.forEach(stagiaire -> {
			ajouterStagiaireDansLeNoeud(stagiaire, index);
			index++;
		});
		arbreStagiaire.parcoursInfixe(arbreStagiaire.getRacine());
	}
	
	/**
	 * ajouter un stagiaire dans l'arbre binaire
	 * @param stagiaire
	 */
	public void ajouterStagiaireDansLeNoeud(Stagiaire stagiaire, int index) {
		if(stagiaire != null)
			arbreStagiaire.ajouterNoeud(stagiaire, index);

	}
	
	/**
	 * Recupérer la liste des stagiaires
	 * @return
	 */
	public List<Stagiaire> getListStagiaire() {
		return stagiaires;
	}

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public ArbreBinaire<Stagiaire> lireArbreBinaireDansFichierSauvegarde() throws IOException {
		ArbreBinaire<Stagiaire> arbreBinaire = new ArbreBinaire<>();

		try {
			RandomAccessFile rafTri = new RandomAccessFile(NOM_SAUVEGARDE_TRI, "rw");
			int i=0;
			Noeud<Stagiaire> noeud = null;
			Stagiaire stagiaireTmp = null;
			for(rafTri.seek(i); i<=rafTri.length(); rafTri.seek(i+80)) {
				String nom="";
				String prenom = "";
				String departement = "";
				String promotion = "";
				Integer anneeObtention = null;
				int index = -1;
				int indexFg = -1;
				int indexFd = -1;
				for(int j=0; j<16; i++) {
					nom +=rafTri.readChar();
				}
				for(int j=16; j<32; i++) {
					prenom +=rafTri.readChar();
				}
				for(int j=32; j<48; i++) {
					departement +=rafTri.readChar();
				}
				for(int j=48; j<64; i++) {
					promotion +=rafTri.readChar();
				}
				for(int j=64; j<68; i++) {
					anneeObtention +=rafTri.readInt();
				}
				for(int j=68; j<72; i++) {
					index +=rafTri.readInt();
				}
				for(int j=72; j<76; i++) {
					indexFg +=rafTri.readInt();
				}
				for(int j=76; j<80; i++) {
					indexFd +=rafTri.readInt();
				}

				stagiaireTmp = new Stagiaire();
				stagiaireTmp.setPrenom(prenom.trim());
				stagiaireTmp.setDepartement(departement.trim());
				stagiaireTmp.setPromotion(promotion.trim());
				stagiaireTmp.setAnneeObtention(anneeObtention);

				noeud = new Noeud<Stagiaire>(stagiaireTmp, index);
				arbreBinaire.ajouterNoeud(stagiaireTmp, index);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arbreBinaire;
	}
	
	/**
	 * @param stagiaires
	 * @return
	 * @throws IOException
	 */

	public ArbreBinaire<Stagiaire> ecrireArbreBinaireDansFichierSauvegarde(List<Stagiaire> stagiaires){
		ArbreBinaire<Stagiaire> arbreBinaire = new ArbreBinaire<>();
		//Noeud<Stagiaire> noeud = new Noeud<Stagiaire>(stagiaire, index);
		try {
			RandomAccessFile rafTri = new RandomAccessFile(NOM_SAUVEGARDE_TRI, "rw");
			stagiaires.forEach(stagiaire -> {
				try {
					rafTri.writeChars(stagiaire.getNom());
					rafTri.writeChars(stagiaire.getPrenom());
					rafTri.writeChars(stagiaire.getDepartement());
					rafTri.writeChars(stagiaire.getPromotion());
					rafTri.writeInt(stagiaire.getAnneeObtention());
					//rafTri.writeInt(noeud.);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return arbreBinaire;
   }
	

}