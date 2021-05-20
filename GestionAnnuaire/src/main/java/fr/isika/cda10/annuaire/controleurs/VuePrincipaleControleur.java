package fr.isika.cda10.annuaire.controleurs;

import java.net.URL;
import java.util.ResourceBundle;
import fr.isika.cda10.annuaire.models.Annuaire;
import fr.isika.cda10.annuaire.models.ModelePrincipal;
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
	
	//private static final String VUE_AJOUT_PRODUIT_VIEW_PATH = "/GestionAnnuaire/ressources/VuePrincipale.fxml"; //TODO à faire
	
	@FXML
	private TableView<Stagiaire> stagiairesTable;
	@FXML
	private Button ajouteStagiaireBtn;
	@FXML
	private Button supprimerStagiaireBtn;
	@FXML
	private Button modifierStagiaireBtn;
	@FXML
	private Button btnImpression;
	@FXML
	private Button btnRecherche;
	@FXML
	private Button btnDocumentation;
	@FXML
	private TableColumn<Stagiaire, String> nomCol;
	@FXML
	private TableColumn<Stagiaire, String> prenomCol;
	@FXML
	private TableColumn<Stagiaire, String> promotionCol;
	@FXML
	private TableColumn<Stagiaire, String> departementCol;
	@FXML
	private TableColumn<Stagiaire, Integer> anneeObtentionCol;
	
	private static final int INDEX_COLONNE_NOM = 0;
	private static final int INDEX_COLONNE_PRENOM = 1;
	private static final int INDEX_COLONNE_PROMOTION = 2;
	private static final int INDEX_COLONNE_DEPARTEMENT = 3;
	private static final int INDEX_COLONNE_ANNEE_OBTENTION = 4;
	
	private Annuaire modelGlobalAnnuaire;
	private ObservableList<Stagiaire> listeDynamiqueStagiaires;
	private Annuaire a;
	public ModelePrincipal modele;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modele = new ModelePrincipal();
		a = new Annuaire();
		a.initierArbreBinaireAPartirDuFichierStagiairesDon();
		initTableView();
	}
	
	private void initTableView() {
		// On rÃ©cupÃ¨re les colonnes par index (0 : nom, 1 : prenom .... 4 : anneeObtention)		
		nomCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_NOM);
		prenomCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_PRENOM);
		promotionCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_PROMOTION);
		departementCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_DEPARTEMENT);
		anneeObtentionCol = (TableColumn<Stagiaire, Integer>) stagiairesTable.getColumns().get(INDEX_COLONNE_ANNEE_OBTENTION);
		// On associe les colonnes de la table aux champs de l'objet qu'elle vont contenir
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		promotionCol.setCellValueFactory(new PropertyValueFactory<>("promotion"));
		departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
		anneeObtentionCol.setCellValueFactory(new PropertyValueFactory<>("annee obtention"));
		listeDynamiqueStagiaires = FXCollections.observableArrayList(this.modelGlobalAnnuaire.getListStagiaire());
		stagiairesTable.setItems(listeDynamiqueStagiaires);
	}
	
	
}
