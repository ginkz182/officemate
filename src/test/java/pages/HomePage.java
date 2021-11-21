package pages;

public class HomePage extends BasePage {

    public void navigateToHomepage() {
        getDriver().get(System.getenv("HOME_URL"));
    }
}
