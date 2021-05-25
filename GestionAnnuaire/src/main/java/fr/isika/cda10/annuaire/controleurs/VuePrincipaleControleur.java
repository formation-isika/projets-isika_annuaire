package fr.isika.cda10.annuaire.controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.isika.cda10.annuaire.app.LanceurAnnuaire;
import fr.isika.cda10.annuaire.models.Annuaire;
import fr.isika.cda10.annuaire.models.ModelePrincipal;
import fr.isika.cda10.annuaire.models.Stagiaire;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class VuePrincipaleControleur implements Initializable {
	
	private static final String VUE_AJOUT_STAGIAIRE_VIEW_PATH = "vues/VueAjouterEdition.fxml"; 
	
	@FXML
	private TableView<Stagiaire> stagiairesTable;
	@FXML
	private Button ajouteStagiaireBtn;
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
	private VueAjouterEditionControleur gestionAnnuaireControleur;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modele = new ModelePrincipal();
		a = new Annuaire();
		a.initierArbreBinaireAPartirDuFichierStagiairesDon();
		initTableView();
		
		// Ajout de l'action du bouton ajouter stagiaire
		ajouteStagiaireBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					afficherFenetreAjoutStagiaire();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
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
	
	public void mettreAjourAnnuaire(Stagiaire stagiaire, int index) {
		a.ajouterStagiaireDansLeNoeud(stagiaire, index);
	}
	
	public void mettreAJourVue(Stagiaire stagiaire) {
		mettreAJoutTable(stagiaire);
	}
	
	private void mettreAJoutTable(Stagiaire stagiaire) {
		listeDynamiqueStagiaires.add(stagiaire);
		stagiairesTable.refresh();
	}
	
	/**
	 * Affiche la fenÃªtre d'ajout de stagiaire
	 * 
	 * @throws IOException
	 */
	public void afficherFenetreAjoutStagiaire() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(VUE_AJOUT_STAGIAIRE_VIEW_PATH));
		 gestionAnnuaireControleur = new VueAjouterEditionControleur(this);
		loader.setController(gestionAnnuaireControleur);
		AnchorPane rootPane = loader.load();
		gestionAnnuaireControleur.creeEtAfficheFenetreAjoutStagiaires(rootPane);
	}
	
	@FXML
	private void quitter() {
		Platform.exit();
	}
}