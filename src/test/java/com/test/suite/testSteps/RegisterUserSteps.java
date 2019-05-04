package com.test.suite.testSteps;

import com.imdb.pages.RegisterUserPage;
import com.imdb.utils.EmailReader;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

public class RegisterUserSteps {
	RegisterUserPage registerUserPage;
	EmailReader emailReader;

	@Step
	public void createAccount() {
		registerUserPage.createAccount();
	}

	@Step
	public void setCustomerName(String customerNameValue) {
		registerUserPage.setCustomerName(customerNameValue);
	}

	@Step
	public void setEmailId(String emailValue) {
		Serenity.recordReportData().withTitle("Registered Email").andContents(registerUserPage.setEmailId(emailValue));
		registerUserPage.setEmailId(emailValue); 
	}

	@Step
	public void setPassword(String passwordValue) {
		registerUserPage.setPassword(passwordValue);
	}

	@Step
	public void setConfirmPassword(String confirmPasswordValue) {
		registerUserPage.setConfirmPassword(confirmPasswordValue);
	}

	@Step
	public void continueButton() {
		registerUserPage.continueButton();
	}

	@Step
	public void signIn() {
		registerUserPage.signIn();
	}

	@Step
	public void signInSubmit() {
		registerUserPage.signInSubmit();
	}

	@Step
	public void profileDropdown() {
		registerUserPage.profileDropdown();
	}

	@Step
	public void logout() {
		registerUserPage.logout();
	}

	@Step
	public void signInWithIMDB() {
		registerUserPage.signInWithIMDB();
	}

	@Step
	public void launchEmailServiceProvider(String userId) {
		emailReader.launchEmailServiceProvider(userId);
	}

	@Step
	public void emailSubject() {
		emailReader.emailSubject();
	}

	@Step
	public void activateAccount() {
		emailReader.activateAccount();
	}

	@Step
	public void completeRegistrationTitle(String expectedTitle) {
		registerUserPage.completeRegistrationTitle(expectedTitle);
	}
}
