package com.webtek.modules;

import com.webtek.helper.Log;
import com.webtek.pages.LogOutPage;

public class SignOutAction {

	public static void execute() {
		LogOutPage.signOut.click();
		Log.info("Logout form the application");
	}

}
