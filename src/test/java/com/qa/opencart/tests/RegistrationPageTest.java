package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regPageSetUp()
	{
		registrationPage = loginPage.navigateToRegisterPage_FromTopMenu();
	}

	@Test
	public void registrationPageTitleTest()
	{
		String pageTitle = registrationPage.getRegistrationPageTitle();
		Assert.assertEquals(pageTitle, Constants.REGISTER_PAGE_TITLE);
	}
	
	
	@DataProvider
	public Object[][] getRegistrationData()
	{
		Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		
		return data;
	}
	
	@Test(dataProvider = "getRegistrationData")
	public void registrationTest(String fname,String lname,String email,String telph,String pwd,String c_pwd)
	{
		Assert.assertTrue(registrationPage.doRegistration(fname,lname,email,telph,pwd,c_pwd));
	}
}
