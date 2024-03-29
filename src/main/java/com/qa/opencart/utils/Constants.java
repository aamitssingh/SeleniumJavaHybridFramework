package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final int DEAULT_TIME_OUT = 10;
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_VALUE = "account/login"; 
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String REGISTER_PAGE_TITLE = "Register Account";
	public static final String REGISTER_PAGE_WARNING = "Warning: You must agree to the Privacy Policy!";
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "Sheet1";
	public static final int IMAC_IMAGE_COUNT = 3;
	
	public static final List<String> EXP_ACCOUNTS_SECTIONS_LIST = new ArrayList<String>(Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter"));

}
