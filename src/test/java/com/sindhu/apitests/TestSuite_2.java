/*NEGATIVE TESTCASES
 * This is the first suite to validate backend api calls for the below Negative Usecases
 * 1) Before Test Check : /users apiMethod status code is not Equal to 200
 * 2) Before Test Check : /users apiMethod status posts is not Equal to 200
 * 3) Before Test Check : /users apiMethod status comments is not Equal to 200
 * 4) Search for the userName
 * 5) Fetch the userID given the userName
 * 6) Fetch the postID given the userName & inside logic by userID
 * 7) Fetch the postedComments given the list of PostID for a particular user
 * 8) Fetch the emailAddresses given the list of PostID for a particular user
 * 9) Fetch the emailAddresses for a given PostID of an user
 */
package com.sindhu.apitests;

import com.sindhu.utils.HelperMethods;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite_2 {

	/*
	 * Test01
	 */
	@Test
	public void T01_searchForUserName() throws MalformedURLException {
		String userName = "NotAUser";
		System.out.println("UserName====>" + userName);
		String searchResult = HelperMethods.searchUser(userName);
//		Assert.assertNotEquals(userName, searchResult);
		System.out.println("Search for the user " + searchResult + ": is Not found!!!");
	}

	/*
	 * Test02
	 */
	@Test
	public void T02_searchForUserName() throws MalformedURLException {
		String userName = "12345678";
		System.out.println("UserName====>" + userName);
		String searchResult = HelperMethods.searchUser(userName);
		Assert.assertNotEquals(userName, searchResult);
		System.out.println("Search for the user " + searchResult + ": is Not found!!!");
	}

	@Test
	public void T03_searchForUserId() throws MalformedURLException {

		String UserName = "Samantha";
		int userId = HelperMethods.getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Exists");
		System.out.println(userId);
	}

	@Test
	public void T04_searchForUserId() throws MalformedURLException {

		String UserName = "InvalidUser";
		int userId = HelperMethods.getUserId(UserName);
//		Assert.assertNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}

	@Test
	public void T05_searchForUserId() throws MalformedURLException {

		String UserName = "";
		int userId = HelperMethods.getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}

	@Test
	public void T06_fetchCommentsByUserName() throws MalformedURLException {

		String userName = "Samantha";
		int userId = HelperMethods.getUserId(userName);
		Integer[] postId = HelperMethods.getPostId(userId);
		Assert.assertNotNull(HelperMethods.getComments(postId), "Comments are Listed Out for the User");
	}

	@Test
	public void T07_fetchEmailsByPostIds() throws MalformedURLException {

		String userName = "Samantha";
		int userId = HelperMethods.getUserId(userName);
		Integer[] postId = HelperMethods.getPostId(userId);
		Assert.assertNotNull(HelperMethods.getEmailAdresses(postId), "Email Addresses are Listed Out for the User");
	}

	@Test
	public void T08_fetchEmailsByPostId() throws MalformedURLException {

		String userName = "Samantha";
		int userId = HelperMethods.getUserId(userName);
//		Integer[] postId = HelperMethods.getPostId(userId);
		int postId = 21;
		
		Assert.assertNotNull(HelperMethods.getEmailAdressesByPostId(postId),
				"Email Addresses are Listed Out for the User");
		boolean isValidEmailList = HelperMethods.isValidEmailAddress(HelperMethods.getEmailAdressesByPostId(postId));
		Assert.assertTrue(isValidEmailList, "Emails in the list are valid");
	}
}
