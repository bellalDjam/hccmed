package be.gest.web;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.DetailsGenerator;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.RowReference;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;
import com.vaadin.ui.renderers.ProgressBarRenderer;
import com.vaadin.ui.themes.Runo;

import be.gest.dao.AdresseDAO;
import be.gest.dao.BankOrderDAO;
import be.gest.dao.DepartementDAO;
import be.gest.dao.FactureDAO;
import be.gest.dao.FournisseurDAO;
import be.gest.dao.MaterielDAO;
import be.gest.dao.MaterielLocationDAO;
import be.gest.dao.PayementDAO;
import be.gest.dao.PersonneDAO;
import be.gest.model.Address;
import be.gest.model.BankOrder;
import be.gest.model.Departement;
import be.gest.model.Facture;
import be.gest.model.Fournisseur;
import be.gest.model.Materiel;
import be.gest.model.MaterielLocation;
import be.gest.model.MoyenContact;
import be.gest.model.Personne;
import be.gest.model.enumeration.EtatFacture;
import be.gest.model.enumeration.TypeMateriel;
import be.gest.service.PayementService;
import be.gest.web.page.AbstractPage;
import be.gest.web.page.CustomCalendar;
import be.gest.web.util.PageContainer;

@Theme("runo")
@PreserveOnRefresh
@Title("HCCMED")
@Component
@Scope("prototype")
@SpringUI
public class MainUI extends UI implements ViewDisplay {

	private static final long serialVersionUID = 1L;
	final String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	final ComboBox cbMateriel = new ComboBox();
	final ComboBox cbDepartement = new ComboBox();
	final SpringContextHelper helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
	final MaterielDAO materielDAO = (MaterielDAO) helper.getBean("materielDAO");
	final DepartementDAO departementDAO = (DepartementDAO) helper.getBean("departementDAO");
	final PersonneDAO personneDAO = (PersonneDAO) helper.getBean("personneDAO");
	final MaterielLocationDAO materielLocationDAO = (MaterielLocationDAO) helper.getBean("materielLocationDAO");

	TreeTable tft;
	Table tableMatriel;
	Table tableDepartements;
	Table tableMatrielLocation;
	Table tablePersonnes;

	private Long matSelectionne;
	private Long persSelectionne;
	private Long tarifSelectionne;
	@Autowired
	private BankOrderDAO bankOrderDAO;
	@Autowired
	private FournisseurDAO fournisseurDAO;
	@Autowired
	private FactureDAO factureDAO;
	@Autowired
	private AdresseDAO adresseDAO;

	@Autowired
	private PayementDAO payementDao;
	@Autowired
	private PayementService payementService;

	private enum Mode {
		MONTH, WEEK, DAY;
	}

	Grid grid1 = new Grid();
	private final Map<String, Object> services = new HashMap<String, Object>();

	private PageContainer pageContainer;

	final Button payer = new Button("Payer");
	private boolean testBench = false;
	private Locale defaultLocale = Locale.US;
	private ComboBox timeZoneSelect;
	private ComboBox formatSelect;
	private static final String DEFAULT_ITEMID = "DEFAULT";
	private BasicEventProvider dataSource;
	private String calendarHeight = null;
	private Date currentMonthsFirstDate;

	private final Label captionLabel = new Label("");
	private String calendarWidth = null;

	private Integer firstHour;
	private static final String FORMAT = "%1$td/%1$tm/%1$tY ";
	private Integer lastHour;
	private CheckBox hideWeekendsButton;
	private Integer firstDay;
	private Button nextButton, prevButton, monthButton;
	private Integer lastDay;

	/**
	 * This Gregorian calendar is used to control dates and time inside of this
	 * test application.
	 */
	private GregorianCalendar calendar;
	/** Target calendar component that this test application is made for. */
	private Calendar calendarComponent;
	private Mode viewMode = Mode.MONTH;

	private TabSheet right = new TabSheet();
	// private List<Long> idSF;

	public MainUI() {
//		setService(HistoryService.class, new HistoryService(this, this));
	}

