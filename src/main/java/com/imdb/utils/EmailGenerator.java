package com.imdb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.annotations.findby.FindBy;

public class EmailGenerator extends CommonMethods {

	public static String impaHostDetail;
	public static String imapUserNameDetail;
	public static String imapPasswordDetail;
	public String emailServiceProvide;

	public EmailGenerator(WebDriver driver) {
		super(driver);
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("config.properties")));
			emailServiceProvide = prop.getProperty("EmailServiceProvider");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FindBy(partialLinkText = "please confirm your email")

	private WebElement subject;

	@FindBy(partialLinkText = "https://www.imdb.com/registration/confirmation")

	private WebElement activateAccount;

	@FindBy(css = "button.btn.btn-primary > span")

	private WebElement createAccountButton;

	@FindBy(xpath = "(//h4[.='IMAP configuration']/following::td/code)[1]")

	private WebElement imapHost;

	@FindBy(xpath = "(//h4[.='IMAP configuration']/following::td/code)[4]")

	private WebElement imapUserName;

	@FindBy(xpath = "(//h4[.='IMAP configuration']/following::td/code)[5]")

	private WebElement imapPassword;

	@FindBy(css = "span.oi-external-link")

	private WebElement openMailBox;

	@FindBy(id = "address")

	private WebElement address;

	@FindBy(id = "password")

	private WebElement password;

	@FindBy(css = ".oi-account-login")

	private WebElement accountLogin;

	@FindBy(linkText = "Messages")

	private WebElement messageTab;

	public void launchEmailServiceProvider() {
		try {
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
			getDriver().manage().timeouts().pageLoadTimeout(4, TimeUnit.MINUTES);
			getDriver().get(emailServiceProvide);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clickElement(createAccountButton);
		impaHostDetail = returnText(imapHost);
		imapUserNameDetail = returnText(imapUserName);
		imapPasswordDetail = returnText(imapPassword);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		clickElement(openMailBox);
		String mainWindow = getDriver().getWindowHandle();
		Set<String> set = getDriver().getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			if (!mainWindow.equals(childWindow)) {
				getDriver().switchTo().window(childWindow);
				System.out.println(getDriver().switchTo().window(childWindow).getTitle());
			}
		}
	}

	public void openMailBox() {
		clickElement(openMailBox);
	}

	public void loginToServiceProvider(String emailValue, String passwordValue) {
		typeInto(address, emailValue);
		typeInto(password, passwordValue);
		clickElement(accountLogin);
		clickElement(messageTab);
	}

	public void emailSubject() {
		clickElement(subject);
	}

	public void activateAccount() {
		clickElement(activateAccount);
	}
}