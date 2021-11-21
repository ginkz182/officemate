package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchBarComponent extends BasePage {
    
    @FindBy(css = "[data-testid='txt-SearchBar']")
    WebElement searchBar;

    @FindBy(id = "btn-searchResultPage")
    WebElement searchIcon;

    public SearchBarComponent() {
        PageFactory.initElements(getDriver(), this);
    }

    public void typeSearchBar(String text) {
        searchBar.clear();
        searchBar.sendKeys(text);
    }

    public void clickSearch() {
        searchIcon.click();
    }

}