	/**
	 * This public alias for #init only exists for unit tests.
	 */
	public void doInit() {
		pageContainer = new PageContainer();

		VerticalLayout root = new VerticalLayout();
		root.setMargin(true);
		root.setSizeFull();

		Panel mainPanel = new Panel(); // for scrollbars.
		mainPanel.setContent(root);
		mainPanel.setHeight("1000px");

		Label title = new Label("GestionMed");
		title.addStyleName(Runo.LABEL_H1);
		title.setSizeUndefined();
		root.addComponent(title);
		root.setComponentAlignment(title, Alignment.TOP_CENTER);

		Label slogan = new Label("Gestion HCCMEDICALE");
		slogan.addStyleName(Runo.LABEL_SMALL);
		slogan.setSizeUndefined();
		root.addComponent(slogan);
		root.setComponentAlignment(slogan, Alignment.TOP_CENTER);

		Label spacer = new Label("");
		spacer.setHeight("10px");
		root.addComponent(spacer);

		final HorizontalSplitPanel split = new HorizontalSplitPanel();
		split.setStyleName(Runo.SPLITPANEL_REDUCED);
		split.setSplitPosition(1, Unit.PIXELS);
		split.setLocked(true);
		root.addComponent(split);
		root.setExpandRatio(split, 1);

		Panel left = new Panel("Servers Monitoring");
		left.setSizeFull();
		split.setFirstComponent(left);

		right.setSizeFull();
		split.setSecondComponent(right);
		split.setLocked(true);
	
		right.addTab(buildEcheance(), " Echeance", null);
		right.addTab(buildToPay(), " Les Payements", null);
		right.addTab(buildBankOrder(), " Cheques", null);
		right.addTab(buildFournisseur(), "Fournisseur ", null);
		right.addTab(buildFactur(), "Factures ", null);
		right.addTab(buildpayement(), " Payer", null);

		setContent(mainPanel);

		// Create tab content dynamically when tab is selected
		right.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
			public void selectedTabChange(SelectedTabChangeEvent event) {
				// Find the tabsheet
				TabSheet tabsheet = event.getTabSheet();

				// Find the tab (here we know it's a layout)
				Layout tab = (Layout) tabsheet.getSelectedTab();

				// Get the tab caption from the tab object
				String caption = tabsheet.getTab(tab).getCaption();
				if ("Echeance".equals(caption)) {

					tab.removeAllComponents();
					tab.addComponent(buildEcheance());
				} else if ("Cheques".equals(caption)) {

					tab.removeAllComponents();
					tab.addComponent(buildBankOrder());
				} else if ("Fournisseur".equals(caption)) {

					tab.removeAllComponents();
					tab.addComponent(buildFournisseur());
				} else if ("Factures".equals(caption)) {

					tab.removeAllComponents();
					tab.addComponent(buildFactur());
				} else if ("Payement".equals(caption)) {

					tab.removeAllComponents();
					tab.addComponent(buildpayement());
				} else if ("Payer".equals(caption)) {

					tab.removeAllComponents();
					tab.addComponent(buildToPay());
				}
			}

		});

	}



	
	private HorizontalLayout buildEcheance() {

		HorizontalLayout layout = new HorizontalLayout();
		layout.removeAllComponents();

		layout.addComponent(new CustomCalendar());
		return layout;
	}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	VerticalLayout buildToPay() {
		VerticalLayout layout = new VerticalLayout();
		layout.removeAllComponents();
		layout.setMargin(true);
		layout.setSpacing(true);

		Panel paymentsPanel = new Panel();
		paymentsPanel.setSizeFull();
		paymentsPanel.addStyleName(Runo.PANEL_LIGHT);
		layout.addComponent(paymentsPanel);

		Grid grid = new Grid();

		updateFacturePayedList(grid);
		grid.setSizeFull();
		layout.addComponent(grid);
		layout.setExpandRatio(grid, 1);
		return layout;
	}

	// 1212OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
	VerticalLayout buildFactur() {

		VerticalLayout layout = new VerticalLayout();
		layout.removeAllComponents();
		layout.setMargin(true);
		layout.setSpacing(true);

		Panel lesFacturesPanel = new Panel();
		lesFacturesPanel.setSizeFull();
		lesFacturesPanel.addStyleName(Runo.PANEL_LIGHT);
		layout.addComponent(lesFacturesPanel);

		Grid grid = new Grid();

		updateFactureList(grid);
		grid.setSizeFull();

		// HeaderRow filterRow = grid.appendHeaderRow();
		Button ajoutFacture = new Button("Nouvelle Facture", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				final Window subWindow = new Window("Ajouter Facture");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);
				// Put some components in it

				// Center it in the browser window
				subWindow.center();

				HorizontalLayout h = new HorizontalLayout();
				h.setSpacing(true);
				h.setMargin(true);
				HorizontalLayout h1 = new HorizontalLayout();
				h1.setSpacing(true);
				h1.setMargin(true);
				HorizontalLayout h2 = new HorizontalLayout();
				h2.setSpacing(true);
				h2.setMargin(true);

				final DateField dateS = new DateField("Date début echeance");
				// Set the date and time to present
				dateS.setValue(new Date());

				final DateField dateF = new DateField("Date fin echeance");
				// Set the date and time to present
				dateF.setValue(new Date());

				final TextField totFacture = new TextField("montant Facture");
				final ComboBox fournisseur = new ComboBox("fournisseur");

				totFacture.addValidator(
						new StringLengthValidator("La raison Social doit contenir au mois 1 lettres", 1, 20, true));

				totFacture.setImmediate(true);
				totFacture.setRequired(true);
				totFacture.setRequiredError("Entrez votre Montant SVP");
				totFacture.setNullRepresentation("");
				totFacture.setNullSettingAllowed(true);

				h.addComponent(dateS);
				h.addComponent(dateF);
				subContent.addComponent(h);

				h1.addComponents(totFacture, fournisseur);
				subContent.addComponent(h1);

				fournisseur.setCaption("fournisseur");
				for (Fournisseur prov : fournisseurDAO.getAll(Fournisseur.class)) {
					fournisseur.addItem(prov);
					fournisseur.setItemCaption(prov, prov.getNom());
				}

				subWindow.setModal(true);

				Button sauverFacture = new Button("Enregistrer Facture", new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {

						if (dateS.getValue() == null || dateF.getValue() == null) {
							Notification.show("Veuillez introduire les dates !");
							return;
						}

						if (totFacture.getValue() == null) {

							Notification.show("Veulliez introduire le montant!! ");

							return;
						}

						if (totFacture.getValue() != null && (new BigDecimal(totFacture.getValue()).getClass()
								.equals(BigDecimal.class)) == false) {

							Notification.show("Veulliez introduire le montant valide!! ");

							return;
						}
						if (fournisseur.isEmpty()) {

							Notification.show("Veulliez Choisir un fournisseur   !! ");

							return;
						}

						try {
							totFacture.validate();

						} catch (InvalidValueException e) {
							Notification.show(e.getMessage());
							totFacture.setValidationVisible(true);
						}

						Facture mat = new Facture();
						DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
						mat.setDateEcheanceFacture(dateF.getValue());
						mat.setDateFacture(dateS.getValue());
						mat.setSoldFacture(new BigDecimal(totFacture.getValue()));
						mat.setTotFacture(new BigDecimal(totFacture.getValue()));
						mat.setFournisseur((Fournisseur) fournisseur.getValue());
						mat.setEtatFacture(EtatFacture.NONPAYEE);

						//
						factureDAO.save(mat);

						Notification.show("La Facture est bien sauvé ");

						subWindow.close();

						buildFactur();
						Layout tab = (Layout) right.getSelectedTab();
						tab.removeAllComponents();
						// updateFactureList(grid);
						tab.addComponent(buildFactur());

					}
				});

				subContent.addComponent(sauverFacture);

				subContent.setComponentAlignment(sauverFacture, Alignment.BOTTOM_CENTER);
				// Open it in the UI
				addWindow(subWindow);

			}
		});
		// VerticalLayout main = new VerticalLayout(ajoutFacture, grid);
		// main.setSpacing(true);
		//// main.setSizeFull();
		// main.setMargin(true);
		// main.setComponentAlignment(ajoutFacture, Alignment.BOTTOM_CENTER);
		layout.addComponents(ajoutFacture, grid);
		layout.setComponentAlignment(ajoutFacture, Alignment.BOTTOM_CENTER);
		layout.setExpandRatio(grid, 1);
		return layout;
	}

	VerticalLayout buildFournisseur() {
		VerticalLayout layout = new VerticalLayout();
		layout.removeAllComponents();
		layout.setMargin(true);
		layout.setSpacing(true);
		Panel pFournisseurs = new Panel();
		pFournisseurs.setSizeFull();
		pFournisseurs.addStyleName(Runo.PANEL_LIGHT);
		layout.addComponent(pFournisseurs);

		List<Fournisseur> fournisseurs = new ArrayList<>();
		fournisseurs = fournisseurDAO.findAll();
		BeanItemContainer<Fournisseur> container = new BeanItemContainer<Fournisseur>(Fournisseur.class, fournisseurs);

		// add generated

		Grid grid = new Grid();

		grid.addStyleName("valo");
		GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(container);
		grid.setContainerDataSource(gpc);
		gpc.addGeneratedProperty("totalToPay", new PropertyValueGenerator<Double>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4722587125842170066L;

			@Override
			public Double getValue(Item item, Object itemId, Object propertyId) {
				return new Double(getPayementPercentage((Fournisseur) itemId).doubleValue());
			}

			@Override
			public Class<Double> getType() {
				return Double.class;
			}

		});

		container.addNestedContainerBean("address");

		grid.setColumns("nom", "address.rue", "address.numero", "address.nomCommune", "numeroRegistre", "article",
				"numTva", "chiffre", "totalToPay");
		// Grid.HeaderRow filterRow = grid.appendHeaderRow();
		grid.setEditorEnabled(false);
		setFilterGrid(container, grid);
		grid.getColumn("totalToPay").setRenderer(new ProgressBarRenderer());

		// getPage().getStyles().add(".Double { color: blue; }");
		grid.getColumn("totalToPay").setHeaderCaption("To Pay Till Date");
		grid.setSizeFull();
		// FilterRow.add(grid);
		// Grid.HeaderRow filterRow = grid.appendHeaderRow();
		// // Set up a filter for all columns
		// for (Object pid:
		// grid.getContainerDataSource().getContainerPropertyIds()) {
		// Grid.HeaderCell cell = filterRow.getCell(pid);
		// // Have an input field to use for filter
		// TextField filterField = new TextField();
		// filterField.setColumns(8);
		//
		// // Update filter When the filter input is changed
		// filterField.addTextChangeListener(change -> {
		// // Can't modify filters so need to replace
		// container.removeContainerFilters(pid);
		//
		// // (Re)create the filter if necessary
		// if (! change.getText().isEmpty())
		// container.addContainerFilter(
		// new SimpleStringFilter(pid,
		// change.getText(), true, false));
		// });
		// cell.setComponent(filterField);
		// }
		// Grid.HeaderCell reference = filterRow.getCell("nom");
		// reference.getComponent().setVisible(false);

		Button ajoutProvider = new Button("Nouveau Fournisseur", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// Create a sub-window and set the content
				final Window subWindow = new Window("Enregistrer un Nouveau Fournisseur");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setWidth("60%");
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);
				// Put some components in it

				// Center it in the browser window
				subWindow.center();

				HorizontalLayout h0 = new HorizontalLayout();
				h0.setSpacing(true);
				h0.setMargin(true);
				HorizontalLayout h01 = new HorizontalLayout();
				h01.setSpacing(true);
				h01.setMargin(true);
				HorizontalLayout h = new HorizontalLayout();
				h.setSpacing(true);
				h.setMargin(true);
				HorizontalLayout h1 = new HorizontalLayout();
				h1.setSpacing(true);
				h1.setMargin(true);
				HorizontalLayout h2 = new HorizontalLayout();
				h2.setSpacing(true);
				h2.setMargin(true);
				HorizontalLayout h3 = new HorizontalLayout();
				h3.setSpacing(true);
				h3.setMargin(true);

				final TextField nomFour = new TextField("Nom Du Fournisseur");
				final TextField numTva = new TextField("Num de TVA");
				final TextField article = new TextField("Article");
				final TextField numRgistre = new TextField("Num de Rgistre");
				final TextField chiffrAff = new TextField("chiffre d'affaire");

				final TextField nomCommune = new TextField("Commune");

				final TextField codePostal = new TextField("code Postal");
				final TextField rue = new TextField("La Rue");
				final TextField numero = new TextField("Numero");

				final TextField numFax = new TextField("Fax");
				final TextField numTel = new TextField("Tel");
				final TextField numGSM = new TextField("GSM");
				final TextField email = new TextField("Email");

				nomFour.addValidator(
						new StringLengthValidator("La raison Social doit contenir au mois 2 lettres", 2, 20, true));
				nomFour.setImmediate(true);
				nomFour.setRequired(true);
				nomFour.setRequiredError("Entrez votre La Raison Sociale SVP");
				nomFour.setNullRepresentation("");
				nomFour.setNullSettingAllowed(true);

				numTva.addValidator(
						new StringLengthValidator("Le Num de TVA SVP doit contenir au mois 15 lettres", 15, 15, true));
				numTva.setImmediate(true);
				numTva.setRequired(true);
				numTva.setRequiredError("Entrez votre La TVA SVP au mois 15 lettres");
				numTva.setNullRepresentation("");
				numTva.setNullSettingAllowed(true);

				article.addValidator(
						new StringLengthValidator("L'article doit contenir au mois 11 lettres", 11, 11, true));
				article.setImmediate(true);
				article.setRequired(true);
				article.setRequiredError("Entrez votre article SVP au mois 15 lettres");
				article.setNullRepresentation("");
				article.setNullSettingAllowed(true);

				numRgistre.addValidator(
						new StringLengthValidator("Le Num de Rgistre doit contenir au mois 2 lettres", 2, 20, true));
				numRgistre.setImmediate(true);
				numRgistre.setRequired(true);
				numRgistre.setRequiredError("Entrez votre Le Num de Rgistre SVP au mois 15 lettres");
				numRgistre.setNullRepresentation("");
				numRgistre.setNullSettingAllowed(true);

				chiffrAff.setImmediate(true);
				chiffrAff.setNullRepresentation("");
				chiffrAff.setNullSettingAllowed(true);

				nomCommune.addValidator(
						new StringLengthValidator("Le nom de La Commune doit contenir au mois 2 lettres", 2, 30, true));
				nomCommune.setImmediate(true);
				nomCommune.setRequired(true);
				nomCommune.setRequiredError("Entrez votre Commune SVP au mois 2 lettres");
				nomCommune.setNullRepresentation("");
				nomCommune.setNullSettingAllowed(true);

				email.addValidator(new EmailValidator("introduiser un email valid"));
				email.setImmediate(true);
				email.setRequiredError("Email non valide");
				email.setNullRepresentation("");
				email.setNullSettingAllowed(true);

				rue.setImmediate(true);
				rue.setRequired(true);
				rue.setRequiredError("Entrez votre Le Num de Rgistre SVP au mois 3 lettres");
				rue.setNullRepresentation("");
				rue.setNullSettingAllowed(true);
				numero.setImmediate(true);
				numero.setNullRepresentation("");
				numero.setNullSettingAllowed(true);
				numFax.setImmediate(true);
				numFax.setNullRepresentation("");
				numFax.setNullSettingAllowed(true);
				numTel.setImmediate(true);
				numTel.setNullRepresentation("");
				numTel.setNullSettingAllowed(true);
				numGSM.setImmediate(true);
				numGSM.setNullRepresentation("");
				numGSM.setNullSettingAllowed(true);

				h.addComponent(nomFour);
				h.addComponent(numRgistre);
				h.addComponent(numTva);
				subContent.addComponent(h);

				h0.addComponent(article);
				h0.addComponent(chiffrAff);
				subContent.addComponent(h0);

				h1.addComponent(rue);
				h1.addComponent(codePostal);
				h1.addComponent(numero);
				subContent.addComponent(h1);

				h2.addComponent(nomCommune);
				h2.addComponent(numFax);
				h2.addComponent(numTel);

				h3.addComponent(numGSM);
				h3.addComponent(email);
				subContent.addComponent(h2);
				subContent.addComponent(h3);

				subWindow.setModal(true);

				Button sauver = new Button("Enregistrer", new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {

						if (nomFour.getValue() == null || article.getValue() == null || numRgistre.getValue() == null
								|| numTva.getValue() == null) {
							Notification.show("Veuillez introduire les donnees obligatoires !");
							return;
						}
						if (rue.getValue() == null || numero.getValue() == null) {

							Notification.show("Veulliez introduire les données d'adresse !! ");

							return;
						}
						if (numFax.getValue() == null || numTel.getValue() == null || numGSM.getValue() == null
								|| nomCommune.getValue() == null || email.getValue() == null) {

							Notification.show("Veulliez introduire les données du Contact !! ");

							return;
						}
						try {
							nomFour.validate();
							numTva.validate();
							article.validate();
							numRgistre.validate();
							nomCommune.validate();
							email.validate();

						} catch (InvalidValueException e) {
							Notification.show(e.getMessage());
							nomFour.setValidationVisible(true);
							numRgistre.setValidationVisible(true);
							article.setValidationVisible(true);
							nomCommune.setValidationVisible(true);
							email.setValidationVisible(true);
						}

						MoyenContact mc = new MoyenContact();

						mc.setEmail(email.getValue());
						mc.setGsm(numGSM.getValue());
						mc.setTelephone(numTel.getValue());
						mc.setFax(numFax.getValue());

						Address add = new Address();

						add.setNomCommune(nomCommune.getValue());
						add.setCodePostal(codePostal.getValue());
						add.setNumero(numero.getValue());
						add.setRue(rue.getValue());

						Fournisseur mat = new Fournisseur();

						mat.setNom(nomFour.getValue());
						mat.setNumTva(numTva.getValue());
						mat.setArticle(article.getValue());
						mat.setNumeroRegistre(numRgistre.getValue());
						mat.setChiffre(chiffrAff.getValue());
						mat.setMoyenContact(mc);
						mat.setAddress(add);
						fournisseurDAO.save(mat);

						Notification.show("Le Fournisseur est bien sauvé ");
						buildFournisseur();
						subWindow.close();
						Layout tab = (Layout) right.getSelectedTab();
						tab.removeAllComponents();
						tab.addComponent(buildFournisseur());

					}
				});

				subContent.addComponent(sauver);

				subContent.setComponentAlignment(sauver, Alignment.BOTTOM_CENTER);
				// Open it in the UI
				addWindow(subWindow);

			}
		});

		layout.addComponents(ajoutProvider, grid);
		layout.setComponentAlignment(ajoutProvider, Alignment.BOTTOM_CENTER);
		layout.setExpandRatio(grid, 1);
		return layout;
	}

	HorizontalLayout buildBankOrder() {

		HorizontalLayout layout = new HorizontalLayout();
		layout.removeAllComponents();

		Panel banking = new Panel();
		banking.setSizeFull();
		banking.addStyleName(Runo.PANEL_LIGHT);
		layout.addComponent(banking);
		layout.setMargin(true);
		layout.setSpacing(true);

		Grid grid = new Grid();

		updateBoList(grid);

		// Use the name property as the item ID of the bean

		// TODO GRID CARNET DE CHEQUES
		// grid.setSizeFull();
		grid.setColumns("numero_cheque", "etatCheque", "montant");
		// grid.setFrozenColumnCount(3);
		grid.setEditorEnabled(true);
		// HeaderRow row = grid.addHeaderRowAt(0);
		// row.join("numero_cheque", "etatCheque");

		layout.addComponent(grid);
		layout.setExpandRatio(grid, 1);
		Button ajouterCarnetDeCheque = new Button("Ajouter Carnet De Cheque", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {

				// Create a sub-window and set the content
				final Window subWindow = new Window("Activer Le nouveau Carnet de cheque");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);

				// Put some components in it

				// Center it in the browser window
				subWindow.center();

				HorizontalLayout h1 = new HorizontalLayout();
				h1.setSpacing(true);
				h1.setMargin(true);
				HorizontalLayout h2 = new HorizontalLayout();
				h2.setSpacing(true);
				h2.setMargin(true);

				final TextField initNumSer = new TextField("Num de cheque");
				final TextField serie = new TextField("Serie");
				initNumSer.addValidator(new IntegerValidator("introduiser un mun SVP"));
				initNumSer.setImmediate(true);
				initNumSer.setRequired(true);
				initNumSer.setRequiredError("Entrez le num de depart du cheque SVP");
				initNumSer.setNullRepresentation("");
				initNumSer.setNullSettingAllowed(true);

				serie.setImmediate(true);
				serie.setNullRepresentation("");
				serie.setNullSettingAllowed(true);

				h1.addComponent(initNumSer);
				h1.addComponent(serie);

				subContent.addComponent(h1);

				subWindow.setModal(true);

				Button sauver = new Button("Sauver", new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {
						if (initNumSer.getValue() == null) {

							Notification.show("Veulliez introduire le premier num de serie  !! ");
							return;
						}
						try {
							initNumSer.validate();

						} catch (InvalidValueException e) {
							Notification.show(e.getMessage());
						}

						int numDepart = Integer.parseInt(initNumSer.getValue());
						bankOrderDAO.validateBankOrderBook(numDepart, serie.getValue());
						Notification.show("Les 50 cheques son valider");
						subWindow.close();
						buildBankOrder();

						Layout tab = (Layout) right.getSelectedTab();
						tab.removeAllComponents();
						updateBoList(grid);
						tab.addComponent(buildBankOrder());
					}
				});

				subContent.addComponent(sauver);
				subContent.setComponentAlignment(sauver, Alignment.BOTTOM_CENTER);
				// Open it in the UI
				addWindow(subWindow);
			}
		});

		VerticalLayout main = new VerticalLayout(ajouterCarnetDeCheque, grid);
		main.setSpacing(true);
		main.setSizeFull();
		main.setMargin(true);
		main.setComponentAlignment(ajouterCarnetDeCheque, Alignment.BOTTOM_CENTER);

		layout.addComponent(main);
		return layout;
	}
