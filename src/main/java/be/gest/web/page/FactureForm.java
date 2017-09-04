package be.gest.web.page;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

public class FactureForm extends FormLayout {

	private NativeSelect nomFour = new NativeSelect("fournisseur");
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
	
	private PopupDateField dateFacture = new PopupDateField("Date Facturation");
	private PopupDateField echeanceFacture = new PopupDateField("Date echeance");
}
