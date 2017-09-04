package be.gest.web.page;

import org.springframework.beans.factory.annotation.Autowired;

import be.gest.dao.FactureDAO;
import be.gest.dao.FournisseurDAO;
import be.gest.model.Facture;
import be.gest.model.Fournisseur;
import be.gest.web.page.FactureEditor.EditorSavedEvent;
import be.gest.web.page.FactureEditor.EditorSavedListener;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Or;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class FournisseurMainView extends HorizontalSplitPanel implements
		ComponentContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1933613186712406685L;

	@Autowired
	private FournisseurDAO fournisseurDAO;
	@Autowired
	private FactureDAO factureDAO;

	private BeanContainer<String, Facture> factures;
	private BeanContainer<String, Fournisseur> fournisseurs;

	private Tree groupTree;
	private TextField searchField;
	private Button newButton;
	private Button deleteButton;
	private Button editButton;
	private String textFilter;

	Table factureTable;

	public FournisseurMainView() {
	
//		buildTree();
		buildMainArea();

		setSplitPosition(30);
	}

	

//	private void buildTree() {
//		// TODO Auto-generated method stub
//
//	}

	@SuppressWarnings({ "deprecation", "serial" })
	public void buildMainArea() {
		VerticalLayout verticalLayout = new VerticalLayout();
		setSecondComponent(verticalLayout);

		BeanContainer<String, Facture> beans = new BeanContainer<String, Facture>(
				Facture.class);
		for (Facture facture : factureDAO.getAll(Facture.class)) {
			beans.addBean(facture);
		}
		factureTable = new Table(null, beans);

		factureTable.setSelectable(true);
		factureTable.setImmediate(true);
		factureTable.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				setModificationsEnabled(event.getProperty().getValue() != null);
			}

			private void setModificationsEnabled(boolean b) {
				deleteButton.setEnabled(b);
				editButton.setEnabled(b);
			}
		});

		factureTable.addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					factureTable.select(event.getItemId());
				}
			}
		});

		factureTable.setPageLength(beans.size());

		factureTable.setVisibleColumns(new Object[] { "fournisseur",
				"numFacture", "codFacture", "totFacture", "dateFacture",
				"etatFacture", "solde", "dateEcheanceFacture" });
		
		HorizontalLayout toolbar = new HorizontalLayout();
		
		newButton = new Button("Add");
		newButton.addClickListener(new Button.ClickListener() {

			
			@SuppressWarnings("serial")
			@Override
			public void buttonClick(ClickEvent event) {
				final BeanItem<Facture> newFactureItem = new BeanItem<Facture>(
						new Facture());
				FactureEditor factureEditor = new FactureEditor(newFactureItem);
				factureEditor.addListener(new EditorSavedListener() {
					@Override
					public void editorSaved(EditorSavedEvent event) {
						factures.addBean(newFactureItem.getBean());
					}
				});
				UI.getCurrent().addWindow(factureEditor);
			}
		});
		
		deleteButton = new Button("delet");
		deleteButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				factures.removeItem(factureTable.getValue());
			}
		});
		deleteButton.setEnabled(false);

        editButton = new Button("Edit");
        editButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                UI.getCurrent().addWindow(
                        new FactureEditor(factureTable.getItem(factureTable
                                .getValue())));
            }
        });
        editButton.setEnabled(false);
        searchField = new TextField();
        searchField.setInputPrompt("Search by name");
        searchField.addTextChangeListener(new TextChangeListener() {

            @Override
            public void textChange(TextChangeEvent event) {
                textFilter = event.getText();
                updateFilters();
            }	
        });

        toolbar.addComponent(newButton);
        toolbar.addComponent(deleteButton);
        toolbar.addComponent(editButton);
        toolbar.addComponent(searchField);
        toolbar.setWidth("100%");
        toolbar.setExpandRatio(searchField, 1);
        toolbar.setComponentAlignment(searchField, Alignment.TOP_RIGHT);

        verticalLayout.addComponent(toolbar);
        verticalLayout.addComponent(factureTable);
        verticalLayout.setExpandRatio(factureTable, 1);
        verticalLayout.setSizeFull();
//		factureTable.setColumnHeader("fournisseur", "fournisseur");
//		factureTable.setColumnHeader("numFacture", "num Facture");
//		factureTable.setColumnHeader("codFacture", "code Facture");
//		factureTable.setColumnHeader("totFacture", "montant Facture");
//		factureTable.setColumnHeader("dateFacture", "date Facture");
//		factureTable.setColumnHeader("etatFacture", "etat facture");
//		factureTable.setColumnHeader("solde", "solde");
//		factureTable.setColumnHeader("dateEcheanceFacture",
//				"date d'Echeance Facture");

	}
	private void updateFilters() {
		factures.removeAllContainerFilters();
		if (textFilter != null && !textFilter.equals("")) {
            Or or = new Or(new Like("firstName", textFilter + "%", false),
                    new Like("lastName", textFilter + "%", false));
            factures.addContainerFilter(or);
        }
		
	}

}