//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	VerticalLayout buildpayement() {
		VerticalLayout layout = new VerticalLayout();
		layout.removeAllComponents();
		layout.setMargin(true);
		layout.setSpacing(true);

		Panel payementPanel = new Panel();
		payementPanel.setSizeFull();
		payementPanel.addStyleName(Runo.PANEL_LIGHT);
		layout.addComponent(payementPanel);

		final ComboBox frournissCombo = new ComboBox();
		updateCbFourniList(frournissCombo);
//		Grid grid = new Grid();
//		updatePaymentList(grid);
		Grid grid1 = new Grid();
		grid1.setSizeFull();

		frournissCombo.setInputPrompt("Choisir un fournisseur");
		frournissCombo.setImmediate(true);

		HorizontalLayout menu = new HorizontalLayout();
		menu.setSizeFull();
		menu.setMargin(true);
		payer.setEnabled(false);
		// populating the grid by all non payed invoices if no provider selected
		frournissCombo.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (frournissCombo.getValue() == null) {
					Notification.show("Veuillez introduire un fournisseur a payer !");
					grid1.setContainerDataSource(
							new BeanItemContainer<Facture>(Facture.class, factureDAO.findAllImpayedFounisseur()));
				} else {
					grid1.setContainerDataSource(new BeanItemContainer<Facture>(Facture.class,
							factureDAO.findAllImpayedByFounisseur((Fournisseur) frournissCombo.getValue())));
				}
			}
		});

		// create and bind containe
		grid1.setLocale(Locale.FRANCE);
		grid1.setColumns("totFacture", "soldFacture", "dateEcheanceFacture", "dateFacture", "etatFacture");
		grid1.setSelectionMode(SelectionMode.MULTI);
		grid1.setSizeFull();

		HeaderCell header1 = grid1.appendHeaderRow().join("totFacture", "soldFacture", "dateEcheanceFacture",
				"dateFacture", "etatFacture");
		// add Listener to show what we have to pay
		grid1.addSelectionListener(selection -> { // Java 8

			// TODO this Part of code is used twice (to extract in one met  hod)
			Collection<Object> selectedRows = grid1.getSelectedRows();
			if (selectedRows.isEmpty()) {
				header1.setText("00.00 DA");
				return;
			}
			// TODO extract this method into buildpayement

			final List<Facture> ids = new ArrayList<>();

			for (Object value : selectedRows) {
				Facture id = (Facture) value;
				ids.add(id);
			}
			if(!ids.isEmpty()){
				payer.setEnabled(true);
			}
			// Display total of selected invoices in the header
			BigDecimal sumFactures = factureDAO.getSumSoldeFacturesList(getSelectedItemsIds(grid1));
			header1.setText("Total " + sumFactures.toString() + " DA");
		});
		// TODO retrieve full date format
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		// Perform Payment 2 use case
		// amount to pay is less or equal the total to pay
		payer.addClickListener(new Button.ClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void buttonClick(Button.ClickEvent event) {
				boolean uniqueFact;

				final Window subWindow = new Window("Payement Facture");
				// Create a sub-window and set the content
				VerticalLayout subContent = new VerticalLayout();
				subContent.setWidth("60%");
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);
				subWindow.center();
				subWindow.setModal(true);
				// Put some components in it

				// Center it in the browser window
				subWindow.center();

				HorizontalLayout h = new HorizontalLayout();
				h.setSpacing(true);
				h.setMargin(true);
				HorizontalLayout h1 = new HorizontalLayout();
				h1.setSpacing(true);
				h1.setMargin(true);
				HorizontalLayout h2 = new HorizontalLayout();
				h2.setSpacing(true);
				h2.setMargin(true);
				HorizontalLayout h3 = new HorizontalLayout();
				h3.setSpacing(true);
				h3.setMargin(true);

				final TextField toPaye = new TextField("montant payement");
				final ComboBox check = new ComboBox("cheques");
				final ComboBox lastToPay = new ComboBox("La facture en dernier");
				final BigDecimal sumFactures = factureDAO.getSumSoldeFacturesList(getSelectedItemsIds(grid1));
				final DateField date_echeance = new DateField("Nouvelle date d'echeance");
				// toPaye.addValidator(new BigDecimalRangeValidator("invalid
				// prix",null, null));
				// toPaye.addValidator(new AbstractValidator<BigDecimal>("Price
				// invalid") {
				//
				// @Override
				// protected boolean isValidValue(BigDecimal value) {
				// // TODO Auto-generated method stub
				// boolean valable = false ;
				// DecimalFormat df = new DecimalFormat("#,##0.00");
				// df.setParseBigDecimal(true);
				// try {
				//
				// BigDecimal bd
				// =(BigDecimal)df.parse(toPaye.getValue().trim());
				// System.out.println("BigDecimal bd "+ bd+" classe de
				// "+bd.getClass());
				// System.out.println("toPaye.getValue().trim() "+
				// df.parse(toPaye.getValue().trim())+" classe de
				// "+df.parse(toPaye.getValue().trim()).getClass());
				//
				// if(toPaye.getValue().trim()!=null &&
				// bd.getClass()==BigDecimal.class ){
				// valable= true;
				// }
				// } catch (ParseException e) {
				// // TODO Auto-generated catch block
				// Notification.show("EERRValidator");
				// }
				// return valable;
				//
				// }
				//
				// @Override
				// public Class<BigDecimal> getType() {
				// // TODO Auto-generated method stub
				// return BigDecimal.class;
				// }
				//
				// });
				toPaye.setImmediate(true);
				toPaye.focus();
				toPaye.setRequired(true);
				toPaye.setRequiredError("Entrez votre Montant");
				toPaye.setNullRepresentation("");
				toPaye.setValidationVisible(false);
				toPaye.setNullSettingAllowed(false);
				toPaye.setInputPrompt("Entrer votre Montant");
				// Select Last Item to be payed with a sold others items will be
				// closed with this payment
				// payment of the selected item can be finished with other
				// payment when amount to pay is less then total to pay
				lastToPay.setImmediate(true);
				lastToPay.setEnabled(false);
				lastToPay.setInputPrompt("selectioner un cheque");
				date_echeance.setImmediate(true);
				date_echeance.setRequiredError("Entrez votre Date");
				date_echeance.setValidationVisible(false);
				date_echeance.setEnabled(false);
				check.setImmediate(true);
				check.setInputPrompt("selectioner un cheque");

				// retrieve Ids List of the selected items
				Collection<Object> selectedRows = grid1.getSelectedRows();
				if (selectedRows.isEmpty() ) {
					// notification Empty
					Notification.show("Veuillez choisire des factures !");
					return;
				}

				h.addComponent(new Label("Tot à Payer = " + sumFactures.toString() + " DA", ContentMode.HTML));
				h3.addComponents(toPaye, check, date_echeance);

				lastToPay.setCaption("Derniere à payer");
			h1.addComponent(lastToPay);

				for (Object value : selectedRows) {
					Facture id = (Facture) value;
					lastToPay.addItem(value);
					lastToPay.setItemCaption(value,
							id.getSoldFacture().toString() + " : paiement partiel echeance initiale )-"
									+ shortDateFormat.format(id.getDateEcheanceFacture()).toString());
				}
				check.setCaption("Choisir un cheque");
				for (BankOrder prov : bankOrderDAO.findBankOrderDisponible()) {
					check.addItem(prov);
					check.setItemCaption(prov, prov.getNumero_cheque().toString());
				}
				// More then one invoice
				if (selectedRows.size() > 1) {
					// uniqueFact=false;
					// lastToPay.setEnabled(true);
					// date_echeance.setEnabled(true);
				}

				toPaye.addListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						TextField toPaye = (TextField) event.getProperty();
						BigDecimal topayV = new BigDecimal(toPaye.getValue().trim());
						topayV.setScale(2);
						// System.out.println("la valeur ,,,"+topayV+" éé "
						// +topayV.getClass());

						if ((toPaye.getValue().trim().isEmpty()) || topayV.getClass() != BigDecimal.class) {

							Notification.show("format incorrect");
							return;
						} else if (topayV.compareTo(sumFactures) == 0) {

						} else if (topayV.compareTo(sumFactures) == 1) {

							Notification.show("Veuillez introduire un montant egal ou inferieur à votre paiement !");
							return;
						} else if (topayV.compareTo(sumFactures) == -1 && selectedRows.size() >= 1) {
							if (selectedRows.size() > 1) {
								lastToPay.setEnabled(true);
								date_echeance.setEnabled(true);
							} else if (selectedRows.size() == 1) {
								date_echeance.setEnabled(true);
							}

						}
					}

				});

				Button payerFacture = new Button("Payer Factures", new Button.ClickListener() {
					/**
					 * first case : payment of all selected invoices second case
					 * : payment of part
					 * 
					 */

					@Override
					public void buttonClick(ClickEvent event) {
						final List<Facture> idffs = new ArrayList<>();

						for (Object value : selectedRows) {
							Facture id = (Facture) value;
							// Chercher l'id
							idffs.add(id);
						}
						String toPayeT = toPaye.getValue().trim();
						BigDecimal toPayValue = new BigDecimal(toPayeT);
						toPayValue.setScale(2);

						if (toPayeT == null) {
							Notification.show("Veuillez introduire le montant de votre paiement !");
							return;
						}
						 if (toPayeT != null && !(toPayValue.getClass().equals(BigDecimal.class))) {
							
							Notification.show("Veulliez introduire un montant valide!! ");

							return;
						}

						try {
							toPaye.validate();
							toPaye.setValidationVisible(false);

						} catch (InvalidValueException e) {
							Notification.show(e.getMessage());
							toPaye.setValidationVisible(true);
						}
						// In case we pay all selected invoices SoldFacture is
						// set to Zero
						// and EtatFacture To payed with one BankOrder
						
						BankOrder bankOrd = (BankOrder) check.getValue();
						
						if ((toPayValue.compareTo(sumFactures)) == 0) {
							bankOrderDAO.bankOrderToPay(bankOrd, sumFactures, idffs);
							Notification.show(" votre paiement est Enregistrer");
						}
						else if ((toPayValue.compareTo(sumFactures)) == -1 && selectedRows.size() > 1) {
							// case part payment with : more then one
							// invoice
//							if(selectedRows.size() > 1){
								
								bankOrderDAO.bankOrderPartToPay(idffs, bankOrd, toPayValue, (Facture) lastToPay.getValue(), sumFactures, date_echeance.getValue());
								Notification.show(" votre paiement est Enregistrer, " + "un solde restant de : "
										+ sumFactures.subtract(toPayValue) + " sur la derneire facture ");
								
							}
						else if (toPayValue.compareTo(sumFactures) == -1 && selectedRows.size() == 1) {
								bankOrderDAO.bankOrderSinglPartToPay(bankOrd, toPayValue, idffs.get(0), date_echeance.getValue());
							Notification.show(" votre paiement est Enregistrer, " + "un solde restant de : "
									+ sumFactures.subtract(toPayValue) + " sur la derneire facture ");

							
						}

						subWindow.close();
						buildpayement();
						Layout tab = (Layout) right.getSelectedTab();
						tab.removeAllComponents();
						tab.addComponent(buildpayement());

					}
				});

				subContent.addComponent(h);
				subContent.addComponent(h3);
				subContent.addComponent(h1);
				subContent.addComponent(h2);
				subContent.addComponent(payerFacture);
				subContent.setComponentAlignment(payerFacture, Alignment.BOTTOM_CENTER);
				addWindow(subWindow);

			}
		});
		menu.addComponents(frournissCombo, payer);
		menu.setComponentAlignment(payer, Alignment.BOTTOM_LEFT);
		menu.setComponentAlignment(frournissCombo, Alignment.BOTTOM_RIGHT);

		VerticalLayout main = new VerticalLayout(menu, grid1);
		main.setSpacing(true);
		main.setSizeFull();
		main.setMargin(true);

		layout.addComponent(main);
		layout.setExpandRatio(main, 1);
		return layout;
	}

	protected BigDecimal getPayementPercentage(Fournisseur fourni) {
		BigDecimal a, b;
		List<Long> idsList = factureDAO.findAllFacIdByFounisseur(fourni);
		a = factureDAO.getSumFacturesList(idsList);
		b = factureDAO.getSumSoldeFacturesList(idsList);
		if (a == null || a == BigDecimal.ZERO||b== BigDecimal.ZERO) {
			return (BigDecimal.ZERO);
		}
		return (b.divide(a, 2, BigDecimal.ROUND_HALF_DOWN));

	}

	/**
	 * @param grid
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	public void updateFactureList(Grid grid) throws IllegalArgumentException, IllegalStateException {
		List<Facture> factures = factureDAO.findAll();
		BeanItemContainer<Facture> container = new BeanItemContainer<Facture>(Facture.class, factures);
		grid.setContainerDataSource(container);
		container.addNestedContainerBean("fournisseur");
		grid.setColumns("fournisseur.nom", "etatFacture", "totFacture", "soldFacture", "dateEcheanceFacture",
				"dateFacture");
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setFrozenColumnCount(2);
		grid.setWidthUndefined();
		grid.setSizeUndefined();
		grid.setEditorEnabled(true);
		setFilterGrid(container, grid);
		grid.clearSortOrder();
	}

	private void updateFacturePayedList(Grid grid) {
		List<Facture> factures = bankOrderDAO.findAllPayedFacByO();
		BeanItemContainer<Facture> container = new BeanItemContainer<Facture>(Facture.class, factures);
		grid.setContainerDataSource(container);		
		grid.setDetailsGenerator(new DetailsGenerator(){

			@Override
			public com.vaadin.ui.Component getDetails(RowReference rowReference) {
				HorizontalLayout layout = new HorizontalLayout();
				Facture fac1 =  (Facture) rowReference.getItemId();
				updateBoListOfFact(fac1, layout);
				layout.setSpacing(true);
				layout.setMargin(true);
				layout.setWidthUndefined();
				return layout;
			}
			
		});
		container.addNestedContainerBean("fournisseur");
		grid.setColumns("fournisseur.nom","totFacture", "soldFacture", "dateFacture","dateEcheanceFacture");
		grid.addItemClickListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				if(event.isDoubleClick()){
					Object itemId = event.getItemId();
					grid.setDetailsVisible(itemId, !grid.isDetailsVisible(itemId));
				}
			}
		});
		grid.setSizeUndefined();
		grid.setEditorEnabled(false);
	}
    public	void updateBoListOfFact(Facture fac, HorizontalLayout layout){
		Grid gr = new Grid();
		List<BankOrder> bOs = bankOrderDAO.findBosByPayedFac(fac);
		BeanItemContainer<BankOrder> container = new BeanItemContainer<BankOrder>(BankOrder.class, bOs);
		gr.setContainerDataSource(container);
		gr.setColumns("montant", "montant", "numero_cheque","datePayement");
		gr.setHeightByRows(3);
		gr.setHeightMode(HeightMode.ROW);
		
		gr.setEditorEnabled(false);
		gr.setSelectionMode(SelectionMode.NONE);
		gr.clearSortOrder();
		layout.addComponent(gr);
		
	}

	public List<Long> getSelectedItemsIds(Grid grid) {
		List<Long> idSF = null;
		MultiSelectionModel selection = (MultiSelectionModel) grid.getSelectionModel();

		Collection<Object> selectedRows = selection.getSelectedRows();
		if (selectedRows.isEmpty() != true) {

			idSF = new ArrayList<>();
			for (Object value : selectedRows) {
				Facture id = (Facture) value;
				// Chercher l'id
				idSF.add(id.getId());
			}
		}
		return idSF;
	}

	/**
	 * @param grid
	 * @throws IllegalArgumentException
	 */
	public void updateBoList(Grid grid) throws IllegalArgumentException {
		List<BankOrder> bOs = bankOrderDAO.findAll();
		BeanItemContainer<BankOrder> container = new BeanItemContainer<BankOrder>(BankOrder.class, bOs);
		grid.setContainerDataSource(container);
		grid.setSizeUndefined();
		setFilterGrid(container, grid);
		grid.clearSortOrder();
	}

	public void setFilterGrid(BeanItemContainer<?> beanType, Grid grid) {
		// This create a HeaderRow to add filter fields
		HeaderRow filterRow = grid.appendHeaderRow();
		for (Column column : grid.getColumns()) {
			// For each column from the grid
			HeaderCell cellFilter = filterRow.getCell(column.getPropertyId());
			// Add a textfield
			cellFilter.setComponent(createFieldFilter(beanType, column));
		}
	}

	// This create a TextField to filter the information
	private TextField createFieldFilter(final BeanItemContainer<?> container, final Column column) {
		TextField filter = new TextField();
		filter.setImmediate(true);
		filter.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				String newValue = event.getText();
				// Remove the previous filter
				container.removeContainerFilters(column.getPropertyId());
				if (newValue != null && !newValue.isEmpty()) {
					// Filter the information
					container.addContainerFilter(new SimpleStringFilter(column.getPropertyId(), newValue, true, false));
				}
			}
		});

		return filter;
	}

	/**
	 * @param frournissCombo
	 * @throws UnsupportedOperationException
	 */
	public void updateCbFourniList(final ComboBox frournissCombo){
		 List<Fournisseur> FourniList= fournisseurDAO.findAll();
		 for (Fournisseur pro : FourniList) {
		 frournissCombo.addItem(pro);
		 frournissCombo.setItemCaption(pro, pro.getNom());
		 }
	}

	/**
	 * A variant of {@link #getCurrentService(Class)} which works on this
	 * instance, instead of the current instance
	 *
	 * @param serviceType
	 *            Service typef
	 * @return a service
	 */
	public <SERVICE> SERVICE getService(Class<? extends SERVICE> serviceType) {
		Object object = services.get(serviceType.getName());
		return cast(object, serviceType);
	}

	public String getUserDisplayName() {
		VaadinRequest request = VaadinService.getCurrentRequest();
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			return "user";
		}
		return "unknown hero";
	}

	public String getUserLogon() {
		VaadinRequest request = VaadinService.getCurrentRequest();
		if (request instanceof HttpServletRequest) {
			Principal principal = request.getUserPrincipal();
			if (principal != null) {
				return principal.getName();
			}
		}
		return "unknown hero";
	}

	private void setMainComponent(AbstractPage page) {

		pageContainer.getPagePanel().removeAllComponents();
		pageContainer.getPagePanel().addComponent(page);
	}

	private static final class MenuValueChangeListener implements Property.ValueChangeListener {

		private static final long serialVersionUID = 1L;
		private final Class<? extends View> linkTarget;

		private MenuValueChangeListener(Class<? extends View> linkTarget) {
			this.linkTarget = linkTarget;
		}

		public void valueChange(ValueChangeEvent event) {
		}

	}

	/**
	 * (Re-)define a service. See {@link #getCurrentService(Class)}
	 *
	 * @param serviceType
	 *            Service class
	 * @param service
	 *            service, can't be null
	 * @return The previous service
	 */
	public <SERVICE> Object setService(Class<? extends SERVICE> serviceType, SERVICE service) {
		return services.put(serviceType.getName(), service);
	}

	@Override
	public void showView(View view) {
		if (!(view instanceof AbstractPage)) {
			throw new IllegalArgumentException("Violation of view standard:" + view);
		}
		setMainComponent((AbstractPage) view);
	}

	@Override
	protected void init(VaadinRequest request) {
		doInit();
	}

	/**
	 * get a service from the current UI.
	 *
	 * Basically, services are objects of user-specified type, which are
	 * attached to the current UI. This is close to {@link #setData}, except
	 * multiple services can be attached, distinguished by by their class name,
	 * and they are typed.
	 *
	 * It is an error to request a service which is not yet defined by
	 * {@link #setService}, or to call services while no active UI is defined.
	 *
	 * @param serviceType
	 *            Any class
	 * @return An object of this class, previously installed by
	 *         {@link #setService}
	 */
	public static <SERVICE> SERVICE getCurrentService(Class<? extends SERVICE> serviceType) {
		MainUI current = getInstance();
		if (current == null) {
			throw new IllegalStateException("Not in a VaadinUI");
		}
		Object object = current.services.get(serviceType.getName());
		if (object == null) {
			throw new IllegalStateException("Service not defined:" + serviceType);
		}
		return cast(object, serviceType);
	}

	public static MainUI getInstance() {
		return (MainUI) UI.getCurrent();
	}
	  @SuppressWarnings("unchecked")
    static <TYPE> TYPE cast(Object object, Class<TYPE> type) {
        if (object == null) {
            return null;
        }
        if (!type.isAssignableFrom(object.getClass())) {
            throw new IllegalStateException("not of type " + type + ":" + object);
        }
        return (TYPE) object;
    }

}
