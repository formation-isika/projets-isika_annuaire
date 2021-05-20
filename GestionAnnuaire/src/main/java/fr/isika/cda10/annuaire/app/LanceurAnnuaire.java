package fr.isika.cda10.annuaire.app;
import java.io.IOException;
import java.net.URL;

import fr.isika.cda10.annuaire.controleurs.VuePrincipaleControleur;
import fr.isika.cda10.annuaire.models.Annuaire;
import fr.isika.cda10.annuaire.models.ArbreBinaire;
import fr.isika.cda10.annuaire.models.Stagiaire;
import fr.isika.cda10.annuaire.vues.VuePrincipale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LanceurAnnuaire extends Application {
	private Stage primaryStage;
	private VuePrincipaleControleur vuePrincipaleControleur;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Gestions Annuaire");
		initVuePrincipale();
	}
	private void initVuePrincipale() {
		try {
			// Charge la Vue Principale depuis fxml file
			URL chemin = getClass().getClassLoader().getResource("vues/VuePrincipale.fxml");
			FXMLLoader loader = new FXMLLoader(chemin);
			loader.setController(vuePrincipaleControleur);
			BorderPane parent = loader.load();
			vuePrincipaleControleur = new VuePrincipaleControleur();
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}





