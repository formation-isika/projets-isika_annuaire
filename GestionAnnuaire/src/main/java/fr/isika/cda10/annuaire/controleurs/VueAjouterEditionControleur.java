package fr.isika.cda10.annuaire.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import fr.isika.cda10.annuaire.models.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VueAjouterEditionControleur implements Initializable {
	
	private VuePrincipaleControleur vuePrincipaleControleur;

	private Stage ajouterStagiaireStage;

	@FXML
	private TextField nomTexteField;

	@FXML
	private TextField prenomTexteField;

	@FXML
	private TextField departementTexteField;

	@FXML
	private TextField promotionTexteField;

	@FXML
	private TextField anneeObtentionTexteField;
	
	@FXML
	private Button validerBtn;
	
	@FXML
	private Button annulerBtn;
	
	@FXML
	private Button closeBtn;

	
	public VueAjouterEditionControleur(VuePrincipaleControleur vuePrincipaleControleur) {
		this.vuePrincipaleControleur = vuePrincipaleControleur;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		validerBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ajouterStagiaire();
			}
		});
		
		annulerBtn.setOnAction(new EventHandler<ActionEvent>() {

			
			@Override
			public void handle(ActionEvent event) {
				reset();
			}
		});
		
		closeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				closeStage();
			}
		});
	}

	@FXML
	private void valider() {
//		if(!saisieValide()) {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setHeaderText("Erreur Saisie");
//			String texteAlert;
//			alert.setContentText(texteAlert);
//			alert.show();
//			return;			
//		}
//		Object modele;
//		((Object) modele).addStagiaire(initDonnees());
//		boolean saisieOK = true;
//		secondaryStage.close();
	}
	private Object initDonnees() {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean saisieValide() {
		// TODO Auto-generated method stub
		return false;
	}

	public void ajouterStagiaire() {
		int index = 0;
		// Valider la saisie
		String erreurs = validerSaisie();
		if (erreurs.isEmpty()) {
			Stagiaire stagiaire = new Stagiaire();
			stagiaire.setNom(nomTexteField.getText());
			stagiaire.setPrenom(prenomTexteField.getText());
			stagiaire.setPromotion(promotionTexteField.getText());
			stagiaire.setDepartement(departementTexteField.getText());
			stagiaire.setAnneeObtention(Integer.valueOf(anneeObtentionTexteField.getText()));

			vuePrincipaleControleur.mettreAjourAnnuaire(stagiaire, index);
			vuePrincipaleControleur.mettreAJourVue(stagiaire);
			
			closeStage();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erreurs de saisie : ");
			alert.setContentText(erreurs);
			alert.show();
		}
		index++;
	}
	
	/**
	 * 
	 * @param rootPane
	 */
	public void creeEtAfficheFenetreAjoutStagiaires(Region rootPane) {
		Scene scene = new Scene(rootPane, rootPane.getPrefWidth(), rootPane.getPrefHeight());
		this.ajouterStagiaireStage = new Stage();
		this.ajouterStagiaireStage.setTitle("Ajout d'un nouveau stagiaire");
		this.ajouterStagiaireStage.setScene(scene);
		this.ajouterStagiaireStage.show();
	}

	private void closeStage() {
		ajouterStagiaireStage.close();
	}

	private void reset() {
		nomTexteField.clear();
		prenomTexteField.clear();
		departementTexteField.clear();
		promotionTexteField.clear();
		anneeObtentionTexteField.clear();
	}
	
	private String validerSaisie() {
		StringBuilder errorsBuilder = new StringBuilder();

		// nom
		String nom = nomTexteField.getText();
		if (nom == null || nom.trim().isEmpty()) {
			errorsBuilder.append("Le nom du stagiaire doit Ãªtre renseignÃ©\n");
		}

		//prenom
		String prenom = nomTexteField.getText();
		if (prenom == null || prenom.trim().isEmpty()) {
			errorsBuilder.append("Le prenom du stagiaire doit Ãªtre renseignÃ©e\n");
		}

		// departement
		String departement = departementTexteField.getText();
		if (departement == null || departement.trim().isEmpty()) {
			errorsBuilder.append("Le departement du stagiaire doit Ãªtre renseignÃ©\n");
		} 

		// promotion
		String promotion = promotionTexteField.getText();
		if (promotion == null || promotion.trim().isEmpty()) {
			errorsBuilder.append("La promotion du stagiaire doit Ãªtre renseignÃ©e\n");
		}

		// annee obtention
		String anneeObtention = anneeObtentionTexteField.getText();
		if (anneeObtention == null || anneeObtention.trim().isEmpty()) {
			errorsBuilder.append("L'annee d'obtention du stagiaire doit Ãªtre renseignÃ©e\n");
		}else {
			try {
				Integer.valueOf(anneeObtention);
			} catch (NumberFormatException e) {
				errorsBuilder.append("L'annee d'obtention du stagiaire doit Ãªtre une valeur numÃ©rique\n");
			}
		}
		return errorsBuilder.toString();
	}
}