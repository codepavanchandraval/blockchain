package com.pavan.blockchain;

import com.pavan.component.MainComponent;
import com.pavan.model.ShipmentBean;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class BlockChainMainUI extends BaseUI {

	private static final long serialVersionUID = 2790109998565358976L;

	@Override
	public void init() {
		ShipmentBean bean = (ShipmentBean) vaadinSession.getAttribute("ShipmentBean");
		VerticalLayout layout = new VerticalLayout();
		MainComponent component = new MainComponent(bean);
		layout.addComponent(new Panel(component));
		Button cancel = new Button("Cancel");
		layout.addComponent(cancel);
		cancel.addClickListener(event -> Ui.getNavigator().navigateTo("BlockChainUI"));
		setCompositionRoot(layout);
	}

}
