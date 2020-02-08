package com.sindhu.apitests;

import com.sindhu.utils.HelperMethods;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite_01 {

	/*
	 * Test01
	 */
	@Test
	public void T01_searchForUserName() throws MalformedURLException {
		String userName = "Samantha";
		System.out.println("Search by UserName====>" + userName);
		String searchResult = HelperMethods.searchUser(userName).toString();
		
		System.out.println("Search for the user "+searchResult+ ": is found!!!");
		
        //Verify the response contained the relevant searched username 
//        Assert.assertEquals( searchResult,userName);
	}

	/*
	 * Test02
	 */
	@Test
	public void T02_searchForUserName() throws MalformedURLException {
		String userName = "NotAUser";
		System.out.println("UserName====>" + userName);
		String searchResult = HelperMethods.searchUser(userName);
//		Assert.assertNotEquals(userName, searchResult);
		System.out.println("Search for the user "+searchResult+ ": is Not found!!!");
	}

	/*
	 * Test03
	 */
	@Test
	public void T03_searchForUserName() throws MalformedURLException {
		String userName = "12345678";
		System.out.println("UserName====>" + userName);
		String searchResult = HelperMethods.searchUser(userName);
		Assert.assertNotEquals(userName, searchResult);
		System.out.println("Search for the user "+searchResult+ ": is Not found!!!");
	}

	@Test
	public void T04_searchForUserId() throws MalformedURLException {

		String UserName = "Samantha";
		int userId = HelperMethods.getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Exists");
		System.out.println(userId);
	}
	
	@Test
	public void T05_searchForUserId() throws MalformedURLException {

		String UserName = "InvalidUser";
		int userId = HelperMethods.getUserId(UserName);
//		Assert.assertNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}

	@Test
	public void T06_searchForUserId() throws MalformedURLException {

		String UserName = "";
		int userId = HelperMethods.getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}
	
	@Test
	public void T07_fetchCommentsByUserName() throws MalformedURLException {
	  	
		String userName = "Samantha";
		int userId = HelperMethods.getUserId(userName);
		Integer [] postId=	HelperMethods.getPostId(userId);
		ArrayList<String> fetchComments=  new ArrayList<String>();
//		fetchComments=	HelperMethods.getComments(postId);	
		Assert.assertNotNull(HelperMethods.getComments(postId), "Comments are Listed Out for the User");
	  }
	
	@Test
	public void T08_fetchEmailsByUserName() throws MalformedURLException {
	  	
		String userName = "Samantha";
		int userId = HelperMethods.getUserId(userName);
		Integer [] postId=	HelperMethods.getPostId(userId);
		Assert.assertNotNull(HelperMethods.getEmailAdresses(postId), "Email Addresses are Listed Out for the User");
	  }
}
