package com.pavan.viewprovider;

import java.util.HashMap;

import com.pavan.blockchain.BaseUI;
import com.pavan.blockchain.BlockChainMainUI;
import com.pavan.blockchain.BlockChainUI;
import com.pavan.blockchain.LoginUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinSession;

public class PViewProvider  implements ViewProvider{

	private static final long serialVersionUID = -6453639226249169566L;
	private HashMap<String,Class<? extends BaseUI>> viewNameToView;
	private VaadinSession vaadinSession;

	public PViewProvider(VaadinSession vaadinSession) {
		this.vaadinSession = vaadinSession;
		viewNameToView = new HashMap<>();
		init();
	}

	private void init() {
		viewNameToView.put("", LoginUI.class);
		viewNameToView.put("BlockChainUI", BlockChainUI.class);
		viewNameToView.put("BlockChainMainUI", BlockChainMainUI.class);
	}

	@Override
	public String getViewName(String viewAndParameters) {
		return viewAndParameters;
	}

	@Override
	public View getView(String viewName) {
		Class<? extends BaseUI> ui = viewNameToView.get(viewName);
		if (ui != null) {
			try {
				BaseUI view = ui.newInstance();
				view.initDefObjects(vaadinSession, null);
				view.init();
				return view;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
