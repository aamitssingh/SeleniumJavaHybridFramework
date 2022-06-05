package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

/*
 * Test class will call all the methods from page class and assert it.
 * 
 */

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginPageTitleTest()
	{
		String pageTitle = loginPage.getLoginPageTitle();
		//System.out.println("Login Page Title = "+pageTitle);
		Assert.assertEquals(pageTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginPageUrlTest()
	{
		String pageUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(pageUrl.contains(Constants.LOGIN_PAGE_URL_VALUE));
	}
	
	@Test
	public void forgotPwdLinkTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
//	@Test
//	public void registerLinkTest()
//	{
//		Assert.assertTrue(loginPage.isRegisterLinkExist());
//	}
	
	@Test
	public void loginTest()
	{
		//loginPage.doLogin("naveenanimation20@gmail.com", "Selenium12345");
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

}
