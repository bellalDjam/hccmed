package be.gest.web.page;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public abstract class DepartementPage extends AbstractPage   {

	private static final long serialVersionUID = 9006143754423411704L;
	
	final Panel panelContent = new Panel();
	final VerticalLayout content = new VerticalLayout();
	
	final String basepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath();
	
	public DepartementPage() {
	
		panelContent.setContent(content);
		panelContent.setWidth("900px");
		panelContent.addStyleName("bloc");
		
		getMainLayout().addComponent(panelContent);
	}
	
}
