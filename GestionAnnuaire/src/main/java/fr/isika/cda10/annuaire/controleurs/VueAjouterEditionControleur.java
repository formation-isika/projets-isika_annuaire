package fr.isika.cda10.annuaire.controleurs;

import fr.isika.cda10.annuaire.models.ModelePrincipal;
import fr.isika.cda10.annuaire.models.Stagiaire;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VueAjouterEditionControleur {
	
	private VuePrincipaleControleur vuePrincipaleControleur;

	private Stage ajouterStagiaireStage;
	@FXML
	private Stagiaire stagiaire;

	@FXML
	private TextField nomTF;

	@FXML
	private TextField prenomTF;

	@FXML
	private TextField departementTF;

	@FXML
	private TextField promotionTF;

	@FXML
	private TextField anneeObtentionTF;
	@FXML
	private Button validerBtn;
	@FXML
	private Button annulerBtn;

	// Lien MVC
	private ModelePrincipal modele;
	private Stage secondaryStage; // Stage Dialogue car nouvelle fenetre qui s'ouvre lors clic
	private String texteAlert;

	private String nom;
	private String prenom;
	private String departement;
	private String promotion;
	private String anneeObtention;
	
	public VueAjouterEditionControleur(VuePrincipaleControleur vuePrincipaleControleur) {
		this.vuePrincipaleControleur = vuePrincipaleControleur;
	}

	private boolean saisieOK;
	public boolean isSaisieOK() {
		return saisieOK;
	}
	@FXML
	private void initialize() {
	}

	public void setModelePrincipal(ModelePrincipal modele) {
		this.modele = modele;
	}

	public void setStage(Stage secondaryStage) {
		this.secondaryStage = secondaryStage;
	}

	public void setStagiaire(Stagiaire modifierStagiaire) {
		this.stagiaire = modifierStagiaire;
	}
	
	public void ajouterStagiaire() {
		
	}

	@FXML
	private void valider() {
		if(!saisieValide()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erreur Saisie");
			alert.setContentText(texteAlert);
			alert.show();
			return;			
		}

		modele.addStagiaire(initDonnees());
		saisieOK = true;

		secondaryStage.close();

	}

	private boolean saisieValide() {
		boolean saisieValide = true;

		texteAlert = "";

		nom = nomTF.getText().trim(); // trim supprime les espaces avant et apres
		prenom =  prenomTF.getText().trim();
		departement =  departementTF.getText().trim();
		promotion = promotionTF.getText().trim();
		anneeObtention =  anneeObtentionTF.getText().trim();

		if (nom.length() == 0 || !nom.matches("[a-zA-Z\\s]+")) {
			saisieValide = false;
			texteAlert += "Champ nom non valide :";
			if (nom.length() == 0) texteAlert += " a remplir\n";
			else if (!nom.matches("[a-zA-Z\\s]+")) texteAlert += " ne doit contenir que des lettres\n";
		}
		if (prenom.length() == 0 || !prenom.matches("[a-zA-Z\\s]+")) {
			saisieValide = false;
			texteAlert += "Champ prenom non valide :";
			if (prenom.length() == 0) texteAlert += " a remplir\n";
			else if (!prenom.matches("[a-zA-Z\\s]+")) texteAlert += " ne doit contenir que des lettres\n";
		}
		if (departement.length() == 0 || !departement.matches("[a-zA-Z0-9\\s]+")) {
			saisieValide = false;
			texteAlert += "Champ departement non valide :";
			if (departement.length() == 0) texteAlert += " a remplir\n";
			else if (!departement.matches("[a-zA-Z\\s]+")) texteAlert += " ne doit contenir que des lettres ou des entiers\n";
		}

		if (promotion.length() == 0 || !promotion.matches("[a-zA-Z0-9\\s]+")) {
			saisieValide = false;
			texteAlert += "Champ promotion non valide :";
			if (promotion.length() == 0) texteAlert += " a remplir\n";
			else if (!promotion.matches("[a-zA-Z\\s]+")) texteAlert += " ne doit contenir que des lettres ou des entiers\n";
		}
		if (anneeObtention.length() == 0) {
			try {
				Integer.parseInt(anneeObtention);
				if (anneeObtention.charAt(0) == '-') {
					saisieValide = false;
					texteAlert += "Champ anneeObtention non valide : ne doit contenir qu'un entier positif compris entre 1980 et 2025\n";
				}
			} catch (NumberFormatException nfE) {
				saisieValide = false;
				texteAlert += "Champ anneeObtention non valide : ne doit contenir qu'un entier positif\n";
			}
		}


		return saisieValide;
	}

	private Object[] initDonnees() {
		Object[] donneesStagiaire = new Object[5];

		donneesStagiaire[0] = nom;
		donneesStagiaire[1] = prenom;
		donneesStagiaire[2] = departement;
		donneesStagiaire[3] = promotion;

		if (anneeObtention.length() == 0) donneesStagiaire[4] = 0; // Ici rajouter condition pour que anneeObtention soit compris entre 1980 et 2025
		else donneesStagiaire[4] = Integer.parseInt(anneeObtention);

		return donneesStagiaire;
	}

	private void closeStage() {
		ajouterStagiaireStage.close();
	}

	private void reset() {
		nomTF.clear();
		prenomTF.clear();
		departementTF.clear();
		promotionTF.clear();
		anneeObtentionTF.clear();
	}
}