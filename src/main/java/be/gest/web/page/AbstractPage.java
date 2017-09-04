package be.gest.web.page;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 */
public abstract class AbstractPage  extends Panel implements View  {

    private static final long serialVersionUID = 574731716926200103L;
    
    private VerticalLayout mainLayout = new VerticalLayout();

    /**
     */
   public abstract String getPageTitle();
   
    /**
     */
    public AbstractPage() {
    setContent(mainLayout);
    addStyleName("pageContent");
	setSizeFull();
	
	HorizontalLayout currentPageTitle = new HorizontalLayout();
	Label label = new Label(getPageTitle());
	label.addStyleName("align-center ");
	
	currentPageTitle.setWidth("1100px");
	currentPageTitle.setHeight("60px");
//	
	
	currentPageTitle.addComponent(label);
	currentPageTitle.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
	
	mainLayout.addComponent(currentPageTitle);
    }
    
    protected VerticalLayout getMainLayout() {
		return mainLayout;
	}
    
    public void enter(ViewChangeEvent event) {
    }

}
