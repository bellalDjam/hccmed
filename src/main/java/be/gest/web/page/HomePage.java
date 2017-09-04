package be.gest.web.page;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HomePage extends AbstractPage implements View {

	private static final long serialVersionUID = 9006143754423411704L;
	
	
	public HomePage() {

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(new Label("Home Label"));

        getMainLayout().addComponent(layout);
    }

	public String getPageTitle() {
		return "Gestionic  Home";
	}

	public void enter(ViewChangeEvent event) {
		
	}

}
