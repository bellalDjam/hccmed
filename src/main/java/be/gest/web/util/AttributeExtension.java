package be.gest.web.util;

import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.TextField;

public class AttributeExtension extends AbstractJavaScriptExtension {

    public void extend(TextField target) {
        super.extend(target);
    }

    @Override
    protected AttributeExtensionState getState() {
        return (AttributeExtensionState) super.getState();
    }

    public void setAttribute(String attribute, String value) {
        getState().attributes.put(attribute, value);
    }
}
