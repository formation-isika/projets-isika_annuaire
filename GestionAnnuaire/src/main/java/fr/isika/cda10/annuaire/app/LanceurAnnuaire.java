package fr.isika.cda10.annuaire.app;

import java.io.IOException;

import fr.isika.cda10.annuaire.controleurs.VuePrincipaleControleur;
import fr.isika.cda10.annuaire.models.Annuaire;
import fr.isika.cda10.annuaire.models.ArbreBinaire;
import fr.isika.cda10.annuaire.models.Stagiaire;
import fr.isika.cda10.annuaire.vues.VuePrincipale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LanceurAnnuaire extends Application {

	private Stage primaryStage;
	private BorderPane vuePrincipale;
	private static VuePrincipale vuePrincip;


	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Gestions Annuaire");
		initVuePrincipale();
		vuePrincip = new VuePrincipale(primaryStage);
	}

	private void initVuePrincipale() {
		try {
			//Charge la Vue Principale depuis fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LanceurAnnuaire.class.getResource("vues/VuePrincipale.fxml"));
			vuePrincipale = (BorderPane) loader.load();
			VuePrincipaleControleur controleur = loader.getController();
		//	controleur.setLanceurAnnuaire(this);
			Scene scene = new Scene(vuePrincipale);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testMethod() {
		Annuaire a = new Annuaire();
		a.initierArbreBinaireAPartirDuFichierStagiairesDon();
		
	}

	 public static void main(String[] args) {
			//launch(args);
		 new LanceurAnnuaire().testMethod();

	 }

}
