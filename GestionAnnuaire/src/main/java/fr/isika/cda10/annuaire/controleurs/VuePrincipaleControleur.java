package fr.isika.cda10.annuaire.controleurs;

import java.net.URL;
import java.util.ResourceBundle;
import fr.isika.cda10.annuaire.app.LanceurAnnuaire;
import fr.isika.cda10.annuaire.models.Annuaire;
import fr.isika.cda10.annuaire.models.ModelePrincipal;
import fr.isika.cda10.annuaire.models.Stagiaire;
import javafx.application.Platform;
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
	private Button nouveauBtn;
	@FXML
	private Button supprimerBtn;
	@FXML
	private Button modifierBtn;
	@FXML
	private Button impressionBtn;
	@FXML
	private Button documentationBtn;
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
	private static final int INDEX_COLONNE_DEPARTEMENT = 2;
	private static final int INDEX_COLONNE_PROMOTION = 3;
	private static final int INDEX_COLONNE_ANNEE_OBTENTION = 4;
	
	private ObservableList<Stagiaire> listeDynamiqueStagiaires;
	private Annuaire a;
	public ModelePrincipal modele;
	private LanceurAnnuaire lanceurAnnuaire;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modele = new ModelePrincipal();
		a = new Annuaire();
		a.initierArbreBinaireAPartirDuFichierStagiairesDon();
		initTableView();
		
	}
	
	@SuppressWarnings("unchecked")
	private void initTableView() {
		// On rÃ©cupÃ¨re les colonnes par index (0 : nom, 1 : prenom .... 4 : anneeObtention)		
		TableColumn<Stagiaire, String> nomCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_NOM);
		TableColumn<Stagiaire, String> prenomCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_PRENOM);
		TableColumn<Stagiaire, String> departementCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_DEPARTEMENT);
		TableColumn<Stagiaire, String> promotionCol = (TableColumn<Stagiaire, String>) stagiairesTable.getColumns().get(INDEX_COLONNE_PROMOTION);
		TableColumn<Stagiaire, Integer> anneeObtentionCol = (TableColumn<Stagiaire, Integer>) stagiairesTable.getColumns().get(INDEX_COLONNE_ANNEE_OBTENTION);
		// On associe les colonnes de la table aux champs de l'objet qu'elle vont contenir
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
		promotionCol.setCellValueFactory(new PropertyValueFactory<>("promotion"));
		anneeObtentionCol.setCellValueFactory(new PropertyValueFactory<>("anneeObtention"));
		listeDynamiqueStagiaires = FXCollections.observableArrayList(a.getListStagiaireDansArbreBinaire());
		stagiairesTable.setItems(listeDynamiqueStagiaires);
	}
	
	public void setMainApp(LanceurAnnuaire lanceurAnnuaire) {
		this.lanceurAnnuaire = lanceurAnnuaire;
		listeDynamiqueStagiaires = FXCollections.observableArrayList(modele.getListeStagiaire());
		stagiairesTable.setItems(listeDynamiqueStagiaires);
	}
	@FXML
	private void ajouterStagiaire() {
		if(lanceurAnnuaire.initVueAjouterEdition(modele, null)) {
			listeDynamiqueStagiaires.add(modele.getStagiaire(modele.getListeStagiaire().size() - 1)); // size parcourue tableau et le - 1, c'est car tableau, index commence à 0
			// cette ligne 84 permet de mettre l'objet ajoute à la fin du tableau
		}
	}
	@FXML
	private void quitter() {
		Platform.exit();
	}
}