package fr.isika.cda10.annuaire.controleurs;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
public class VueLoginControleur implements Initializable {
	
	private VuePrincipaleControleur vuePrincipaleControleur;
	private Stage ajouterStagiaireStage;
	
	@FXML
	private TextField identifiantTexteField;
	@FXML
	private TextField passwordTexteField;
	
	@FXML
	private Button loginBtn;
	
	@FXML
	private Button cancelBtn;
	
	public VueLoginControleur() {
	}
	
	public VueLoginControleur(VuePrincipaleControleur vuePrincipaleControleur) {
		this.vuePrincipaleControleur = vuePrincipaleControleur;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				login();
			}
		});
		
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				closeStage();
			}
		});
		
	}
	
	public void login() {
		String erreurs = validerSaisie();
		if (erreurs.isEmpty()) {
			
			closeStage();
			
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erreurs de saisie : ");
			alert.setContentText(erreurs);
			alert.show();
		}
	}
	
	private void closeStage() {
		ajouterStagiaireStage.close();
	}
	
	private String validerSaisie() {
		StringBuilder errorsBuilder = new StringBuilder();
		// identifiant
		String identitifant = identifiantTexteField.getText();
		if (identitifant == null || identitifant.trim().isEmpty()) {
			errorsBuilder.append("L'identitifiant doit Ãªtre renseignÃ©\n");
		}
		//mot de passe
		String password = passwordTexteField.getText();
		if (password == null || password.trim().isEmpty()) {
			errorsBuilder.append("Le mot de passe doit Ãªtre renseignÃ©e\n");
		}
		return errorsBuilder.toString();
	}
	
	/**
	 *
	 * @param rootPane
	 */
	public void creeEtAfficheFenetreLogin(Region rootPane) {
		Scene scene = new Scene(rootPane, rootPane.getPrefWidth(), rootPane.getPrefHeight());
		this.ajouterStagiaireStage = new Stage();
		this.ajouterStagiaireStage.setTitle("Login users");
		this.ajouterStagiaireStage.setScene(scene);
		this.ajouterStagiaireStage.show();
	}
}
