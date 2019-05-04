package com.test.suite.stepDefinitions;

import java.util.Map;
import com.imdb.utils.ExcelReader;
import com.test.suite.testSteps.RegisterUserSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class RegisterUserStepDefs {

	@Steps
	RegisterUserSteps registerUserSteps;

	@Then("^Click on Create Account button$")
	public void createAccountButton() {
		registerUserSteps.createAccount();
	}

	@Given("^Fill in the following user details to create a new account$")
	@Then("^Fill in the following user details to sign in$")
	public void userDetails(DataTable table) {
		for (Map<String, String> row : table.asMaps(String.class, String.class)) {
			String field = row.get("Field");
			String value = row.get("Value");
			setUserDetails(field, value);
		}
	}

	@Then("^Click on Continue button$")
	public void continueButton() {
		registerUserSteps.continueButton();
	}

	@Then("^Click on Sign In link$")
	public void signInLink() {
		registerUserSteps.signIn();
	}

	@Then("^click on Submit button$")
	public void submitButton() {
		registerUserSteps.signInSubmit();
	}

	private void setUserDetails(String field, String value) {
		switch (field) {
		case "Customer Name":
			registerUserSteps.setCustomerName(value);
			break;

		case "Email Address":
			registerUserSteps.setEmailId(value);
			break;

		case "Password":
			registerUserSteps.setPassword(value);
			break;

		case "Confirm Password":
			registerUserSteps.setConfirmPassword(value);
			break;

		}
	}

	@Then("^Click on logout option$")
	public void logout() {
		registerUserSteps.logout();
	}

	@Then("^Click on profile dropdown$")
	public void profileDropdown() {
		registerUserSteps.profileDropdown();
	}

	@Then("^Sign in with imdb account$")
	public void signInWithIMDB() {
		registerUserSteps.signInWithIMDB();
	}

	@Given("^Fill in the following user details: \"([^\"]*)\"$")
	public void userDetails_CreateAccount(String field) {
		try {
			ExcelReader.setExcelFile("./data/userdata.xlsx", "UserDetails");
			if (field.equals("Create Account")) {
				registerUserSteps.setCustomerName(ExcelReader.getCellData(1, 0));
				registerUserSteps.setEmailId(ExcelReader.getCellData(1, 1));
				registerUserSteps.setPassword(ExcelReader.getCellData(1, 2));
				registerUserSteps.setConfirmPassword(ExcelReader.getCellData(1, 3));

			} else if (field.equals("Sign In")) {
				registerUserSteps.setEmailId(ExcelReader.getCellData(1, 1));
				registerUserSteps.setPassword(ExcelReader.getCellData(1, 2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Given("^Launch the email service provider with email address$")
	public void launchServiceProvider() {
		try {
			ExcelReader.setExcelFile("./data/userdata.xlsx", "UserDetails");
			registerUserSteps.launchEmailServiceProvider(ExcelReader.getCellData(1, 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^Click on verification email that the user received$")
	public void receivedEmail() {
		registerUserSteps.emailSubject();
	}

	@Then("^Click on account activation link$")
	public void activateAccount() {
		registerUserSteps.activateAccount();
	}

	@Then("^Verifyt the title \"([^\"]*)\"$")
	public void assertPageNavigation(String expectedText) {
		registerUserSteps.completeRegistrationTitle(expectedText);
	}
}
