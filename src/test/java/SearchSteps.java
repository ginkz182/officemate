import com.thoughtworks.gauge.Step;

import org.json.JSONObject;

import api.ProductsSearchAPI;
import io.restassured.response.Response;
import pages.SearchBarComponent;
import pages.SearchResultPage;

public class SearchSteps {
   
    SearchBarComponent searchBar = new SearchBarComponent();
    SearchResultPage searchResultPage = new SearchResultPage();
    ProductsSearchAPI productsSearchAPI = new ProductsSearchAPI();
    
    Response result; 
    String searchKeyword = "";

    @Step("When I search with keyword <keyword>")
    public void searchWithKeyword(String keyword) {
        searchBar.typeSearchBar(keyword);
        searchBar.clickSearch();
        searchKeyword = keyword;
        searchResultPage.waitSearchResultLoad();
    }

    @Step("Then I should see correct search result")
    public void verifySearchResult() {
        searchResultPage.verifyProductResultText(searchKeyword);
    }

    @Step("Test cookie")
    public void test() {
        ProductsSearchAPI searchAPI = new ProductsSearchAPI();
        searchAPI.getProductSearchByQuery("paper");
    }

    @Step({"And I get product search result from API by keyword <keyword>",
    "When I get product search result from API by keyword <keyword>"})
    public void getProductResultAPI(String keyword) {
        result = productsSearchAPI.getProductSearchByQuery(keyword);
    }

    @Step("Then I should see correct number of result in search screen")
    public void verifyResultCount() {
        JSONObject resultObj = new JSONObject(result.getBody().asString());
        String count_text = String.format("%,d", resultObj.getJSONObject("products").getInt("total_count"));
        String expected_text =  count_text + " product results found for \"" + searchKeyword + "\"";
        searchResultPage.verifySearchResultText(expected_text);
    }

    @Step("And product search API should return correct response")
    public void verifySearchAPIResponse() {
        productsSearchAPI.verifySearchResponse(result);

    }
}
