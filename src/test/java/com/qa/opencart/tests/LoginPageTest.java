package com.qa.opencart.tests;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.Constants;


//@Listeners(ExtentReportListener.class)
public class LoginPageTest{	
	private BasePage basePage;
	private LoginPage loginPage;
	private Properties prop;
	private WebDriver driver;
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browserName) {
		basePage = new BasePage();
		prop = basePage.init_prop();
		String browser = prop.getProperty("browser");
		
		if(browserName!=null) {
			browser=browserName;
		}

		driver = basePage.init_driver(browser);

		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority = 1)
	public void verifyLoginPageTitle() {	
		System.out.println("================================"+Thread.currentThread().getId());
		String title=loginPage.getLoginPageTitle();
		System.out.println("Login page title is : "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void verifyLoginPageHeadersListTest() {
		
		System.out.println("================================"+Thread.currentThread().getId());
		List<String> headers=loginPage.getHeadersInLoginPage();
		System.out.println("Headers are as follows : ");
		headers.stream().forEach(System.out::println);
		Assert.assertEquals(headers, Constants.getCommonHeadersList());
	}
	
	@Test(priority = 3)
	public void verifyForgotPwdLinkTest() {
		System.out.println("================================"+Thread.currentThread().getId());
		//Assert.assertTrue(loginPage.isForgotPwdLinkDisplayed());
		Assert.assertFalse(loginPage.isForgotPwdLinkDisplayed());
	}
	
	@Test(priority = 4)
	public void loginTest() {
		System.out.println("================================"+Thread.currentThread().getId());
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}
