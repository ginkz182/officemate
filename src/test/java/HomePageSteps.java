
import com.thoughtworks.gauge.Step;
import pages.HomePage;


public class HomePageSteps {

    HomePage homePage = new HomePage();
    @Step("Given I am on Home Page")
    public void goToHomepage() throws InterruptedException {
        homePage.navigateToHomepage();
    }

}
