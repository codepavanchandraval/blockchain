package com.pavan.component;

import java.util.List;

import com.pavan.model.ShipmentBean;
import com.pavan.util.BlockchainManager;
import com.vaadin.ui.HorizontalLayout;

public class MainComponent extends HorizontalLayout {

	private static final long serialVersionUID = 1771191052840024156L;

	public MainComponent(List<ShipmentBean> listData) {
		setMargin(false);
		if (listData == null) {
			this.setVisible(false);
			return;
		}
		for (ShipmentBean bean : listData) {
			MultiObjectComponent component = new MultiObjectComponent(bean);
			this.addComponent(component.getPanel());
		}
	}
	
	public MainComponent(ShipmentBean mainBean) {
		setMargin(false);
		if (mainBean == null) {
			this.setVisible(false);
			return;
		}
		for (ShipmentBean bean : BlockchainManager.getShipemntBean(mainBean)) {
			MultiObjectComponent component = new MultiObjectComponent(bean);
			this.addComponent(component.getPanel());
		}
	}
}
