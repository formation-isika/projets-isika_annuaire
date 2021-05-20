package fr.isika.cda10.annuaire.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AnnuaireControleur implements Initializable {
	
	private VuePrincipaleControleur vuePrincipaleControleur;
	
	private Stage ajoutStagiaireStage;
	
	@FXML
	private TextField nomStagiaireTextField;

	@FXML
	private TextField prenomStagiaireTextField;

	@FXML
	private TextField departementStagiaireTextField;
	
	@FXML
	private TextField anneeObtentionStagiaireTextField;

	@FXML
	private Button validateBtn;
	
	@FXML
	private Button modifierBtn;
	
	@FXML
	private Button supprimerBtn;
	
	@FXML
	private Button editerBtn;

	@FXML
	private Button resetBtn;

	@FXML
	private Button closeBtn;
	
	
	public AnnuaireControleur(VuePrincipaleControleur vuePrincipaleControleur) {
		this.vuePrincipaleControleur = vuePrincipaleControleur;
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
		validateBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ajoutStagiaire();
			}
		});
		
		modifierBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				modifierStagiaire();
			}
		});
		
		supprimerBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				supprimerStagiaire();
			}
		});
		
		editerBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				editerStagiaire();
			}
		});

		resetBtn.setOnAction(new EventHandler<ActionEvent>() {

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
	
	public void ajoutStagiaire() {
		//TODO reflechir comment faire
	}
	
	public void modifierStagiaire() {
		//TODO reflechir comment faire
	}
	
	public void supprimerStagiaire() {
		//TODO reflechir comment faire
	}
	
	public void editerStagiaire() {
		//TODO reflechir comment faire
	}
	
	public void reset() {
		//TODO reflechir comment faire
	}
	
	public void closeStage() {
		
	}

	

}
