package com.webtek.test;

import java.io.File;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webtek.helper.Excelutils;
import com.webtek.helper.Log;
import com.webtek.modules.SignInAction;
import com.webtek.modules.SignOutAction;
import com.webtek.modules.SignUpAction;
import com.webtek.pages.HomePage;
import com.webtek.pages.LogOutPage;
import com.webtek.pages.LoginPage;
import com.webtek.pages.SignUpPage;

public class SanityTest1 extends SelTestCase {

	@Test(dependsOnMethods = "userSignUp", priority = 2)
	public void signUser() throws InterruptedException {

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, LoginPage.class);

		try {
			SignInAction.execute(null);
			Log.info("SignInAction performed succesfully");
		} catch (Exception e) {
			Log.info("SignInAction dose not perform succesfully");
			e.printStackTrace();
		}

	}

	@Test( dataProvider = "SignUpUser")
	public void userSignUp(String e_mail, String f_Name, String l_Name,
			String pwd, String myf_name, String myl_name,
			String my_company, String myAddressF1, String myAddressF2,
			String my_City, String my_PostCode, String my_Phone,
			String my_AliasAddress) {

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, LoginPage.class);
		PageFactory.initElements(driver, SignUpPage.class);
		PageFactory.initElements(driver, LogOutPage.class);

		try {

			Log.startTestCase("sTestCaseName");
			SignUpAction.execute(e_mail, f_Name, l_Name, pwd, myf_name, myl_name, my_company,
					myAddressF1, myAddressF2, my_City, my_PostCode, my_Phone, my_AliasAddress);

		} catch (Exception e) {
			Log.info("SignUpAction dose not perform successfully");
			e.printStackTrace();
		}

		try {
			SignOutAction.execute();
			Log.info("SignOutAction perform successfully");
		} catch (Exception e) {
			Log.info("SignOutAction dose not perform successfully");
			e.printStackTrace();
		}

	}

	/*
	 * @Test( dataProvider = "SignUpUser") public void userSignUp(String email,
	 * String firstName, String lastName, String password, String myfirstname,
	 * String mylastname, String mycompany, String myAddressField1, String
	 * myAddressField2, String myCity, String myPostCode, String myPhone, String
	 * myAliasAddress) throws InterruptedException {
	 * 
	 * PageFactory.initElements(driver, HomePage.class);
	 * PageFactory.initElements(driver, LoginPage.class);
	 * PageFactory.initElements(driver, SignUpPage.class);
	 * PageFactory.initElements(driver, LogOutPage.class);
	 * 
	 * SignUpAction.execute(email, firstName, lastName, password, myfirstname,
	 * mylastname, mycompany, myAddressField1, myAddressField2, myCity,
	 * myPostCode, myPhone, myAliasAddress); SignOutAction.execute(); }
	 */

	@DataProvider(name = "SignUpUser")
	public Object[][] getNewCompanyData() throws Exception {
		System.out.println(new File(".").getAbsolutePath());
		Object[][] cellData = Excelutils
				.getInstance()
				.readData(
						"E:\\NewReTry\\ObjectWithDataDriven\\src\\test\\resources\\SignUp1.xls",	0);
		                //E:\NewReTry\ObjectWithDataDriven\src\test\resources\SignUp.xls
		System.out.println("MMMMMM : " + cellData);
		return cellData;
	}
}
