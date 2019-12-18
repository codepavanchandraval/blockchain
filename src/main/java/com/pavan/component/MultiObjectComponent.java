package com.pavan.component;

import com.pavan.model.ShipmentBean;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MultiObjectComponent extends VerticalLayout{

	private static final long serialVersionUID = -2125685103332008921L;
	
	private TextField originBranchDepartmentTextField;
	private TextField destinationBranchDepartment;
	private TextField eta_etd;
	private TextField pol_pod;
	private TextField shippers;
	private TextField consinee;
	private Panel mainComponent;
	
	public MultiObjectComponent(ShipmentBean bean) {
		init(bean);
	}

	private void init(ShipmentBean bean) {
		originBranchDepartmentTextField = new TextField("Origin Branch Department");
		originBranchDepartmentTextField.setValue(bean.getOriginBranchDepartment());
		originBranchDepartmentTextField.setEnabled(false);
		
		destinationBranchDepartment = new TextField("Destination Branch Department");
		destinationBranchDepartment.setValue(bean.getDestinationBranchDepartment());
		destinationBranchDepartment.setEnabled(false);
		
		eta_etd = new TextField("ETA-ETD");
		eta_etd.setValue(bean.getEta_etd());
		eta_etd.setEnabled(false);
		
		
		pol_pod = new TextField("POL-POD");
		pol_pod.setValue(bean.getPol_pod());
		pol_pod.setEnabled(false);
		
		shippers = new TextField("Shippers");
		shippers.setValue(bean.getShippers());
		shippers.setEnabled(false);
		
		consinee =new TextField("Consinee");
		consinee.setValue(bean.getConsinee());
		consinee.setEnabled(false);
	    
		this.addComponents(originBranchDepartmentTextField,destinationBranchDepartment,eta_etd,pol_pod,shippers,consinee);
		
		mainComponent = new Panel();
		mainComponent.setContent(this);	
		
	}
	
	public Panel getPanel() {
		return mainComponent;
	}
}
