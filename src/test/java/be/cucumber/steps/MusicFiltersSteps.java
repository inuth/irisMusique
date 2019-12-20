package be.cucumber.steps;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;

public class MusicFiltersSteps {
	
	private String path = "";
	private RequestSpecification rs = given();
	private ValidatableResponse vr;
	
	/*
	 * 
	 * Given I give "thing" as "title" in the url
		When I go to "/music/v2"
		Then I validate 
		And I get a music with "1" as "id"
		*/
	
	@Given("I give {string} as {string} in the url")
	public void iGiveInTheUrl(String value, String name) {
		rs.param(name, value);
	}
	
	@When("I go to {string}")
	public void iGoTo(String path) {

		this.path += path;
	}
	
	@Then("I validate")
	public void iValidate() {
		vr = rs.get(path).then().assertThat();
	}
	@Then("I get a statutCode {int}")
	public void iGetAStatutCode(int code) {
		vr.statusCode(code);
	}
	@Then("I get {string} in {string}")
	public void iGetValueStringInPath(String value, String name) {
		vr.body(name, equalTo(value));
	}
	@Then("I get {int} in {string}")
	public void iGetValueIntInPath(int value, String name) {
		vr.body(name, equalTo(value));
	}
	
	
}
