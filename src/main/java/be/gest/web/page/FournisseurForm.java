package be.gest.web.page;


import org.springframework.beans.factory.annotation.Autowired;

import be.gest.dao.AdresseDAO;
import be.gest.dao.FournisseurDAO;
import be.gest.model.Address;
import be.gest.model.Fournisseur;
import be.gest.web.MainUI;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class FournisseurForm extends FormLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2883363833710887547L;
	@Autowired
	private FournisseurDAO fournisseurDAO;
	private Fournisseur fournisseur;
	@Autowired
	private AdresseDAO adresseDAO;
	private Address adresse;

	// private MyUI myUI;
	private MainUI mainUI;
	private TextField nomFour = new TextField("Nom Du Fournisseur");
	private TextField numTva = new TextField("Num de TVA");
	private TextField article = new TextField("Article");
	private TextField numRgistre = new TextField("Num de Rgistre");

	private TextField nomCommune = new TextField("Commune");

	private TextField codePostal = new TextField("code Postal");
	private TextField rue = new TextField("La Rue");
	private TextField numero = new TextField("Numero");

	private TextField numFax = new TextField("Fax");
	private TextField numTel = new TextField("Tel");
	private TextField numGSM = new TextField("GSM");
	private TextField email = new TextField("Email");

	private Button save = new Button("Save");
	private Button delete = new Button("Delete");

	public FournisseurForm(MainUI mainUI) {
		this.mainUI = mainUI;
		
		save.setClickShortcut(KeyCode.ENTER);
		 save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		 save.addClickListener(e -> this.save());
		 delete.addClickListener(e -> this.delete());
		setSizeUndefined();

	HorizontalLayout buttons = new HorizontalLayout(save, delete);

		buttons.setSpacing(true);

		addComponents(nomFour, numTva, article, numRgistre, nomCommune,
				codePostal, rue, numero, numFax, numTel, numGSM, email, buttons);
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
		BeanFieldGroup.bindFieldsUnbuffered(fournisseur, this);

		// Show delete button for only customers already in the database
		delete.setVisible((fournisseur.getId()) != null);
		setVisible(true);
		nomFour.selectAll();
	}

	private void delete() {
		fournisseurDAO.delete(fournisseur);
//		mainUI.buildFournisseur( );
		setVisible(false);
	}

	private void save() {
		fournisseurDAO.save(fournisseur);
//		mainUI.updateListeFour();
		setVisible(false);
	}

}
