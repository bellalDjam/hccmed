package be.gest.web.page;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FournisseursForm extends GridLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -204031239305746460L;
	
	private TextField nomFour = new TextField("Nom Du Fournisseur");
	private TextField numTva = new TextField("Num de TVA");
	private TextField article = new TextField("Article");
	private TextField numRgistre = new TextField("Num de Rgistre");
	
	private TextField nomCommune = new TextField("Commune");

	private TextField boitePostale = new TextField("code Postal");
	private TextField codePostal = new TextField("code Postal");
	private TextField rue = new TextField("La Rue");
	private TextField numero = new TextField("Numero");

	private TextField numFax = new TextField("Fax");
	private TextField numTel = new TextField("Tel");
	private TextField numGSM = new TextField("GSM");
	private TextField email = new TextField("Email");
	
	public FournisseursForm(){
		super(2,4);
		
		addComponent(nomFour);
		addComponent(numTva);
		addComponent(article);
		addComponent(numRgistre);
		addComponent(nomCommune);
		addComponent(codePostal);
		addComponent(boitePostale);
		addComponent(rue);
		addComponent(numero);
		addComponent(numFax);
		addComponent(numTel);
		addComponent(numGSM);
		addComponent(email);

		
	}
	
}
