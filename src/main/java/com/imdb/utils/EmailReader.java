package com.imdb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.imdb.pages.RegisterUserPage;
import net.serenitybdd.core.annotations.findby.FindBy;

public class EmailReader extends CommonMethods {

	public String emailServiceProvide;

	public EmailReader(WebDriver driver) {
		super(driver);
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
			emailServiceProvide = prop.getProperty("EmailServiceProvider");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FindBy(partialLinkText = "IMDb User Registration")

	private WebElement subject;

	@FindBy(xpath = "//*[contains(text(),'clicking this link')]/a")

	private WebElement activateAccount;

	public void launchEmailServiceProvider(String userId) {
		try {
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
			getDriver().manage().timeouts().pageLoadTimeout(4, TimeUnit.MINUTES);
			getDriver().get(emailServiceProvide + "mailbox/" + userId + RegisterUserPage.emailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void emailSubject() {
		clickElement(subject);
	}

	public void activateAccount() {
		clickElement(activateAccount);
	}
}
