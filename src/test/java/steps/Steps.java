package steps;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import utils.CommonMethods;
import utils.ReadingPropertiesFile;
import org.json.simple.JSONObject;

import java.util.List;

public class Steps {

	ReadingPropertiesFile data = CommonMethods.Call_ReadPropFile();
	utils.LogInFile Logger = new utils.LogInFile(Steps.class);

	int randomUserID=0;
	Response response;
	int temp = 0;

	@Given("Get the random userID")
	public void Get_the_random_userID () throws Throwable {
		randomUserID = new CommonMethods().GenRandomeNum();
		assertTrue(randomUserID >=1);
		assertTrue(randomUserID <=10);
		System.out.println("Get_the_random_userID");

	}
	
	
	@And("users API endpoint is exists")
	public void users_API_endpoint_is_exists () throws Throwable {
		response = new CommonMethods().doGetRequest(data.GetPrintUserEmailURL());
		String STATUS_CODE = String.valueOf(response.getStatusCode());
		Assert.assertEquals(STATUS_CODE, "200");
		
		response = null;
		System.out.println("users_API_endpoint_is_exists");

	}
	
	@When("I print a user Email")
	public void I_print_a_user_Email() throws Throwable {
		try {

			Logger.WriteLog(
					" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":TC01_printUserEmail");

			response = new CommonMethods().doGetRequest(data.GetPrintUserEmailURL() + randomUserID);
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":response node: "+ data.GetPrintUserEmailURL() + randomUserID);

			String Email = response.jsonPath().getString(data.GetprintValue());
			System.out.println("The User Email is: " + Email + "\n------------------");
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "The User Email is: "
					+ Email + "\n------------------");

			response = null;

		} catch (Exception e) {
			e.printStackTrace();
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n", e);
		}
		System.out.println("I_print_a_user_Email");

	}

	@And("Verify a user posts ID between 1and100")
	public void Verify_a_user_posts_ID_between_1and100() throws Throwable {
		try {

			Logger.WriteLog(
					" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":TC02_GetUserPosts");

			response = new CommonMethods().doGetRequest(data.GetUserPostsURL() + randomUserID);
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":response node: "
					+ data.GetUserPostsURL() + randomUserID);

			List<Integer> jsonResponse = response.jsonPath().getList(data.GetcheckPostValue());
			System.out.println("The user posts list size: " + jsonResponse.size());
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ ":The user posts list size: " + jsonResponse.size());

			for (int test : jsonResponse) {
				assertTrue(
						test >= Integer.parseInt(data.GetMinValue()) && test <= Integer.parseInt(data.GetMaxValue()));
				// boolean check= Test>=1 && Test=<100;

				if (test > temp)
					temp = test;

				// for Tester
				System.out.println("list value is: " + test);
				Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
						+ ":list value is: " + test);
			}

			System.out.println("All user posts ids in 1 - 100");
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ ":All user posts ids in 1 - 100");
			response = null;

		} catch (Exception e) {
			e.printStackTrace();
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n", e);
		}
		
		System.out.println("Verify_a_user_posts_ID_between_1and100");

	}

	
	@Then("Add new Post using same userID")
	public void Add_new_Post_using_same_userID() throws Throwable {
		try {

			Logger.WriteLog(
					" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":TC02_GetUserPosts");
			JSONObject request = new JSONObject();

			request.put("userId", randomUserID);
			request.put("Id", temp + 1);
			request.put("title", "New Post Title");
			request.put("body", "New Post body for API Task");
			System.out.println("The new post parameter is: " + request.toJSONString());
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ ":The new post parameter is: " + request.toJSONString());

			given().body(request.toJSONString()).when().post(data.GetAddPostURL()).then().statusCode(201);
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ ":The new post done successfully");

		} catch (Exception e) {
			e.printStackTrace();
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n", e);
		}
		System.out.println("Add_new_Post_using_same_userID");

	}

}
