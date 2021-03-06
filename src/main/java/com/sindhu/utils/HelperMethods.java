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

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.regex.*;

public class HelperMethods {

	public static String searchUser(String searchUserName) throws MalformedURLException {
		/*
		 * Search By User Method to find a particular user given the userName as input
		 * argument from the '/users' API response content
		 */

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users");

		int code = response.getStatusCode();
		System.out.println("Search User : Status Code:" + code);

		String responseString = response.asString();

		List<Object> userList = com.jayway.jsonpath.JsonPath.parse(responseString)
				.read("$.[?(@.username=='" + searchUserName + "')].username");

		if (userList == null || userList.isEmpty()) {
			return null;
		}

		return (String) userList.get(0);
	}

	public static int getUserId(String userName) throws MalformedURLException {
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

		String responseString = response.body().asString();

		// Fetching the UserId of a particular userName as given parameter

		System.out.println("userName === " + userName);

		List<Object> filteredIds = com.jayway.jsonpath.JsonPath.parse(responseString)
				.read("$[?(@.username == '" + userName + "')].id");

		if (filteredIds == null || filteredIds.isEmpty()) {
			return -1;
		}

		Integer id = (Integer) filteredIds.get(0);

		System.out.println("HelperMethods -> Fetched User ID = " + id);

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

	public static ArrayList<String> getComments(Integer[] postId) throws MalformedURLException {
		/*
		 * Get the List of Comments for a particular user's posts given their postIds as
		 * input argument from the '/comments' API response content
		 */

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/comments");

		int code = response.getStatusCode();
		System.out.println("Status Code:" + code);
		// Assert.assertEquals(code,200);

		String responseString = response.asString();
		ArrayList<String> postsList = new ArrayList<String>();

		for (int pIds : postId) {
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
		Assert.assertEquals(code, 200);

		String responseString = response.asString();
		ArrayList<String> emailList = new ArrayList<String>();

		for (int pIds : PostId) {
			List<Object> fetchEmaiAddress = com.jayway.jsonpath.JsonPath.parse(responseString)
					.read("$[?(@.postId == " + pIds + ")].email");
			String postedEmail = fetchEmaiAddress.toString();
			System.out.println("-------------Email Addresses of the PostID# " + pIds + "----------" + postedEmail);

			emailList.add(postedEmail);
		}
		return emailList;
	}

	public static ArrayList<String> getEmailAdressesByPostId(int PostId) throws MalformedURLException {
		/*
		 * Get the List of Email Addresses for a particular user's posts given their
		 * postIds as input argument from the '/comments' API response content
		 */

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/comments");

		int code = response.getStatusCode();
		System.out.println("Status Code:" + code);
		Assert.assertEquals(code, 200);

		String responseString = response.asString();
		ArrayList<String> emailList = new ArrayList<String>();

		List<Object> fetchEmaiAddress = com.jayway.jsonpath.JsonPath.parse(responseString)
				.read("$[?(@.postId == " + PostId + ")].email");

		for (Object object : fetchEmaiAddress) {
			emailList.add((String) object);
		}

		return emailList;
	}

	public static void displayStringList(ArrayList<String> dataContents) throws MalformedURLException {
		Iterator<String> iter = dataContents.iterator();
		while (iter.hasNext()) {
			System.out.println("------Comments data ------" + iter.next());
		}
	}

	public static boolean isValidEmailAddress(ArrayList<String> email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		boolean emailValidationResult = true;
		Pattern pattern = Pattern.compile(emailRegex);

		Object[] objects = email.toArray();

		// Printing array of objects

		int len = objects.length;
		System.out.println("Object Length is:" + len);

		for (Object emailID : email) {
			System.out.println("Object Value is:" + emailID);
			emailValidationResult = pattern.matcher((CharSequence) emailID).matches();
			if (!emailValidationResult) {
				return false;
			}
		}

		return emailValidationResult;
	}

	public static void main(String[] args) throws MalformedURLException {

		String UserName = "Samantha";
		System.out.println("UserName====>" + UserName);
		String searchResult = searchUser(UserName);
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
		boolean validEmailList = isValidEmailAddress(fetchListOfEmails);

	}

}
