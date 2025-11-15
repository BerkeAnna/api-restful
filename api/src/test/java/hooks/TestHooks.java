package hooks;

import config.TestConfig;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class TestHooks {

    @Before
    public void setup() {
      //  RestAssured.baseURI = TestConfig.get("baseUrl");
    }
}
