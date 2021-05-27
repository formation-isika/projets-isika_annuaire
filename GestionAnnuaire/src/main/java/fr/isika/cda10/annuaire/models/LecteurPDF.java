package fr.isika.cda10.annuaire.models;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import javax.swing.JPanel;
import com.adobe.acrobat.Viewer;

public class LecteurPDF extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Viewer viewer;
    
    public LecteurPDF(String nomfichier) {
    	this.setLayout(new BorderLayout()); 
        //créer le viewer qui va servir à afficher le contenu du pdf
        try {
			viewer = new Viewer();
			this.add(viewer, BorderLayout.CENTER);
	        FileInputStream fis = new FileInputStream(nomfichier);
	        viewer.setDocumentInputStream(fis);
	        viewer.activate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
