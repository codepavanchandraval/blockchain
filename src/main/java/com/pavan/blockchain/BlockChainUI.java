package com.pavan.blockchain;

import com.pavan.model.ShipmentBean;
import com.pavan.util.BlockchainManager;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

public class BlockChainUI  extends BaseUI{
    private static final long serialVersionUID = -3702714594322304919L;

	@Override
	public void init() {
		VerticalLayout layout = new VerticalLayout();

		Grid<ShipmentBean> grid = new Grid<>();
		grid.addColumn(ShipmentBean::getShipmentRefNo).setId("shipementRefNo").setCaption("Shipment Ref No.");
		grid.addColumn(ShipmentBean::getHouseNo).setId("houseNo").setCaption("House No.");
		grid.setItems(BlockchainManager.getShipemntBean());
		grid.addItemClickListener(event -> {
			vaadinSession.setAttribute("ShipmentBean", event.getItem());
			Ui.getNavigator().navigateTo("BlockChainMainUI");
		});
		layout.addComponent(grid);
		setCompositionRoot(layout);
	}
}
