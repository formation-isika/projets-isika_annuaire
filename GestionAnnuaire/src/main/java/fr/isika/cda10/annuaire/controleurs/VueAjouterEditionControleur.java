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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


	public class VueAjouterEditionControleur implements Initializable {

		// Controleur de la vue principale qu'on utilisera pour mettre Ã  jour la vue
		// principale quand le modÃ¨le va changer
		private VuePrincipaleControleur vuePrinipaleControleur;

		// On mÃ©morise le stage contrÃ´lÃ© pour faire des actions dessus (fermeture par exemple)
		private Stage ajoutStagiaireStage;
		
		// Composants Ã  manipuler par le controleur

		@FXML
		private TextField nomStagiaireTextField;
		
		@FXML
		private TextField prenomStagiaireTextField;

		@FXML
		private TextField departementStagiaireTextField;

		@FXML
		private TextField promotionStagiaireTextField;

		@FXML
		private TextField anneeObtentionStagiaireTextField;

		@FXML
		private Button validateBtn;

		@FXML
		private Button resetBtn;
		
		public VueAjouterEditionControleur(VuePrincipaleControleur vuePrinipaleControleur) {
			this.vuePrinipaleControleur = vuePrinipaleControleur;
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			validateBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					ajoutStagiaire();
				}
			});

			resetBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					reset();
				}
			});

		}

		public void ajoutStagiaire() {
			// Valider la saisie
			String erreurs = validerSaisie();
			if (erreurs.isEmpty()) {
				Stagiaire stagiaire = new Stagiaire();
				stagiaire.setNom(nomStagiaireTextField.getText());
				stagiaire.setPrenom(prenomStagiaireTextField.getText());
				stagiaire.setDepartement(departementStagiaireTextField.getText());
				stagiaire.setPromotion(promotionStagiaireTextField.getText());
				stagiaire.setAnneeObtention(Integer.valueOf(anneeObtentionStagiaireTextField.getText()));

//				vuePrinipaleControleur.mettreAJourModele(stagiaire);
//				vuePrinipaleControleur.mettreAJourVue(stagiaire);
				
				closeStage();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Erreurs de saisie : ");
				alert.setContentText(erreurs);
				alert.show();
			}
		}

		public void creeEtAfficheFenetreAjoutProduits(Region rootPane) {
			Scene scene = new Scene(rootPane, rootPane.getPrefWidth(), rootPane.getPrefHeight());
			this.ajoutStagiaireStage = new Stage();
			this.ajoutStagiaireStage.setTitle("Ajout d'un nouveau produit");
			this.ajoutStagiaireStage.setScene(scene);
			this.ajoutStagiaireStage.show();
		}

		private void closeStage() {
			ajoutStagiaireStage.close();
		}
		
		private void reset() {
			departementStagiaireTextField.clear();
			nomStagiaireTextField.clear();
			prenomStagiaireTextField.clear();
			anneeObtentionStagiaireTextField.clear();
			promotionStagiaireTextField.clear();

		}
		
		/*
		 * MÃ©thodes privÃ©es
		 */
		
		private void preRemplirProduitAleatoire() {
//			Stagiaire stagiaire = generateRandomProduit();
//			nomStagiaireTextField.setText(String.valueOf(stagiaire.getNom()));
//			prenomStagiaireTextField.setText(String.valueOf(stagiaire.getPrenom()));
//			departementStagiaireTextField.setText(stagiaire.getDepartement());
//			promotionStagiaireTextField.setText(String.valueOf(stagiaire.getPromotion()));
//			anneeObtentionStagiaireTextField.setText(String.valueOf(stagiaire.getAnneeObtention()));
		}

//		private Stagiaire generateRandomProduit() {
//			Stagiaire stagiaire = new Stagiaire();
//			
//			// un numÃ©ro random
//			stagiaire.setId(new Random().nextLong());
//			
//			// une dÃ©signation 
//			stagiaire.setDesignation("Produit_Num_" + stagiaire.getId());
//			
//			// Un type alÃ©atoire entre 1 et 5 (voir liste dÃ©roulante ci-dessous pour les index)
//			int randomTypeProduitIndex = new Random().nextInt(5);
////			typeProduitSelectBox.getSelectionModel().select(randomTypeProduitIndex);
//			stagiaire.setTypeProduit(typeProduitSelectBox.getItems().get(randomTypeProduitIndex));
//			
//			// une quantitÃ© random
//			int randomQuantite = new Random().nextInt(50);
//			stagiaire.setQuantite(randomQuantite);
//			
//			// un prix random
//			double randomPrix = new Random().nextDouble();
//			stagiaire.setPrixUnitaire(randomPrix);
//			
//			return stagiaire;
//		}
		
		private String validerSaisie() {
			StringBuilder errorsBuilder = new StringBuilder();
			
			// Nom
			String nom = nomStagiaireTextField.getText();
			if (nom == null || nom.trim().isEmpty()) {
				errorsBuilder.append("Le nom du stagiaire doit être renseigné\n");
				
			}

			// Prenom
			String prenom = prenomStagiaireTextField.getText();
			if (prenom == null || prenom.trim().isEmpty()) {
				errorsBuilder.append("Le prenom du stagiaire doit être renseigné\n");
			
			}

			// Departement
			String departement = departementStagiaireTextField.getText();
			if (departement == null || departement.trim().isEmpty()) {
				errorsBuilder.append("La département du stagiaire doit être renseigné\n");
			}

			// Promotion
			String promotion = promotionStagiaireTextField.getText();
			if (promotion == null || promotion.trim().isEmpty()) {
				errorsBuilder.append("La promotion du stagiaire doit être renseignée\n");
			}

			// Année Obtention
			String anneeObtention = anneeObtentionStagiaireTextField.getText();
			if (anneeObtention == null || anneeObtention.trim().isEmpty()) {
				errorsBuilder.append("L'année du stagiaire doit être renseignée\n");
			} else {
				try {
					Integer.valueOf(anneeObtention);
				} catch (NumberFormatException e) {
					errorsBuilder.append("L'année du stagiaire doit être une valeur numÃ©rique\n");
				}
			}
			return errorsBuilder.toString();

		}

	}
