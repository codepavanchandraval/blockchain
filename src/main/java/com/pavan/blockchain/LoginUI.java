package com.pavan.blockchain;

import com.pavan.model.User;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginUI  extends BaseUI{

	private static final long serialVersionUID = 2382129702346145127L;
	private Binder<User> binder;
	private TextField emailId;
	private  PasswordField password;
	

	@Override
	public void init() {
		VerticalLayout layout = new VerticalLayout();
		binder = new Binder<>();
		emailId = new TextField();
		emailId.setPlaceholder("Please enter your Email::");
		password = new PasswordField();
		password.setPlaceholder("Please enter your Password");

		binder.forField(emailId).asRequired().withValidator(new EmailValidator("Please Enter Correct Email")).bind(User::getEmailId, User::setEmailId);
		binder.forField(password).asRequired().bind(User::getPassword, User::setPassword);

		Button doLogin = new Button("Login");
		doLogin.addClickListener(event -> doLogin());

		layout.addComponents(emailId, password, doLogin);
		Panel panel = new Panel();
		panel.setContent(layout);
		
		
		setCompositionRoot(panel);
	}


	private void doLogin() {
		if (!binder.validate().isOk()) {
			Notification.show("Please Enter the Correct Email Id");
			return;
		}
		if ("Pavan@Dev.com".equals(emailId.getValue()) && "Pavan".equals(password.getValue())) {
			Ui.getNavigator().navigateTo("BlockChainUI");
			return;
		}
		Notification.show("Please Enter the Correct Credientials");
		return;
	}
}
