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
	
//	private static final String VUE_AJOUT_PRODUIT_VIEW_PATH = "/GestionAnnuaire/ressources/VuePrincipale.fxml"; //TODO à faire
	
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
	TableColumn<Stagiaire, String> nomCol;
	@FXML
	TableColumn<Stagiaire, String> prenomCol;
	@FXML
	TableColumn<Stagiaire, String> promotionCol;
	@FXML
	TableColumn<Stagiaire, String> departementCol;
	@FXML
	TableColumn<Stagiaire, Integer> anneeObtentionCol;
	
	private static final int INDEX_COLONNE_NOM = 0;
	private static final int INDEX_COLONNE_PRENOM = 1;
	private static final int INDEX_COLONNE_PROMOTION = 2;
	private static final int INDEX_COLONNE_DEPARTEMENT = 3;
	private static final int INDEX_COLONNE_ANNEE_OBTENTION = 4;
	
//	private AnnuaireControleur annuaireControleur;
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
	
	@SuppressWarnings("unchecked")
	private void initTableView() {
//		 On rÃ©cupÃ¨re les colonnes par index (0 : nom, 1 : prenom .... 4 : anneeObtention)		
		TableColumn<Stagiaire, String>nomCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_NOM);
		TableColumn<Stagiaire, String>prenomCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_PRENOM);
		TableColumn<Stagiaire, String>promotionCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_PROMOTION);
		TableColumn<Stagiaire, String>departementCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_DEPARTEMENT);
		TableColumn<Stagiaire, Integer>anneeObtentionCol = (TableColumn<Stagiaire, Integer>) stagiairesTable.getColumns().get(INDEX_COLONNE_ANNEE_OBTENTION);
		
		// On associe les colonnes de la table aux champs de l'objet qu'elle vont contenir
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		promotionCol.setCellValueFactory(new PropertyValueFactory<>("promotion"));
		departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
		anneeObtentionCol.setCellValueFactory(new PropertyValueFactory<>("annee obtention"));
		listeDynamiqueStagiaires = FXCollections.observableArrayList(a.getListStagiaire());
		stagiairesTable.setItems(listeDynamiqueStagiaires);
	}
	
	
}
