package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

public class GetStepDefinitions {

    private Response response;
    private String endpoint;

    @Given("the baseURL is {string}")
    public void theBaseURLIs(String url) {
        RestAssured.baseURI=url;
    }

    @Given("the {string} endpoint is available")
    public void theEndpointIsAvailable(String endpoint) {
        this.endpoint = endpoint;
    }

    @When("the user send a GET request")
    public void theUserSendAGETRequest() {
      response = RestAssured.given()
              .when()
              .get(endpoint)
              .then()
              .extract()
              .response();
    }

    @Then("the response code is {int}")
    public void theResponseCodeIs(int responseCode) {
        assertEquals(responseCode, response.getStatusCode());
    }

    @Then("the response includes:")
    public void theResponseIncludes(DataTable dataTable) {
        Map<String, Objects> jsonMap = response.jsonPath().getMap("$");

        for (String data : dataTable.asList()){
            assertTrue(jsonMap.containsKey(data));
        }
    }

    @Then("{string} is equals with {string}")
    public void isEqualsWith(String field, String value) {
       Map<String, Object> jsonMap = response.jsonPath().getMap("$");
       assertEquals(value, jsonMap.get(field));
    }

    @And("the data contains:")
    public void theContains(DataTable dataTable) {
       Map<String, Object> jsonMap = response.jsonPath().getMap("$");

        Map<String, Object> dataMap = (Map<String, Object>) jsonMap.get("data");

        System.out.println("kiiros---- " + dataTable.cells());
        for (List<String> row : dataTable.cells()) {
            String key = row.get(0);      // 1. oszlop = kulcs
            String value = row.get(1);    // 2. oszlop = elvárt érték

            assertTrue("Missing field in data: " + key, dataMap.containsKey(key));
            assertEquals(value, String.valueOf(dataMap.get(key)));
        }




    }

    @And("the data is null")
    public void theDataIsNull() {
        Map<String, Object> jsonMap = response.jsonPath().getMap("$");
        assertNull(jsonMap.get("data"));
    }
}
