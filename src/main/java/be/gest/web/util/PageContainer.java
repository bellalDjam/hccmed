package be.gest.web.util;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;

/**
 * This class is the container of each page.
 * 
 *
 */
public class PageContainer extends Panel {

	private static final long serialVersionUID = 492562888852150243L;
	

	/**
	 * Constructeur.
	 */
	public PageContainer() {
		
		CssLayout content = new CssLayout();
		this.setContent(content);
	}
	

	/**
	 * @return the pagePanel
	 */
	public CssLayout getPagePanel() {
		return (CssLayout)this.getContent();
	}
	
}
