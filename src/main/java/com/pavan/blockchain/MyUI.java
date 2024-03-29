package com.pavan.blockchain;

import javax.servlet.annotation.WebServlet;

import com.pavan.viewprovider.PViewProvider;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	final VerticalLayout layout = new VerticalLayout();
	private static final long serialVersionUID = -7687763695266200416L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		PViewProvider viewProvider = new PViewProvider(getSession());
		Navigator navigator = new Navigator(this, layout);
		navigator.addProvider(viewProvider);
		navigator.navigateTo("");
		setContent(layout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

    	private static final long serialVersionUID = -5139120461236022861L;
    }
}
