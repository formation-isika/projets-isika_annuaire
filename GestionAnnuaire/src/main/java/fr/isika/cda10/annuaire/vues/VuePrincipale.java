package fr.isika.cda10.annuaire.vues;

import fr.isika.cda10.annuaire.models.ArbreBinaire;
import fr.isika.cda10.annuaire.models.Stagiaire;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VuePrincipale {

	private TableView <ArbreBinaire<Stagiaire>> tableStagiaires;
	private Stage primaryStage;
	public VuePrincipale(Stage primaryStage) {
		this.primaryStage = primaryStage;
		initVue();
	}
	private void initVue() {
		// 1 - Créer la table et l'ajouter dans un panel
		creerTableStagiaire();

		// 2 - Ajouter la table
		
		GridPane grid = new GridPane();
		grid.addRow(0, tableStagiaires);

		Pane panelPrincipal = new Pane();
		panelPrincipal.setPrefSize(600, 500);
		panelPrincipal.getChildren().add(grid);
		Scene scene = new Scene(panelPrincipal, panelPrincipal.getPrefWidth(), panelPrincipal.getPrefHeight());
		primaryStage.setScene(scene);
		primaryStage.setTitle("TableView Demo app");
		primaryStage.show();
	}

	private void creerTableStagiaire() {
		
		tableStagiaires = new TableView<ArbreBinaire<Stagiaire>>();
		
		// Déclarer les colonnes de la table !!!
		// On récupère les colonnes par index (0 : id, 1 : désignation .... 4 : prix unitaire)		
		TableColumn<ArbreBinaire<Stagiaire>, ?> nomCol = new TableColumn<ArbreBinaire<Stagiaire>, String>("Nom");
		TableColumn<ArbreBinaire<Stagiaire>, ?> prenomCol  = new TableColumn<ArbreBinaire<Stagiaire>, String>("Prenom");
		TableColumn<ArbreBinaire<Stagiaire>, ?> departementCol  = new TableColumn<ArbreBinaire<Stagiaire>, String>("Departement");
		TableColumn<ArbreBinaire<Stagiaire>, ?> promotionCol    = new TableColumn<ArbreBinaire<Stagiaire>, String>("Promotion");
		TableColumn<ArbreBinaire<Stagiaire>, ?> anneeObtentionCol = new TableColumn<ArbreBinaire<Stagiaire>, Integer>("Année Obtention");
		// On associe les colonnes de la table aux champs de l'objet qu'elle vont
		// contenir
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
		promotionCol.setCellValueFactory(new PropertyValueFactory<>("promotion"));
		anneeObtentionCol.setCellValueFactory(new PropertyValueFactory<>("anneeObtention"));

		//ajouter les colonnes a la table
		tableStagiaires.getColumns().add(nomCol);
		tableStagiaires.getColumns().add(prenomCol);
		tableStagiaires.getColumns().add(departementCol);
		tableStagiaires.getColumns().add(promotionCol);
		tableStagiaires.getColumns().add(anneeObtentionCol);
		
	}
}
