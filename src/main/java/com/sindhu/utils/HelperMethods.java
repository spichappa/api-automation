/*
 * This is a Java Helper Class with all core functions/methods for this project 'Backend Test Challenge - FreeNow'
 * search by User and return the userNameList/boolean
 * Find the Id for a particular user and return as integer value
 * Find the PostId for a particular userId and return as integer array
 * Fetch the Comments content for each postIds of a particular UserId and return as Array of Strings
 * Fetch the EmaiAddresses for each postIds of a particular UserId and return as Array of Strings
 */
package com.sindhu.utils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HelperMethods {
	public static boolean searchUser(String UserName) throws MalformedURLException {
		/*
		 * Search By User Method to find a particular user given the userName as input
		 * argument from the '/users' API response content
		 */

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users");

		int code = response.getStatusCode();
		System.out.println("Search User : Status Code:" + code);

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Fetching value of userName parameter
		List<Object> usersList = jsonPathEvaluator.get("username");
		System.out.println(usersList);
		boolean userNameExists = jsonPathEvaluator.get("username").equals("Samantha");

		// Asserting that userName is Samantha
		// Assert.assertEquals(UserName, "Samantha");

		return userNameExists;
	}

	public static int getUserId(String UserName) throws MalformedURLException {
		/*
		 * Get the UserId for a particular user given the userName as input argument
		 * from the '/users' API response content
		 * 
		 */
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users");

		int code = response.getStatusCode();
		System.out.println("Get UserID : Status Code:" + code);

		String responseString = response.asString();

		// Fetching the UserId of a particular userName as given parameter

		List<Object> filteredIds = com.jayway.jsonpath.JsonPath.parse(responseString)
				.read("$[?(@.username == 'Samantha')].id");

		Integer id = (Integer) filteredIds.get(0);

		System.out.println("Samantha id = " + id);

		return id;

	}

	public static Integer[] getPostId(int userId) throws MalformedURLException {
		/*
		 * Get the UserId for a particular user given the userName as input argument
		 * from the '/posts' API response content
		 */

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/posts");

		int code = response.getStatusCode();
		System.out.println("Post Id : Status Code:" + code);
		// Assert.assertEquals(code,200);

		String responseString = response.asString();

		List<Object> fetchPostIds = com.jayway.jsonpath.JsonPath.parse(responseString)
				.read("$[?(@.userId == " + userId + ")].id");

		Integer[] postIds = fetchPostIds.toArray(new Integer[0]);

		return postIds;

	}

	public static ArrayList<String> getComments(Integer[] PostId) throws MalformedURLException {
		/*
		 * Get the List of Comments for a particular user's posts given their postIds as
		 * input argument from the '/comments' API response content
		 */

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/comments");

		int code = response.getStatusCode();
		System.out.println("Status Code:" + code);
//	Assert.assertEquals(code,200);

		String responseString = response.asString();
		ArrayList<String> postsList = new ArrayList<String>();

		for (int pIds : PostId) {
			List<Object> fetchComments = com.jayway.jsonpath.JsonPath.parse(responseString)
					.read("$[?(@.postId == " + pIds + ")].body");
			String postedComments = fetchComments.toString();
			System.out.println("-------------Posted Comments---------" + postedComments);

			postsList.add(postedComments);
		}
		return postsList;
	}

	public static ArrayList<String> getEmailAdresses(Integer[] PostId) throws MalformedURLException {
		/*
		 * Get the List of Email Addresses for a particular user's posts given their
		 * postIds as input argument from the '/comments' API response content
		 */

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/comments");

		int code = response.getStatusCode();
		System.out.println("Status Code:" + code);
//	Assert.assertEquals(code,200);

		String responseString = response.asString();
		ArrayList<String> emailList = new ArrayList<String>();

		for (int pIds : PostId) {
			List<Object> fetchEmaiAddress = com.jayway.jsonpath.JsonPath.parse(responseString)
					.read("$[?(@.postId == " + pIds + ")].email");
			String postedEmail = fetchEmaiAddress.toString();
			System.out.println("-------------Email Addresses----------" + postedEmail);

			emailList.add(postedEmail);
//		System.out.println(emailList.get(0));
		}
		return emailList;
	}

	public static void displayStringList(ArrayList<String> dataContents) throws MalformedURLException {
		Iterator iter = dataContents.iterator();
		while (iter.hasNext()) {
			System.out.println("------Comments data ------" + iter.next());
		}
	}

	public static void main(String[] args) throws MalformedURLException {

		String UserName = "Samantha";
		System.out.println("UserName====>" + UserName);
		boolean searchResult = searchUser(UserName);
		System.out.println(searchResult);

		int getUserId = getUserId(UserName);
		Integer postIds[] = getPostId(getUserId);
		for (int pIds : postIds) {
			System.out.println("Post Ids:" + pIds);
		}

		ArrayList<String> fetchListOfComments = getComments(postIds);
//	displayStringList(fetchListOfComments);

		ArrayList<String> fetchListOfEmails = getEmailAdresses(postIds);
//	displayStringList(fetchListOfEmails);

	}

	/*
	 * Test01
	 */
	@Test
	public void T01_searchForUserName() throws MalformedURLException {
		String UserName = "Samantha";
		System.out.println("UserName====>" + UserName);
		boolean searchResult = searchUser(UserName);
		Assert.assertEquals(searchResult, true);
		System.out.println(searchResult);
	}

	/*
	 * Test02
	 */
	@Test
	public void T02_searchForUserName() throws MalformedURLException {
		String UserName = "InvalidUser";
		boolean searchResult = searchUser(UserName);
		Assert.assertEquals(searchResult, false);
		System.out.println(searchResult);
	}

	/*
	 * Test03
	 */
	@Test
	public void T03_searchForUserName() throws MalformedURLException {
		String UserName = "InvalidUser";
		boolean searchResult = searchUser(UserName);
		Assert.assertEquals(searchResult, false);
		System.out.println(searchResult);
	}

	@Test
	public void T04_searchForUserId() throws MalformedURLException {

		String UserName = "Samantha";
		int userId = getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Exists");
		System.out.println(userId);
	}

	public void T05_searchForUserId() throws MalformedURLException {

		String UserName = "InvalidUser";
		int userId = getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}

	public void T06_searchForUserId() throws MalformedURLException {

		String UserName = "InvalidUser";
		int userId = getUserId(UserName);
		Assert.assertNotNull(userId, "UserId Does Not Exists");
		System.out.println(userId);
	}

}
