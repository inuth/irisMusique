package be.restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import be.DTO.music.MusicPostDTO;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestTest {
	
	@Test
	public void test1() {
		
		//RequestSpecification rs = given();
		given()
		.pathParam("pseudo", "Albert")
		.when()
		.get("http://localhost:8082/autor/search/{pseudo}")
		.then()
		.assertThat()
		.statusCode(200)
		.body("id", equalTo(1));
	}
	
	@Test
	public void getAllFiltered_v2() {
		
		
		given()
		.param("title", "Nothing")
		.param("autorName", "Albert")
		.when()
		.get("http://localhost:8082/music/v2")
		.then()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		//.log()
		.body("[0].title", equalTo("Nothing else matters"));
	}
	
	@Test
	public void postMusic() throws JSONException {
		JSONObject jo = new JSONObject();
		jo.put("title", "nouvelle musique");
		jo.put("duration", 300);
		jo.put("autorId", 1);
		
		MusicPostDTO mpd = new MusicPostDTO();
		mpd.setAutorId(1);
		mpd.setDuration(300);
		mpd.setTitle("nouvelle musique");
		
		given()
		.contentType(ContentType.JSON)
		.body(mpd)
		//.body(jo.toString())
		.when()
		.post("http://localhost:8082/music")
		.then()
		.assertThat()
		.statusCode(200)
		.body("autorName", equalTo("Albert"));
	}
	
	@Test
	public void storeValue() {
		MusicPostDTO mpd = new MusicPostDTO();
		mpd.setAutorId(1);
		mpd.setDuration(300);
		mpd.setTitle("nouvelle musique");
		
		String autorName = 
		given()
		.contentType(ContentType.JSON)
		.body(mpd)
		//.body(jo.toString())
		.when()
		.post("http://localhost:8082/music")
		.then()
		.extract()
		.path("autorName");
		
		given()
		.pathParam("pseudo", autorName)
		
		.when()
		.get("http://localhost:8082/autor/search/{pseudo}")
		.then()
		.assertThat()
		.statusCode(200)
		.body("id", equalTo(1));
	}
	
	
	
	
}
