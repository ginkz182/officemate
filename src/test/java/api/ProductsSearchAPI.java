package api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import driver.DriverFactory;


public class ProductsSearchAPI {
    
    static final int DEFAULT_LIMIT = 45;
    static final int DEFAULT_PAGE = 1;
    static final int DEFAULT_STORE_ID = 3;
    static final int DEFAULT_PRICE_RANGE = 1;
    static final int DEFAULT_VISIBILITY = 4;

    public Response getProductSearchByQuery(String searchQuery) {
        String endpoint = System.getenv("SEARCH_PRODUCTS_BASE_API");
        
        Set<Cookie> seleniumCookies = DriverFactory.getInstance().getDriver().manage().getCookies();
        Map<String, String> cookies = new HashMap<>();

        for (org.openqa.selenium.Cookie cookie : seleniumCookies) {
            cookies.put(cookie.getName(), cookie.getValue());
        }
  
        Response response;

        response = given()
                .header("accept", "*/*")
                .header("accept-encoding", "gzip, deflate, br")
                .header("connection", "keep-alive")
                .param("searchQuery", searchQuery)
                .param("limit", DEFAULT_LIMIT)
                .param("page", DEFAULT_PAGE)
                .param("store_id", DEFAULT_STORE_ID)
                .param("x_subject_id", cookies.get("guest"))
                .param("price_range", DEFAULT_PRICE_RANGE)
                .param("visibility", DEFAULT_VISIBILITY)
            .when()
                .get(endpoint);

        return response;
    }

    public void verifySearchResponse(Response response) {
        response.then()
            .statusCode(200)
            .body("$", hasKey("products"))
            .body("products", hasKey("total_count"))
            .body("products", hasKey("products"))
            .body("products.products[0]", hasKey("id"))
            .body("products.products[0]", hasKey("type_id"))
            .body("products.products[0]", hasKey("name"))
            .body("products.products[0]", hasKey("brand_name"));
    }

}
