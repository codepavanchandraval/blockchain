package com.pavan.blockchain;

import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;

public abstract class BaseUI extends CustomComponent implements View {

	private static final long serialVersionUID = -5679533412248374613L;
	protected VaadinSession vaadinSession;
	protected VaadinRequest vaadinRequest;
	protected UI Ui;
	
	public BaseUI() {
		Ui = UI.getCurrent();
	}
	
	public void initDefObjects(VaadinSession vaadinSession, String parentModule) {
		this.vaadinSession = vaadinSession;
		this.vaadinRequest = VaadinService.getCurrentRequest();
	}
  
	public abstract void init();
	
}
