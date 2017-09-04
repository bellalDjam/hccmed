package be.gest.web;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import be.gest.dao.AdresseDAO;
import be.gest.dao.BankOrderDAO;
import be.gest.dao.DepartementDAO;
import be.gest.dao.FactureDAO;
import be.gest.dao.FournisseurDAO;
import be.gest.dao.MaterielDAO;
import be.gest.dao.MaterielLocationDAO;
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
import be.gest.model.enumeration.EtatCheque;
import be.gest.model.enumeration.EtatFacture;
import be.gest.model.enumeration.EtatMateriel;
import be.gest.model.enumeration.TypeMateriel;
import be.gest.web.page.AbstractPage;
import be.gest.web.page.DepartementPage;
import be.gest.web.page.FournisseurForm;
import be.gest.web.page.HomePage;
import be.gest.web.util.PageContainer;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.themes.Runo;

public class MyUI extends UI {
//	private static final long serialVersionUID = 1L;
//	@Autowired
//	private BankOrderDAO bankOrderDAO;
//	@Autowired
//	private FournisseurDAO fournisseurDAO;
//	@Autowired
//	private FactureDAO factureDAO;
//	@Autowired
//	private AdresseDAO adresseDAO;
//
//	final String basepath = VaadinService.getCurrent().getBaseDirectory()
//			.getAbsolutePath();
//
//	final ComboBox cbMateriel = new ComboBox();
//	final ComboBox cbDepartement = new ComboBox();
//	final SpringContextHelper helper = new SpringContextHelper(VaadinServlet
//			.getCurrent().getServletContext());
//	final MaterielDAO materielDAO = (MaterielDAO) helper.getBean("materielDAO");
//	final DepartementDAO departementDAO = (DepartementDAO) helper
//			.getBean("departementDAO");
//	final PersonneDAO personneDAO = (PersonneDAO) helper.getBean("personneDAO");
//	final MaterielLocationDAO materielLocationDAO = (MaterielLocationDAO) helper
//			.getBean("materielLocationDAO");
//
//	Table tableMatriel;
//	Table tableDepartements;
//	Table tableMatrielLocation;
//	Table tablePersonnes;
//	Table tablepayement;
//	Table tableFacture;
//	Table tableFournisseur;
//
//	private Grid grid = new Grid();
//	FournisseurForm formFourni = new FournisseurForm(this);
//	private TextField serchTexF = new TextField("Filtrer");
//
//	private List<Fournisseur> fournisseurs = fournisseurDAO.findAll();
//	private Long matSelectionne;
//	private Long persSelectionne;
//	private Long tarifSelectionne;
//
//	private final Map<String, Object> services = new HashMap<String, Object>();
//
//	private PageContainer pageContainer;
//
//	private TabSheet right = new TabSheet();
//
//	public MainUI() {
//		setService(HistoryService.class, new HistoryService(this, this));
//	}
//
//	/**
//	 * This public alias for #init only exists for unit tests.
//	 */
//	public void doInit() {
//		pageContainer = new PageContainer();
//
//		VerticalLayout root = new VerticalLayout();
//		root.setMargin(true);
//		root.setSizeFull();
//
//		Panel mainPanel = new Panel(); // for scrollbars.
//		mainPanel.setContent(root);
//		mainPanel.setHeight("1000px");
//
//		Label title = new Label("GestionMed");
//		title.addStyleName(Runo.LABEL_H1);
//		title.setSizeUndefined();
//		root.addComponent(title);
//		root.setComponentAlignment(title, Alignment.TOP_CENTER);
//
//		Label slogan = new Label("Gestion HCCMEDICALE");
//		slogan.addStyleName(Runo.LABEL_SMALL);
//		slogan.setSizeUndefined();
//		root.addComponent(slogan);
//		root.setComponentAlignment(slogan, Alignment.TOP_CENTER);
//
//		Label spacer = new Label("");
//		spacer.setHeight("20px");
//		root.addComponent(spacer);
//
//		final HorizontalSplitPanel split = new HorizontalSplitPanel();
//		split.setStyleName(Runo.SPLITPANEL_REDUCED);
//		split.setSplitPosition(1, Unit.PIXELS);
//		split.setLocked(true);
//		root.addComponent(split);
//		root.setExpandRatio(split, 1);
//
//		Panel left = new Panel("Servers Monitoring");
//		left.setSizeFull();
//		split.setFirstComponent(left);
//
//		right.setSizeFull();
//		split.setSecondComponent(right);
//		split.setLocked(true);
//
//		right.addTab(buildLocationMaterielJournaliste(),
//				"Matériels Journalistes Disponibles", null);
//		right.addTab(buildMateriel(), "Matériel", null);
//		right.addTab(builTransfertScreen(), "Transfért", null);
//		// right.addTab(buildDepartement(), "Département", null);
//		right.addTab(buildBankOrder(), " Cheques", null);
//		right.addTab(buildFactureProv(), "Factures Fournisseur", null);
////		right.addTab(buildFactur(), "Factures ", null);
//		right.addTab(buildFournisseur(), "Fournisseur ", null);
//		
//		formFourni.setVisible(false);
//
//		setContent(mainPanel);
//
//		// Create tab content dynamically when tab is selected
//		right.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
//			public void selectedTabChange(SelectedTabChangeEvent event) {
//				// Find the tabsheet
//				TabSheet tabsheet = event.getTabSheet();
//
//				// Find the tab (here we know it's a layout)
//				Layout tab = (Layout) tabsheet.getSelectedTab();
//
//				// Get the tab caption from the tab object
//				String caption = tabsheet.getTab(tab).getCaption();
//
//				if ("Matériel".equals(caption)) {
//					tab.removeAllComponents();
//					tab.addComponent(buildMateriel());
//				} else if ("Matériels Journalistes Disponibles".equals(caption)) {
//
//					tab.removeAllComponents();
//					tab.addComponent(buildLocationMaterielJournaliste());
//				} else if ("Transfért".equals(caption)) {
//
//					tab.removeAllComponents();
//					tab.addComponent(builTransfertScreen());
//				} else if ("Département".equals(caption)) {
//
//					tab.removeAllComponents();
//					tab.addComponent(buildDepartement());
//				}
//
//			}
//		});
//
//	}
//
//	HorizontalLayout buildFactur() {
//		HorizontalLayout layout = new HorizontalLayout();
//		layout.removeAllComponents();
//
//		Panel Factures = new Panel("Les Factures");
//		Factures.setSizeFull();
//		Factures.addStyleName(Runo.PANEL_LIGHT);
//		layout.addComponent(Factures);
//		
//		grid.setColumns("fournisseur", "totFacture", "dateEcheanceFacture",
//				"etatFacture", "dateFacture");
//		grid.setFrozenColumnCount(3);
//		grid.setEditorEnabled(true);
//		HeaderRow row = grid.addHeaderRowAt(0);
//		row.join("fournisseur", "totFacture");
//		
//		Button addCustomerBtn = new Button("Add new customer");
//		addCustomerBtn.addClickListener(e -> {
//			grid.select(null);
//			formFourni.setFournisseur(new Fournisseur());
//		});
//
//		
//		HorizontalLayout main=new HorizontalLayout(grid,formFourni); 
//		main.setSpacing(true);
//		main.setSizeFull();
//		main.setExpandRatio(grid, 1);
//		updateListeFac();
//		layout.addComponent(main);
//
//		return layout;
//	}
//
//	public void updateListeFac() {
//		
//		List<Facture> factures = factureDAO.findAll();
//		BeanItemContainer<Facture> container = new BeanItemContainer<Facture>(
//				Facture.class, factures);
//
//		GeneratedPropertyContainer gContainer = new GeneratedPropertyContainer(
//				container);
//
//		gContainer.addGeneratedProperty("fournisseur",
//				new PropertyValueGenerator<String>() {
//
//					/**
//			 *  
//			 */
//					private static final long serialVersionUID = 1L;
//
//					@Override
//					public String getValue(Item item, Object itemId,
//							Object propertyId) {
//
//						Fournisseur prov = (Fournisseur) (item
//								.getItemProperty("fournisseur").getValue());
//						return prov.getNom();
//					}
//
//					@Override
//					public Class<String> getType() {
//						return String.class;
//					}
//				});
//
//		grid = new Grid(gContainer);
//
//		// grid.setContainerDataSource(new BeanItemContainer<>(Facture.class,
//		// factures));
//		
//		
//		// FilterRow.add(grid);
//
//		// grid.setEditorErrorHandler(new EditorErrorHandler() {
//		//
//		// @Override
//		// public void commitError(CommitErrorEvent event) {
//		// com.vaadin.ui.Notification.show(event.getCause()
//		// .getInvalidFields().values().iterator().next()
//		// .getMessage()
//		// ,Notification.Type.ERROR_MESSAGE);
//		// event.setUserErrorMessage(userErrorMessage);
//		//
//		// }
//		// });
//		// grid.getColumn("fournisseur").getPropertyId().
//
//	}
//	
//	HorizontalLayout buildFournisseur() {
//		HorizontalLayout layout = new HorizontalLayout();
//		layout.removeAllComponents();
//
//		Panel Fournisseurs = new Panel("Les Fournisseurs");
//		Fournisseurs.setSizeFull();
//		Fournisseurs.addStyleName(Runo.PANEL_LIGHT);
//		layout.addComponent(Fournisseurs);
//		layout.setMargin(true);
//		layout.setSpacing(true);
//		BeanItemContainer<Fournisseur> container = new BeanItemContainer<Fournisseur>(Fournisseur.class,fournisseurs);
//		Grid grid = new Grid(container);
//		grid.setSizeFull();
//		layout.addComponent(grid);
//		layout.setExpandRatio(grid, 1);
//		
//		return layout;
//	}
//
//	public void updateListeFour() {
//		
//		List<Fournisseur> fournisseurs = fournisseurDAO.findAll();
//		 grid.setContainerDataSource(new BeanItemContainer<>(Fournisseur.class,
//		 fournisseurs));
////		BeanItemContainer<Fournisseur> container = new BeanItemContainer<Fournisseur>(
////				Fournisseur.class, fournisseurs);
//		
////		GeneratedPropertyContainer gContainer = new GeneratedPropertyContainer(
////				container);
////		gContainer.addGeneratedProperty("address",
////				new PropertyValueGenerator<String>() {
////					private static final long serialVersionUID = 1L;
////					@Override
////					public String getValue(Item item, Object itemId,
////							Object propertyId) {
////						Adresse prov = (Adresse) (item
////								.getItemProperty("address").getValue());
////						return prov.toString();
////					}
////					@Override
////					public Class<String> getType() {
////						return String.class;
////					}
////				});
////
////		grid = new Grid(container);
//
////		 grid.setContainerDataSource(new BeanItemContainer<>(Fournisseur.class,
////				 fournisseurs));
//		
//		
////		 FilterRow.add(grid);
//
//		// grid.setEditorErrorHandler(new EditorErrorHandler() {
//		//
//		// @Override
//		// public void commitError(CommitErrorEvent event) {
//		// com.vaadin.ui.Notification.show(event.getCause()
//		// .getInvalidFields().values().iterator().next()
//		// .getMessage()
//		// ,Notification.Type.ERROR_MESSAGE);
//		// event.setUserErrorMessage(userErrorMessage);
//		//
//		// }
//		// });
//		// grid.getColumn("fournisseur").getPropertyId().
//
//	}
//
//	@Override
//	public Navigator getNavigator() {
//		return getService(HistoryService.class).getNavigator();
//	}
//
//	VerticalLayout builTransfertScreen() {
//		VerticalLayout l = new VerticalLayout();
//		l.setWidth("60%");
//		l.addComponent(buildSelects());
//
//		Button tranfert = new Button("Transférer", new Button.ClickListener() {
//			public void buttonClick(ClickEvent event) {
//
//				Materiel mat = materielDAO.getById(Materiel.class,
//						((Materiel) cbMateriel.getValue()).getId());
//
//				Departement dep = departementDAO.getById(Departement.class,
//						((Departement) cbDepartement.getValue()).getId());
//
//				mat.setDepartement(dep);
//
//				materielDAO.update(mat);
//
//				Notification.show("Le Matériel :  " + mat.getDescription()
//						+ " a été transféré vers le département :  "
//						+ dep.getNom());
//
//			}
//		});
//
//		tranfert.addStyleName(Runo.BUTTON_DEFAULT);
//		tranfert.addStyleName(Runo.BUTTON_BIG);
//		l.addComponent(tranfert);
//
//		l.setComponentAlignment(tranfert, Alignment.MIDDLE_CENTER);
//		return l;
//	}
//
//	Layout buildSelects() {
//
//		HorizontalLayout hl = new HorizontalLayout();
//		hl.setSpacing(true);
//		hl.setMargin(true);
//
//		cbMateriel.setCaption("Matérieles");
//
//		for (Materiel materiel : materielDAO.getAll(Materiel.class)) {
//			cbMateriel.addItem(materiel);
//			cbMateriel.setItemCaption(materiel, materiel.getDescription());
//		}
//
//		hl.addComponent(cbMateriel);
//
//		cbDepartement.setCaption("Départements");
//		for (Departement departement : departementDAO.getAll(Departement.class)) {
//			cbDepartement.addItem(departement);
//			cbDepartement.setItemCaption(departement, departement.getNom());
//		}
//
//		hl.addComponent(cbDepartement);
//
//		return hl;
//	}
//
//	HorizontalLayout buildBankOrder() {
//
//		HorizontalLayout layout = new HorizontalLayout();
//		layout.removeAllComponents();
//		Panel banking = new Panel("Livret de Cheques");
//		banking.setSizeFull();
//		banking.addStyleName(Runo.PANEL_LIGHT);
//		layout.addComponent(banking);
//
//		BeanContainer<String, BankOrder> beans = new BeanContainer<String, BankOrder>(
//				BankOrder.class);
//
//		// Use the name property as the item ID of the bean
//		beans.setBeanIdProperty("id");
//
//		// Add some beans to it
//		for (BankOrder bank_Order : bankOrderDAO.getAll(BankOrder.class)) {
//			beans.addBean(bank_Order);
//		}
//		// Bind a table to it
//		tablepayement = new Table(null, beans);
//		layout.addComponent(tablepayement);
//		// Some resizing
//		tablepayement.setPageLength(beans.size());
//		tablepayement.setVisibleColumns(new Object[] { "numero_cheque",
//				"numero_depart", "etatCheque" });
//
//		tablepayement.setColumnHeader("etatCheque", "etatCheque");
//		tablepayement.setColumnHeader("numero_cheque", "Numero du cheque");
//		tablepayement.setColumnHeader("numero_depart", "Num De Carnet");
//		
//		Button ajouterCarnetDeCheque = new Button("Ajouter Carnet De Cheque",
//				new Button.ClickListener() {
//					public void buttonClick(ClickEvent event) {
//
//						// Create a sub-window and set the content
//						final Window subWindow = new Window(
//								"Activer Le nouveau Carnet de cheque");
//						VerticalLayout subContent = new VerticalLayout();
//						subContent.setMargin(true);
//						subContent.setSpacing(true);
//						subWindow.setContent(subContent);
//
//						// Put some components in it
//
//						// Center it in the browser window
//						subWindow.center();
//
//						HorizontalLayout h1 = new HorizontalLayout();
//						h1.setSpacing(true);
//						h1.setMargin(true);
//						HorizontalLayout h2 = new HorizontalLayout();
//						h2.setSpacing(true);
//						h2.setMargin(true);
//
//						final TextField initNumSer = new TextField(
//								"Num de cheque");
//						final TextField serie = new TextField("Serie");
//						h1.addComponent(initNumSer);
//						h1.addComponent(serie);
//
//						subContent.addComponent(h1);
//
//						subWindow.setModal(true);
//
//						Button sauver = new Button("Sauver",
//								new Button.ClickListener() {
//									public void buttonClick(ClickEvent event) {
//										if (initNumSer.getValue() == null) {
//
//											Notification
//													.show("Veulliez introduire le premier num de serie  !! ");
//											return;
//										}
//
//										BankOrder mat = new BankOrder();
//										int numDepart = Integer
//												.parseInt(initNumSer.getValue());
//										for (int i = 0; i < 50; i++) {
//											mat.setDate_validation(new Date());
//											mat.setNumero_depart(numDepart);
//											mat.setNumero_cheque(numDepart + i);
//											mat.setSerie_cheque(serie
//													.getValue());
//											mat.setEtatCheque(EtatCheque.NOUVEAU);
//											bankOrderDAO.save(mat);
//										}
//										Notification
//												.show("Les 50 cheques son valider");
//										subWindow.close();
//										buildBankOrder();
//										Layout tab = (Layout) right
//												.getSelectedTab();
//										tab.removeAllComponents();
//
//										tab.addComponent(buildBankOrder());
//									}
//								});
//
//						subContent.addComponent(sauver);
//						subContent.setComponentAlignment(sauver,
//								Alignment.BOTTOM_CENTER);
//						// Open it in the UI
//						addWindow(subWindow);
//					}
//				});
//
//		layout.addComponent(ajouterCarnetDeCheque);
//		layout.setSpacing(true);
//		layout.setMargin(true);
//		layout.setComponentAlignment(ajouterCarnetDeCheque,
//				Alignment.BOTTOM_CENTER);
//
//		return layout;
//
//	}
//
//	HorizontalLayout buildFactureProv() {
//
//		HorizontalLayout layout = new HorizontalLayout();
//		Panel provFact = new Panel("Facture et Fournisseur");
//		provFact.setSizeFull();
//		provFact.addStyleName(Runo.PANEL_LIGHT);
//		layout.addComponent(provFact);
//
//		BeanContainer<String, Facture> beans = new BeanContainer<String, Facture>(
//				Facture.class);
//
//		// Use the name property as the item ID of the bean
//		beans.setBeanIdProperty("id");
//
//		// Add some beans to it
//		for (Facture facture : factureDAO.findAll()) {
//			beans.addBean(facture);
//		}
//
//		// Bind a table to it
//		tableFacture = new Table(null, beans);
//		tableFacture.setSelectable(true);
//		layout.addComponent(tableFacture);
//		// END-EXAMPLE: datamodel.container.beancontainer.basic
//
//		tableFacture.setPageLength(beans.size());
//
//		tableFacture.setVisibleColumns(new Object[] { "fournisseur",
//				"numFacture", "codFacture", "totFacture", "dateFacture",
//				"etatFacture",  "dateEcheanceFacture" });
//
//		tableFacture.setColumnHeader("fournisseur", "fournisseur");
//		tableFacture.setColumnHeader("numFacture", "num Facture");
//		tableFacture.setColumnHeader("codFacture", "code Facture");
//		tableFacture.setColumnHeader("totFacture", "montant Facture");
//		tableFacture.setColumnHeader("dateFacture", "date Facture");
//		tableFacture.setColumnHeader("etatFacture", "etat facture");
//		tableFacture.setColumnHeader("dateEcheanceFacture",
//				"date d'Echeance Facture");
//
//		tableFacture.addGeneratedColumn("fournisseur", new ColumnGenerator() {
//			private static final long serialVersionUID = -8133822448459398254L;
//
//			@Override
//			public com.vaadin.ui.Component generateCell(Table source,
//					final Object itemId, Object columnId) {
//				// Get the value in the first column
//				Fournisseur prov = (Fournisseur) source.getContainerProperty(
//						itemId, "fournisseur").getValue();
//
//				// Create the component for the generated column
//				return new Label(prov.getNom().toString());
//			}
//		});
//		// tableFacture.addValueChangeListener(new
//		// Property.ValueChangeListener() {
//		// public void valueChange(ValueChangeEvent event) {
//		// Object selectedRow = tableFacture.getValue();
//		//
//		// }
//		// });
//
//		Button ajoutProvider = new Button("Enregistrer un Fournisseur",
//				new Button.ClickListener() {
//					public void buttonClick(ClickEvent event) {
//
//						// Create a sub-window and set the content
//						final Window subWindow = new Window(
//								"Enregistrer un Fournisseur");
//						VerticalLayout subContent = new VerticalLayout();
//						subContent.setWidth("60%");
//						subContent.setMargin(true);
//						subContent.setSpacing(true);
//						subWindow.setContent(subContent);
//						// Put some components in it
//
//						// Center it in the browser window
//						subWindow.center();
//
//						HorizontalLayout h0 = new HorizontalLayout();
//						h0.setSpacing(true);
//						h0.setMargin(true);
//						HorizontalLayout h = new HorizontalLayout();
//						h.setSpacing(true);
//						h.setMargin(true);
//						HorizontalLayout h1 = new HorizontalLayout();
//						h1.setSpacing(true);
//						h1.setMargin(true);
//						HorizontalLayout h2 = new HorizontalLayout();
//						h2.setSpacing(true);
//						h2.setMargin(true);
//						HorizontalLayout h3 = new HorizontalLayout();
//						h3.setSpacing(true);
//						h3.setMargin(true);
//
//						final TextField nomFour = new TextField(
//								"Nom Du Fournisseur");
//						final TextField numTva = new TextField("Num de TVA");
//						final TextField article = new TextField("Article");
//						final TextField numRgistre = new TextField(
//								"Num de Rgistre");
//
//						final TextField nomCommune = new TextField("Commune");
//
//						final TextField codePostal = new TextField(
//								"code Postal");
//						final TextField rue = new TextField("La Rue");
//						final TextField numero = new TextField("Numero");
//
//						final TextField numFax = new TextField("Fax");
//						final TextField numTel = new TextField("Tel");
//						final TextField numGSM = new TextField("GSM");
//						final TextField email = new TextField("Email");
//
//						h.addComponent(nomFour);
//						h.addComponent(nomCommune);
//						h.addComponent(numTva);
//						subContent.addComponent(h);
//
//						h0.addComponent(article);
//						h0.addComponent(numRgistre);
//						subContent.addComponent(h0);
//
//						h1.addComponent(rue);
//						h1.addComponent(codePostal);
//						h1.addComponent(numero);
//						subContent.addComponent(h1);
//
//						h2.addComponent(numFax);
//						h2.addComponent(numTel);
//
//						h3.addComponent(numGSM);
//						h3.addComponent(email);
//						subContent.addComponent(h2);
//						subContent.addComponent(h3);
//						//
//						// fournisseur.setCaption("fournisseur");
//						// for (Fournisseur prov :
//						// fournisseurDAO.getAll(Fournisseur.class)) {
//						// fournisseur.addItem(prov);
//						// fournisseur.setItemCaption(prov,
//						// prov.getNom().toString());
//						// }
//						//
//						subWindow.setModal(true);
//
//						Button sauver = new Button("Nouveau Fournisseur",
//								new Button.ClickListener() {
//									public void buttonClick(ClickEvent event) {
//
//										if (nomFour.getValue() == null
//												|| nomCommune.getValue() == null
//												|| numTva.getValue() == null) {
//											Notification
//													.show("Veuillez introduire les donnees obligatoires !");
//											return;
//										}
//										if (article.getValue() == null
//												|| numRgistre.getValue() == null) {
//											Notification
//													.show("Veuillez introduire les numeros obligatoires !");
//											return;
//										}
//
//										if (rue.getValue() == null
//												|| numero.getValue() == null) {
//
//											Notification
//													.show("Veulliez introduire les données d'adresse !! ");
//
//											return;
//										}
//										if (numFax.getValue() == null
//												|| numTel.getValue() == null
//												|| numGSM.getValue() == null
//												|| email.getValue() == null) {
//
//											Notification
//													.show("Veulliez introduire les données du Contact !! ");
//
//											return;
//										}
//										MoyenContact mc = new MoyenContact();
//
//										mc.setEmail(email.getValue());
//										mc.setGsm(numGSM.getValue());
//										mc.setTelephone(numTel.getValue());
//										mc.setFax(numFax.getValue());
//
//										Adresse add = new Adresse();
//
//										add.setNomCommune(nomCommune.getValue());
//										add.setCodePostal(Integer
//												.parseInt(codePostal.getValue()));
//										add.setNumero(numero.getValue());
//										add.setRue(rue.getValue());
//
//										Fournisseur mat = new Fournisseur();
//
//										mat.setNom(nomFour.getValue());
//										mat.setNumTva(numTva.getValue());
//										mat.setArticle(article.getValue());
//										mat.setNumeroRegistre(numRgistre
//												.getValue());
//										mat.setMoyenContact(mc);
//										mat.setAddress(add);
//										fournisseurDAO.save(mat);
//
//										Notification
//												.show("Le Fournisseur est bien sauvé ");
//										buildFactureProv();
//										subWindow.close();
//										Layout tab = (Layout) right
//												.getSelectedTab();
//										tab.removeAllComponents();
//										tab.addComponent(buildFactureProv());
//
//									}
//								});
//
//						subContent.addComponent(sauver);
//
//						subContent.setComponentAlignment(sauver,
//								Alignment.BOTTOM_CENTER);
//						// Open it in the UI
//						addWindow(subWindow);
//
//					}
//				});
//
//		// TODO To print the invoice in the released version we as the button
//		// retrieve Entity recently saved in DB
//		Button ajoutFacture = new Button("Ajouter Facture",
//				new Button.ClickListener() {
//					public void buttonClick(ClickEvent event) {
//
//						// Create a sub-window and set the content
//						final Window subWindow = new Window("Ajouter Facture");
//						VerticalLayout subContent = new VerticalLayout();
//						subContent.setMargin(true);
//						subContent.setSpacing(true);
//						subWindow.setContent(subContent);
//						// Put some components in it
//
//						// Center it in the browser window
//						subWindow.center();
//
//						HorizontalLayout h = new HorizontalLayout();
//						h.setSpacing(true);
//						h.setMargin(true);
//						HorizontalLayout h1 = new HorizontalLayout();
//						h1.setSpacing(true);
//						h1.setMargin(true);
//						HorizontalLayout h2 = new HorizontalLayout();
//						h2.setSpacing(true);
//						h2.setMargin(true);
//						HorizontalLayout h3 = new HorizontalLayout();
//						h3.setSpacing(true);
//						h3.setMargin(true);
//
//						final DateField dateS = new DateField(
//								"Date début echeance");
//						// Set the date and time to present
//						dateS.setValue(new Date());
//
//						final DateField dateF = new DateField(
//								"Date fin echeance");
//						// Set the date and time to present
//						dateF.setValue(new Date());
//
//						final TextField totFacture = new TextField(
//								"montant Facture");
//						final TextField numFacture = new TextField(
//								"num Facture");
//						final TextField codFacture = new TextField(
//								"code Facture");
//						final ComboBox fournisseur = new ComboBox("fournisseur");
//
//						h.addComponent(dateS);
//						h.addComponent(dateF);
//						subContent.addComponent(h);
//
//						h1.addComponent(fournisseur);
//						h1.addComponent(numFacture);
//						subContent.addComponent(h1);
//
//						h2.addComponent(codFacture);
//						h2.addComponent(totFacture);
//
//						h3.addComponent(fournisseur);
//						subContent.addComponent(h2);
//						subContent.addComponent(h3);
//
//						fournisseur.setCaption("fournisseur");
//						for (Fournisseur prov : fournisseurDAO
//								.getAll(Fournisseur.class)) {
//							fournisseur.addItem(prov);
//							fournisseur.setItemCaption(prov, prov.getNom());
//						}
//
//						subWindow.setModal(true);
//
//						Button sauverFacture = new Button("sauver Facture",
//								new Button.ClickListener() {
//									public void buttonClick(ClickEvent event) {
//
//										if (dateS.getValue() == null
//												|| dateF.getValue() == null) {
//											Notification
//													.show("Veuillez introduire les dates !");
//											return;
//										}
//
//										if (totFacture.getValue() == null
//												|| numFacture.getValue() == null
//												|| codFacture.getValue() == null) {
//
//											Notification
//													.show("Veulliez introduire les données obligatoires  !! ");
//
//											return;
//										}
//
//										Facture mat = new Facture();
//										mat.setDateEcheanceFacture(dateF
//												.getValue());
//										mat.setDateFacture(dateS.getValue());
//										mat.setCodFacture(Integer
//												.parseInt(codFacture.getValue()));
//										mat.setNumFacture(numFacture.getValue());
//										mat.setTotFacture(Integer
//												.parseInt(totFacture.getValue()));
//										mat.setFournisseur((Fournisseur) fournisseur
//												.getValue());
//										mat.setEtatFacture(EtatFacture.NONPAYEE);
//
//										//
//										factureDAO.save(mat);
//
//										Notification
//												.show("La Facture est bien sauvé ");
//
//										subWindow.close();
//										buildFactureProv();
//										Layout tab = (Layout) right
//												.getSelectedTab();
//										tab.removeAllComponents();
//										tab.addComponent(buildFactureProv());
//
//									}
//								});
//
//						subContent.addComponent(sauverFacture);
//
//						subContent.setComponentAlignment(sauverFacture,
//								Alignment.BOTTOM_CENTER);
//						// Open it in the UI
//						addWindow(subWindow);
//
//					}
//				});
//
//		layout.addComponent(ajoutProvider);
//		layout.setSpacing(true);
//		layout.setMargin(true);
//		layout.setComponentAlignment(ajoutProvider, Alignment.TOP_CENTER);
//		layout.addComponent(ajoutFacture);
//		layout.setSpacing(true);
//		layout.setMargin(true);
//		layout.setComponentAlignment(ajoutFacture, Alignment.TOP_CENTER);
//
//		return layout;
//	}
//
//	HorizontalLayout buildMateriel() {
//
//		HorizontalLayout layout = new HorizontalLayout();
//		Panel mat = new Panel("Matériel");
//		mat.setSizeFull();
//		mat.addStyleName(Runo.PANEL_LIGHT);
//		layout.addComponent(mat);
//
//		BeanContainer<String, Materiel> beans = new BeanContainer<String, Materiel>(
//				Materiel.class);
//
//		// Use the name property as the item ID of the bean
//		beans.setBeanIdProperty("id");
//
//		// Add some beans to it
//		for (Materiel materiel : materielDAO.findAll()) {
//			beans.addBean(materiel);
//		}
//
//		beans.addNestedContainerProperty("materielLocation.retard");
//		beans.addNestedContainerProperty("materielLocation.prixTotalLocation");
//
//		// Bind a table to it
//		tableMatriel = new Table(null, beans);
//		layout.addComponent(tableMatriel);
//		// END-EXAMPLE: datamodel.container.beancontainer.basic
//
//		tableMatriel.setPageLength(beans.size());
//
//		tableMatriel
//				.setVisibleColumns(new Object[] { "codeMateriel",
//						"description", "typeMateriel", "etatMateriel",
//						"departement", "disponibilite", "materielLocation",
//						"materielLocation.prixTotalLocation",
//						"materielLocation.retard" });
//
//		tableMatriel.setColumnHeader("codeMateriel", "Code Matériel");
//		tableMatriel.setColumnHeader("description", "Description");
//		tableMatriel.setColumnHeader("typeMateriel", "Type Matériel");
//		tableMatriel.setColumnHeader("etatMateriel", "Etat Matériel");
//		tableMatriel.setColumnHeader("departement", "Département");
//		tableMatriel.setColumnHeader("disponibilite", "Disponible");
//		tableMatriel.setColumnHeader("materielLocation", "Dates location");
//		tableMatriel.setColumnHeader("materielLocation.prixTotalLocation",
//				"Prix total location");
//		tableMatriel.setColumnHeader("materielLocation.retard", "Retard");
//
//		tableMatriel.addGeneratedColumn("departement", new ColumnGenerator() {
//			private static final long serialVersionUID = -8133822448459398254L;
//
//			@Override
//			public com.vaadin.ui.Component generateCell(Table source,
//					final Object itemId, Object columnId) {
//				// Get the value in the first column
//				Departement dep = (Departement) source.getContainerProperty(
//						itemId, "departement").getValue();
//
//				// Create the component for the generated column
//				return new Label(dep.getNom());
//			}
//		});
//
//		tableMatriel.addGeneratedColumn("materielLocation",
//				new ColumnGenerator() {
//					private static final long serialVersionUID = -8133822448459398254L;
//
//					@Override
//					public com.vaadin.ui.Component generateCell(Table source,
//							final Object itemId, Object columnId) {
//						// Get the value in the first column
//						MaterielLocation matlId = (MaterielLocation) source
//								.getContainerProperty(itemId,
//										"materielLocation").getValue();
//
//						if (matlId != null) {
//							DateFormat shortDateFormat = DateFormat
//									.getDateTimeInstance(DateFormat.SHORT,
//											DateFormat.SHORT);
//							return new Label("De "
//									+ shortDateFormat.format(matlId
//											.getDateDebutLocation())
//									+ " A "
//									+ shortDateFormat.format(matlId
//											.getDateFinLocation()));
//						}
//						// Create the component for the generated column
//						return null;
//					}
//				});
//	if(grid1.getColumn("dateFacture")!=null){
//		Grid.Column dateColF = grid1.getColumn("dateFacture"); 
//		dateColF.setRenderer(new DateRenderer("%s", Locale.getDefault(),"" ));
//		grid1.addColumn(dateColF);
//	}
//
//		Button ajouterMat = new Button("Ajouter Matériel",
//				new Button.ClickListener() {
//					public void buttonClick(ClickEvent event) {
//
//						// Create a sub-window and set the content
//						final Window subWindow = new Window(
//								"Ajouter un Matériel");
//						VerticalLayout subContent = new VerticalLayout();
//						subContent.setMargin(true);
//						subContent.setSpacing(true);
//						subWindow.setContent(subContent);
//
//						// Put some components in it
//
//						// Center it in the browser window
//						subWindow.center();
//
//						HorizontalLayout h1 = new HorizontalLayout();
//						h1.setSpacing(true);
//						h1.setMargin(true);
//						HorizontalLayout h2 = new HorizontalLayout();
//						h2.setSpacing(true);
//						h2.setMargin(true);
//						HorizontalLayout h3 = new HorizontalLayout();
//						h3.setSpacing(true);
//						h3.setMargin(true);
//						HorizontalLayout h4 = new HorizontalLayout();
//						h4.setSpacing(true);
//						h4.setMargin(true);
//
//						final TextField codeMateriel = new TextField(
//								"Code Materiel");
//						final TextField description = new TextField(
//								"Descrition");
//						final TextField typeMateriel = new TextField(
//								"Type Materiel");
//						typeMateriel.setValue(TypeMateriel.JOURNALISTE.name());
//						final ComboBox etatMateriel = new ComboBox(
//								"Etat Materiel");
//						final ComboBox departement = new ComboBox("Département");
//						h1.addComponent(codeMateriel);
//						h1.addComponent(description);
//
//						subContent.addComponent(h1);
//
//						h2.addComponent(typeMateriel);
//						h2.addComponent(etatMateriel);
//
//						h3.addComponent(departement);
//						subContent.addComponent(h2);
//						subContent.addComponent(h3);
//
//						departement.setCaption("Départements");
//						for (Departement depa : departementDAO
//								.getAll(Departement.class)) {
//							departement.addItem(depa);
//							departement.setItemCaption(depa, depa.getNom());
//						}
//
//						for (EtatMateriel etat : EtatMateriel.values()) {
//							etatMateriel.addItem(etat.name());
//						}
//
//						subWindow.setModal(true);
//
//						Button sauver = new Button("Sauver",
//								new Button.ClickListener() {
//									public void buttonClick(ClickEvent event) {
//
//										if (departement.getValue() == null
//												|| codeMateriel.getValue() == null
//												|| description.getValue() == null) {
//
//											Notification
//													.show("Veulliez introduire les données obligatoires du matériel !! ");
//
//											return;
//										}
//
//										Materiel mat = new Materiel();
//										mat.setCodeMateriel(codeMateriel
//												.getValue());
//										mat.setDepartement((Departement) departement
//												.getValue());
//										mat.setDescription(description
//												.getValue());
//										mat.setDisponibilite(true);
//										mat.setEtatMateriel(EtatMateriel.NOUVEAU);
//										mat.setPrixLocationJour(BigDecimal.TEN);
//										mat.setTypeMateriel(TypeMateriel.JOURNALISTE);
//										//
//										materielDAO.save(mat);
//
//										Notification
//												.show("Le matériel a été bien sauvé ");
//
//										subWindow.close();
//
//									}
//								});
//
//						subContent.addComponent(sauver);
//
//						subContent.setComponentAlignment(sauver,
//								Alignment.BOTTOM_CENTER);
//						// Open it in the UI
//						addWindow(subWindow);
//
//					}
//				});
//
//		layout.addComponent(ajouterMat);
//		layout.setSpacing(true);
//		layout.setMargin(true);
//		layout.setComponentAlignment(ajouterMat, Alignment.BOTTOM_CENTER);
//
//		return layout;
//	}
//
//	HorizontalLayout buildDepartement() {
//
//		HorizontalLayout layout = new HorizontalLayout();
//		Panel mat = new Panel("Département");
//		mat.setSizeFull();
//		mat.addStyleName(Runo.PANEL_LIGHT);
//		layout.addComponent(mat);
//
//		BeanContainer<String, Departement> beans = new BeanContainer<String, Departement>(
//				Departement.class);
//
//		// Use the name property as the item ID of the bean
//		beans.setBeanIdProperty("id");
//
//		// Add some beans to it
//		for (Departement dep : departementDAO.getAll(Departement.class)) {
//			beans.addBean(dep);
//		}
//
//		// Bind a table to it
//		tableDepartements = new Table(null, beans);
//		// END-EXAMPLE: datamodel.container.beancontainer.basic
//
//		tableDepartements.setPageLength(beans.size());
//
//		tableDepartements.setVisibleColumns(new Object[] { "nom", "address",
//				"typeDepartement", "moyenContact" });
//
//		tableDepartements.setColumnHeader("nom", "Description");
//		tableDepartements.setColumnHeader("address", "Adresse");
//		tableDepartements
//				.setColumnHeader("typeDepartement", "Type Departement");
//		tableDepartements.setColumnHeader("moyenContact", "Moyen contact");
//
//		tableDepartements.addGeneratedColumn("address", new ColumnGenerator() {
//			private static final long serialVersionUID = -8133822448459398254L;
//
//			@Override
//			public com.vaadin.ui.Component generateCell(Table source,
//					final Object itemId, Object columnId) {
//				// Get the value in the first column
//				// adresseDAO.findAdresseByID(itemId.);
//
//				Adresse add = (Adresse) source.getContainerProperty(itemId,
//						"address").getValue();
//
//				// Create the component for the generated column
//				return new Label(add.getRue() + " " + add.getNumero() + " "
//						+ add.getCodePostal() + " " + add.getNomCommune());
//			}
//		});
//
//		tableDepartements.addGeneratedColumn("moyenContact",
//				new ColumnGenerator() {
//					private static final long serialVersionUID = -8133822448459398254L;
//
//					@Override
//					public com.vaadin.ui.Component generateCell(Table source,
//							final Object itemId, Object columnId) {
//						// Get the value in the first column
//						MoyenContact add = (MoyenContact) source
//								.getContainerProperty(itemId, "moyenContact")
//								.getValue();
//
//						// Create the component for the generated column
//						return new Label(add.getEmail() + " "
//								+ add.getTelephone() + " ");
//					}
//				});
//
//		layout.addComponent(tableDepartements);
//
//		return layout;
//	}
//
//	VerticalLayout buildLocationMaterielJournaliste() {
//
//		VerticalLayout layout = new VerticalLayout();
//		layout.setMargin(true);
//		layout.setSpacing(true);
//
//		Panel mat = new Panel();
//		mat.setSizeFull();
//		mat.addStyleName(Runo.PANEL_LIGHT);
//		layout.addComponent(mat);
//
//		BeanContainer<String, Materiel> beans = new BeanContainer<String, Materiel>(
//				Materiel.class);
//
//		// Use the name property as the item ID of the bean
//		beans.setBeanIdProperty("id");
//
//		// Add some beans to it
//		for (Materiel materiel : materielDAO
//				.findMaterielsDisponible(TypeMateriel.JOURNALISTE)) {
//			beans.addBean(materiel);
//		}
//
//		// Bind a table to it
//		tableMatrielLocation = new Table(null, beans);
//		tableMatrielLocation.setSelectable(true);
//
//		tableMatrielLocation
//				.addValueChangeListener(new Property.ValueChangeListener() {
//					public void valueChange(ValueChangeEvent event) {
//						Object selectedRow = tableMatrielLocation.getValue();
//
//					}
//				});
//
//		layout.addComponent(tableMatrielLocation);
//
//		Button louer = new Button("Louer", new Button.ClickListener() {
//			public void buttonClick(ClickEvent event) {
//
//				Long idMat = (Long) tableMatrielLocation.getValue();
//				if (idMat == null) {
//					Notification.show("Veuillez séléctionner un Matériel !");
//					return;
//				}
//				// Create a sub-window and set the content
//				final Window subWindow = new Window(
//						"Location Matériel Personne");
//				VerticalLayout subContent = new VerticalLayout();
//				subContent.setMargin(true);
//				subContent.setSpacing(true);
//				subWindow.setContent(subContent);
//
//				// Put some components in it
//
//				// Center it in the browser window
//				subWindow.center();
//
//				BeanContainer<String, Personne> beans = new BeanContainer<String, Personne>(
//						Personne.class);
//
//				beans.setBeanIdProperty("id");
//
//				// Add some beans to it
//				for (Personne p : personneDAO.getAll(Personne.class)) {
//					beans.addBean(p);
//				}
//
//				tablePersonnes = new Table("Choisir une Personne", beans);
//				tablePersonnes.setSelectable(true);
//
//				tablePersonnes
//						.addValueChangeListener(new Property.ValueChangeListener() {
//							public void valueChange(ValueChangeEvent event) {
//
//								BeanItem<Personne> selectedPersonne = (BeanItem<Personne>) tablePersonnes
//										.getItem(event.getProperty().getValue());
//
//							}
//						});
//
//				tablePersonnes.setPageLength(beans.size());
//				tablePersonnes.setVisibleColumns(new Object[] { "nom",
//						"prenom", "typePersonne", "dateNaissance" });
//
//				tablePersonnes.setColumnHeader("nom", "Nom");
//				tablePersonnes.setColumnHeader("prenom", "Prénom");
//				tablePersonnes.setColumnHeader("typePersonne", "Type Personne");
//				tablePersonnes.setColumnHeader("dateNaissance",
//						"Date Naissance");
//
//				HorizontalLayout h = new HorizontalLayout();
//				h.setMargin(true);
//				h.setSpacing(true);
//
//				// add dates
//				final DateField dateS = new DateField("Date début location");
//				// Set the date and time to present
//				dateS.setValue(new Date());
//
//				final DateField dateF = new DateField("Date fin location");
//				// Set the date and time to present
//				dateF.setValue(new Date());
//
//				h.addComponent(dateS);
//				h.addComponent(dateF);
//
//				subContent.addComponent(h);
//
//				subContent.setWidth("800px");
//				subContent.setHeight("500px");
//
//				subContent.addComponent(tablePersonnes);
//				tablePersonnes.setWidth("100%");
//
//				Button conf = new Button("Confirmer Location",
//						new Button.ClickListener() {
//							public void buttonClick(ClickEvent event) {
//
//								Long idMat = (Long) tableMatrielLocation
//										.getValue();
//								if (idMat == null) {
//									Notification
//											.show("Veuillez séléctionner un Matériel !");
//									return;
//								}
//
//								Materiel ma = materielDAO.getById(
//										Materiel.class, idMat);
//
//								// Confirmer location
//								Long idPers = (Long) tablePersonnes.getValue();
//								if (idPers == null) {
//									Notification
//											.show("Veuillez séléctionner une personne !");
//									return;
//								}
//
//								if (dateS.getValue() == null
//										|| dateF.getValue() == null) {
//									Notification
//											.show("Veuillez introduire les dates de locations !");
//									return;
//								}
//
//								Personne personne = personneDAO.getById(
//										Personne.class, idPers);
//								MaterielLocation materielLocation = new MaterielLocation();
//
//								materielLocation.setMateriel(ma);
//								materielLocation.setPersonne(personne);
//								materielLocation.setDateDebutLocation(dateS
//										.getValue());
//								materielLocation.setDateFinLocation(dateF
//										.getValue());
//
//								ma.setDisponibilite(false);
//
//								materielDAO.update(ma);
//								materielLocationDAO.save(materielLocation);
//
//								Notification
//										.show("Le matérile a été loué avec succés");
//
//								subWindow.close();
//
//								buildLocationMaterielJournaliste();
//
//								Layout tab = (Layout) right.getSelectedTab();
//								tab.removeAllComponents();
//
//								tab.addComponent(buildLocationMaterielJournaliste());
//							}
//						});
//
//				subContent.addComponent(conf);
//				subContent.setComponentAlignment(conf, Alignment.BOTTOM_CENTER);
//
//				// Open it in the UI
//				addWindow(subWindow);
//			}
//		});
//
//		layout.addComponent(louer);
//		layout.setComponentAlignment(louer, Alignment.BOTTOM_LEFT);
//
//		// END-EXAMPLE: datamodel.container.beancontainer.basic
//
//		tableMatrielLocation.setPageLength(beans.size());
//		tableMatrielLocation.setVisibleColumns(new Object[] { "codeMateriel",
//				"description", "typeMateriel", "etatMateriel",
//				"prixLocationJour" });
//
//		tableMatrielLocation.setColumnHeader("codeMateriel", "Code Matériel");
//		tableMatrielLocation.setColumnHeader("description", "Description");
//		tableMatrielLocation.setColumnHeader("typeMateriel", "Type Matériel");
//		tableMatrielLocation.setColumnHeader("etatMateriel", "Etat Matériel");
//		tableMatrielLocation.setColumnHeader("prixLocationJour",
//				"Prix de location par jour");
//
//		return layout;
//	}
//
//	/**
//	 * A variant of {@link #getCurrentService(Class)} which works on this
//	 * instance, instead of the current instance
//	 *
//	 * @param serviceType
//	 *            Service typef
//	 * @return a service
//	 */
//	public <SERVICE> SERVICE getService(Class<? extends SERVICE> serviceType) {
//		Object object = services.get(serviceType.getName());
//		return SafeReflection.cast(object, serviceType);
//	}
//
//	public String getUserDisplayName() {
//		VaadinRequest request = VaadinService.getCurrentRequest();
//		if (request instanceof HttpServletRequest) {
//			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//			return "user";
//		}
//		return "unknown hero";
//	}
//
//	public String getUserLogon() {
//		VaadinRequest request = VaadinService.getCurrentRequest();
//		if (request instanceof HttpServletRequest) {
//			Principal principal = request.getUserPrincipal();
//			if (principal != null) {
//				return principal.getName();
//			}
//		}
//		return "unknown hero";
//	}
//
//	private void setMainComponent(AbstractPage page) {
//
//		pageContainer.getPagePanel().removeAllComponents();
//		pageContainer.getPagePanel().addComponent(page);
//	}
//
//	private static final class MenuValueChangeListener implements
//			Property.ValueChangeListener {
//
//		private static final long serialVersionUID = 1L;
//		private final Class<? extends View> linkTarget;
//
//		private MenuValueChangeListener(Class<? extends View> linkTarget) {
//			this.linkTarget = linkTarget;
//		}
//
//		public void valueChange(ValueChangeEvent event) {
//			HistoryService.getInstance().navigateTo(this.linkTarget);
//		}
//
//	}
//
//	/**
//	 * (Re-)define a service. See {@link #getCurrentService(Class)}
//	 *
//	 * @param serviceType
//	 *            Service class
//	 * @param service
//	 *            service, can't be null
//	 * @return The previous service
//	 */
//	public <SERVICE> Object setService(Class<? extends SERVICE> serviceType,
//			SERVICE service) {
//		return services.put(serviceType.getName(), service);
//	}
//
//	@Override
//	public void showView(View view) {
//		if (!(view instanceof AbstractPage)) {
//			throw new IllegalArgumentException("Violation of view standard:"
//					+ view);
//		}
//		setMainComponent((AbstractPage) view);
//	}
//
//	@Override
//	protected void init(VaadinRequest request) {
//		HistoryService handler = HistoryService.getInstance();
//
//		handler.register(DepartementPage.class);
//		handler.registerErrorView(DepartementPage.class);
//
//		handler.register(HomePage.class);
//		handler.registerErrorView(HomePage.class);
//
//		doInit();
//	}
//
//	/**
//	 * get a service from the current UI.
//	 *
//	 * Basically, services are objects of user-specified type, which are
//	 * attached to the current UI. This is close to {@link #setData}, except
//	 * multiple services can be attached, distinguished by by their class name,
//	 * and they are typed.
//	 *
//	 * It is an error to request a service which is not yet defined by
//	 * {@link #setService}, or to call services while no active UI is defined.
//	 *
//	 * @param serviceType
//	 *            Any class
//	 * @return An object of this class, previously installed by
//	 *         {@link #setService}
//	 */
//	public static <SERVICE> SERVICE getCurrentService(
//			Class<? extends SERVICE> serviceType) {
//		MainUI current = getInstance();
//		if (current == null) {
//			throw new IllegalStateException("Not in a VaadinUI");
//		}
//		Object object = current.services.get(serviceType.getName());
//		if (object == null) {
//			throw new IllegalStateException("Service not defined:"
//					+ serviceType);
//		}
//		return SafeReflection.cast(object, serviceType);
//	}
//
//	public static MainUI getInstance() {
//		return (MainUI) UI.getCurrent();
//	}
	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub

	}

}
