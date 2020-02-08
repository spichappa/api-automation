package com.sindhu.apitests;

import com.sindhu.utils.HelperMethods;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite_01 {

	/*
	 * Test01
	 */
	@Test
	public void T01_searchForUserName() throws MalformedURLException {
		String UserName = "Samantha";
		System.out.println("UserName====>" + UserName);
		boolean searchResult = HelperMethods.searchUser(UserName);
		Assert.assertEquals(searchResult, true);
		System.out.println(searchResult);
	}

	/*
	 * Test02
	 */
	@Test
	public void T02_searchForUserName() throws MalformedURLException {
		String UserName = "InvalidUser";
		boolean searchResult = HelperMethods.searchUser(UserName);
		Assert.assertEquals(searchResult, false);
		System.out.println(searchResult);
	}

	/*
	 * Test03
	 */
	@Test
	public void T03_searchForUserName() throws MalformedURLException {
		String UserName = "InvalidUser";
		boolean searchResult = HelperMethods.searchUser(UserName);
		Assert.assertEquals(searchResult, false);
		System.out.println(searchResult);
	}

	@Test
	public void T04_searchForUserId() throws MalformedURLException {

		String UserName = "Samantha";
		int userId = HelperMethods.getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Exists");
		System.out.println(userId);
	}

	public void T05_searchForUserId() throws MalformedURLException {

		String UserName = "InvalidUser";
		int userId = HelperMethods.getUserId(UserName);
		Assert.assertNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}

	public void T06_searchForUserId() throws MalformedURLException {

		String UserName = "";
		int userId = HelperMethods.getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}
//	
//public void T07_fetchCommentsforUserId() throws MalformedURLException {
//	  	
//		String UserName = "";
//		int userId=	HelperMethods.getUserId(UserName);
//		String []Array = ne
//		Assert.assertNotNull(userId, "UserId Does Not Exists");
//		System.out.println(userId);
//	  }

}
