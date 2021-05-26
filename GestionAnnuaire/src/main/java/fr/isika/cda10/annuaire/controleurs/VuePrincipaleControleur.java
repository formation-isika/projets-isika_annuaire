package fr.isika.cda10.annuaire.controleurs;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fr.isika.cda10.annuaire.models.Annuaire;
import fr.isika.cda10.annuaire.models.Stagiaire;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class VuePrincipaleControleur implements Initializable {

	private static final String VUE_AJOUT_STAGIAIRE_VIEW_PATH = "vues/VueAjouterEdition.fxml"; 

	private static final String VUE_LOGIN_VIEW_PATH = "vues/VueLogin.fxml";

	@FXML
	private TableView<Stagiaire> stagiairesTable;
	@FXML
	private Button ajouteStagiaireBtn;
	@FXML
	private Button supprimerStagiaireBtn;
	@FXML
	private Button btnLogin;
	@FXML
	private Button modifierStagiaireBtn;
	@FXML
	private Button impressionBtn;
	@FXML
	private Button documentationBtn;
	@FXML
	private Button rechercheBtn;
	@FXML
	private Button rafraichirBtn;
	@FXML
	private TextField rechercheTF;
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
	private VueAjouterEditionControleur gestionAnnuaireControleur;

	private VueLoginControleur gestionLoginControleur;

	private Stagiaire selectedStagiaire = new Stagiaire();
	boolean isModif;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		a = new Annuaire();
		a.initierArbreBinaireAPartirDuFichierStagiairesDon();
		initTableView();

		stagiairesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stagiaire>() {

			@Override
			public void changed(ObservableValue<? extends Stagiaire> observable, Stagiaire oldValue,
					Stagiaire newValue) {
				selectedStagiaire = newValue;
				isModif = true;
			}
		});

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

		modifierStagiaireBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					afficherFenetreAjoutStagiaire();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		rechercheBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				rechercheStagiaire();
			}
		});

		rafraichirBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				initTableView();
			}
		});


		supprimerStagiaireBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				supprimerStagiaireSelectionne();
			}
		});


		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					afficherFenetreLogin();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// Ajout de l'action du bouton impression 
		impressionBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					String file_name = "C:\\Users\\micka\\Desktop\\Dev\\Projet1\\projets-isika_annuaire\\GestionAnnuaire\\Projet1.pdf";
					Document document = new Document();
					PdfWriter.getInstance(document, new FileOutputStream(file_name));

					Annuaire a = new Annuaire();
					a.initierArbreBinaireAPartirDuFichierStagiairesDon();

					List<Stagiaire> lesStagiaires = a.getListStagiaireDansArbreBinaire();

					document.open();

					PdfPTable table = new PdfPTable(5);
					PdfPCell c1 = new PdfPCell(new Phrase("Nom"));
					table.addCell(c1);

					c1 = new PdfPCell(new Phrase("Prenom"));
					table.addCell(c1);

					c1 = new PdfPCell(new Phrase("Departement"));
					table.addCell(c1);

					c1 = new PdfPCell(new Phrase("Promotion"));
					table.addCell(c1);

					c1 = new PdfPCell(new Phrase("Annee Obtention"));
					table.addCell(c1);
					table.setHeaderRows(1);


					for (Stagiaire stagiaire : lesStagiaires) {
						table.addCell(stagiaire.getNom());
						table.addCell(stagiaire.getPrenom());
						table.addCell(stagiaire.getDepartement());
						table.addCell(stagiaire.getPromotion());
						table.addCell(stagiaire.getAnneeObtention().toString());

					}

					document.add(table);

					document.close();

					System.out.println("Test fini");
					
					

				} catch (Exception e) {
					// TODO Auto-generated catch block
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
		stagiairesTable.refresh();
	}

	/**
	 * Permet de mettre à jour un stagiaire
	 */
	public void miseAjourStagiaire(Stagiaire stagiaire) {
		stagiairesTable.getItems().set(stagiairesTable.getSelectionModel().getSelectedIndex(), stagiaire);
		stagiairesTable.refresh();
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

	public void afficherFenetreLogin() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(VUE_LOGIN_VIEW_PATH));
		gestionLoginControleur = new VueLoginControleur(this);
		loader.setController(gestionLoginControleur);
		AnchorPane rootPane = loader.load();
		gestionLoginControleur.creeEtAfficheFenetreLogin(rootPane);
	}
	
	private ObservableList<Stagiaire> supprimerStagiaireSelectionne() {
		Stagiaire selectionStagiaire = stagiairesTable.getSelectionModel().getSelectedItem();
		if (selectionStagiaire != null) {
			a.supprimerUnStagiaireDansArbreBinaire(listeDynamiqueStagiaires, selectionStagiaire.getNom());
			listeDynamiqueStagiaires.remove(selectionStagiaire);
			stagiairesTable.refresh();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Suppression d'un stagiaire");
			alert.setHeaderText("Veuillez selectionner un stagiaire a supprimer");
			alert.show();
		}
		return listeDynamiqueStagiaires;
	}

	private ObservableList<Stagiaire> rechercheStagiaire() {
		String recherche = rechercheTF.getText();
		if(recherche != null) {
			Stagiaire stagiaire = a.rechercherUnStagiaireDansLaListe(listeDynamiqueStagiaires, recherche);
			listeDynamiqueStagiaires.clear();
			listeDynamiqueStagiaires.add(stagiaire);
			stagiairesTable.refresh();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Rechercher un stagiaire");
			alert.setHeaderText("Veuillez saisir un stagiaire");
			alert.show();
		}
		return listeDynamiqueStagiaires;
	}

	@FXML
	private void quitter() {
		Platform.exit();
	}

	public boolean isModeModif() {
		return isModif;
	}

	public Stagiaire getSelectedStagiaire() {
		return selectedStagiaire;
	}

	public void setSelectedStagiaire(Stagiaire selectedStagiaire) {
		this.selectedStagiaire = selectedStagiaire;
	}

}