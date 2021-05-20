package fr.isika.cda10.annuaire.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import fr.isika.cda10.annuaire.models.Annuaire;
import fr.isika.cda10.annuaire.models.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VuePrincipaleControleur implements Initializable {
	
	private static final String VUE_AJOUT_PRODUIT_VIEW_PATH = "ressources/vues/VueAjoutStagiaire.fxml"; //TODO à faire
	
	@FXML
	private TableView<Stagiaire> stagiairesTable;
	
	@FXML
	private Button ajouteSatgiareBtn;

	@FXML
	private Button supprimerStagiaireBtn;
	
	@FXML
	private Button modifierStagiaireBtn;
	
	private static final int INDEX_COLONNE_NOM = 0;
	private static final int INDEX_COLONNE_PRENOM = 1;
	private static final int INDEX_COLONNE_PROMOTION = 2;
	private static final int INDEX_COLONNE_DEPARTEMENT = 3;
	private static final int INDEX_COLONNE_ANNEE_OBTENTION = 4;
	
	private AnnuaireControleur annuaireControleur;
	private Annuaire modelGlobalAnnuaire;
	private ObservableList<Stagiaire> listeDynamiqueStagiaires;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		annuaireControleur = new AnnuaireControleur(this);
		modelGlobalAnnuaire = new Annuaire();
		
		// Init la table
	    initStagiairesTable();
	}
	
	@SuppressWarnings("unchecked")
	private void initStagiairesTable() {
		// On rÃ©cupÃ¨re les colonnes par index (0 : nom, 1 : prenom .... 4 : anneeObtention)		
		TableColumn<Stagiaire, String> nomCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_NOM);
		TableColumn<Stagiaire, String> prenomCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_PRENOM);
		TableColumn<Stagiaire, String> promotionCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_PROMOTION);
		TableColumn<Stagiaire, String> departementCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_DEPARTEMENT);
		TableColumn<Stagiaire, Integer> anneObtentionCol = (TableColumn<Stagiaire, Integer>) stagiairesTable.getColumns().get(INDEX_COLONNE_ANNEE_OBTENTION);

		// On associe les colonnes de la table aux champs de l'objet qu'elle vont
		// contenir
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		promotionCol.setCellValueFactory(new PropertyValueFactory<>("promotion"));
		departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
		anneObtentionCol.setCellValueFactory(new PropertyValueFactory<>("annee obtention"));

		listeDynamiqueStagiaires = FXCollections.observableArrayList(this.modelGlobalAnnuaire.getListStagiaire());
		stagiairesTable.setItems(listeDynamiqueStagiaires);
	}

}
