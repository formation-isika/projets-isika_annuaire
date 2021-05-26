package fr.isika.cda10.annuaire.app;

import java.io.IOException;
import fr.isika.cda10.annuaire.controleurs.VuePrincipaleControleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LanceurAnnuaire extends Application {
	private Stage vuePrincipale;
	private VuePrincipaleControleur controleur;
	@Override
	public void start(Stage primaryStage) {
		vuePrincipale = primaryStage;
		vuePrincipale.setTitle("Gestions Annuaire");
		
		initVuePrincipale();
	}
	private void initVuePrincipale() {
		try {
			// Charge la Vue Principale depuis fxml file
			controleur = new VuePrincipaleControleur();
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("vues/VuePrincipale.fxml"));
			loader.setController(controleur);
			BorderPane root = loader.load();
			Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
			vuePrincipale.setScene(scene);
			
			vuePrincipale.setTitle("Gestion des stagiaires");
			vuePrincipale.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	public Stage getPrimaryStage() {
		return vuePrincipale;
	}

	public static void main(String[] args) {
		launch(args);
	}
}





