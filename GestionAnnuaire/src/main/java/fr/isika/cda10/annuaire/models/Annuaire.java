package fr.isika.cda10.annuaire.models;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


public class Annuaire {

	List<Stagiaire> stagiaires;
	ArbreBinaire<Stagiaire> arbreStagiaire;
	BufferedReader bufferedReader = null;
	FileReader fileReader;
	//private static String NOM_FICHIER = "STAGIAIRES.DON";
	private static String STAGIAIRES_BIN= "STAGIAIRES.bin";
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
	 * Recupere la liste dans l'arbre binaire
	 * 
	 * @return stagiaires
	 */
	public List<Stagiaire> getListStagiaireDansArbreBinaire() {
		List<Stagiaire> stagiaires = new ArrayList<>();
		arbreStagiaire.getElementsArbreBinaire(stagiaires, arbreStagiaire.getRacine());
		ecrireArbreBinaireDansFichierStagiaireBin(stagiaires);
		return stagiaires;
	}

	/**
	 *
	 * @param stagiaires
	 * @param critereSuppression
	 */
	public void supprimerUnStagiaireDansArbreBinaire(List<Stagiaire> stagiaires, String critereSuppression) {
		Stagiaire stagiaireASupprimer = rechercherStagiaire(stagiaires, critereSuppression);
		if(stagiaireASupprimer != null)
			arbreStagiaire.suppressionNoeud(stagiaireASupprimer);
	}

	/**
	 * rechercher dans l'arbre binaire un stagiaire
	 * @param stagiaire
	 * @return
	 */
	public Stagiaire rechercherStagiaire(List<Stagiaire> stagiaires, String critereRecherche) {
		Stagiaire stagiaireChercher = rechercherUnStagiaireDansLaListe(stagiaires, critereRecherche); 
		return arbreStagiaire.rechercher(arbreStagiaire.getRacine(), stagiaireChercher);
	}

	/**
	 * @param stagiaires
	 * @return
	 * @throws IOException
	 */
	public void ecrireArbreBinaireDansFichierStagiaireBin(List<Stagiaire> stagiaires) {

		try (RandomAccessFile rafTri = new RandomAccessFile(new File(STAGIAIRES_BIN), "rw")) {

			stagiaires.forEach(stagiaire -> {
				try {
					rafTri.seek(rafTri.length());
					rafTri.writeChars(stagiaire.nomLong());
					rafTri.seek(rafTri.length());
					rafTri.writeChars(stagiaire.prenomLong());
					rafTri.seek(rafTri.length());
					rafTri.writeChars(stagiaire.deptLong());
					rafTri.seek(rafTri.length());
					rafTri.writeChars(stagiaire.promoLong());
					String anneeObtention = String.valueOf(stagiaire.getAnneeObtention());
					rafTri.seek(rafTri.length());
					rafTri.writeChars(anneeObtention);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			rafTri.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * rechercher un stagiaire dans la liste
	 * @param stagiaires
	 * @param critere
	 * @return
	 */
	public Stagiaire rechercherUnStagiaireDansLaListe(List<Stagiaire> stagiaires, String critere) {
		Stagiaire stagiaireAChercher = null;
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

	public void testFichierBinaire() {
		File fichierBinaire = new File("C:\\Users\\micka\\Desktop\\Dev\\Projet1\\projets-isika_annuaire\\GestionAnnuaire\\fichierBinaire.bin");
		List<Stagiaire> stagiaires = getListStagiaireDansArbreBinaire();

		try (RandomAccessFile raf =  new RandomAccessFile(fichierBinaire, "rw")){

			for (Stagiaire stagiaire : stagiaires) {
				raf.writeChars(stagiaire.nomLong());
				raf.writeChars(stagiaire.prenomLong());
				raf.writeChars(stagiaire.deptLong());
				raf.writeChars(stagiaire.promoLong());
				raf.writeInt(stagiaire.getAnneeObtention());
				
			}

			raf.close();
			System.out.println("Enregistrement réussi");
		} catch (IOException ioe) {
			System.out.println("Enregistrement échoué");
		}		
	}

}