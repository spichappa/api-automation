/*POSITIVE TESTCASES
 * This is the first suite to validate backend api calls for the below Postive Usecases
 * 1) Before Test Check : /users apiMethod status code
 * 2) Before Test Check : /users apiMethod status posts
 * 3) Before Test Check : /users apiMethod status comments
 * 4) Search for the userName
 * 5) Fetch the userID given the userName
 * 6) Fetch the postID given the userName & inside logic by userID
 * 7) Fetch the postedComments given the list of PostID for a particular user
 * 8) Fetch the emailAddresses given the list of PostID for a particular user
 * 9) Fetch the emailAddresses for a given PostID of an user
 */
package com.sindhu.apitests;

import com.sindhu.utils.HelperMethods;

import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSuite_01 {

	@BeforeTest
	/*
	 * Verify the http response status returned. Check Status Code is 200? We can
	 * use Rest Assured library's response's getStatusCode method
	 */
//	public static void checkStatusIs200(Response res) {
//		Assert.assertEquals(200, res.getStatusCode(), "Status Check Success!");
//	}

	/*
	 * Test01
	 */
	@Test
	public void T01_searchForUserName() throws MalformedURLException {
		String userName = "Samantha";
		System.out.println("Search by UserName====>" + userName);
		String searchResult = HelperMethods.searchUser(userName).toString();

		System.out.println("Search for the user " + searchResult + ": is found!!!");

		// Verify the response contained the relevant searched username
//        Assert.assertEquals( searchResult,userName);
	}

	@Test
	public void T02_searchForUserId() throws MalformedURLException {

		String UserName = "Samantha";
		int userId = HelperMethods.getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Exists");
		System.out.println(userId);
	}

	@Test
	public void T03_searchForUserId() throws MalformedURLException {

		String UserName = "InvalidUser";
		int userId = HelperMethods.getUserId(UserName);
//		Assert.assertNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}

	@Test
	public void T04_searchForUserId() throws MalformedURLException {
		int userId = HelperMethods.getUserId("Samantha");
		Assert.assertNotNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}

	@Test
	public void T05_fetchCommentsByUserName() throws MalformedURLException {

		String userName = "Samantha";
		int userId = HelperMethods.getUserId(userName);
		Integer[] postId = HelperMethods.getPostId(userId);
		Assert.assertNotNull(HelperMethods.getComments(postId), "Comments are Listed Out for the User");
	}

	@Test
	public void T06_fetchEmailsByPostIds() throws MalformedURLException {

		String userName = "Samantha";
		int userId = HelperMethods.getUserId(userName);
		Integer[] postId = HelperMethods.getPostId(userId);
		Assert.assertNotNull(HelperMethods.getEmailAdresses(postId), "Email Addresses are Listed Out for the User");
	}

	@Test
	public void T07_fetchEmailsByPostId() throws MalformedURLException {

		String userName = "Samantha";
		int userId = HelperMethods.getUserId(userName);
//		Integer[] postId = HelperMethods.getPostId(userId);
		int postId = 21;
		ArrayList<String> emailList = HelperMethods.getEmailAdressesByPostId(postId);
		Assert.assertNotNull(emailList, "Email Addresses are Listed Out for the User");
		boolean isValidEmailList = HelperMethods.isValidEmailAddress(emailList);
		Assert.assertTrue(isValidEmailList, "Emails in the list are valid");

	}
}
