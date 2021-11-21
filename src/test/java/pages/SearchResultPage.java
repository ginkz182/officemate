package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage {

    @FindBy(css = "[data-testid='pnl-productGrid']")
    WebElement productResultGrid;

    @FindBy(css = "[data-testid='pnl-ListPage']")
    WebElement searchResultDiv;

    public SearchResultPage() {
        PageFactory.initElements(getDriver(), this);
    }
    
    public void verifyProductResultText(String text) {
        Assert.assertTrue("Search result doesn't contain search keyword: " + text,
            productResultGrid.getText().toLowerCase().contains(text.toLowerCase()));
    }

    public void waitSearchResultLoad() {
        waitForElementDisappear(By.cssSelector("[data-testid='pnl-productGrid-loading']"));
        waitForElementVisible(By.cssSelector("[data-testid='pnl-productPreview']"));
    }

    public void verifySearchResultText(String expected) {
        Assert.assertTrue("Search result text not match",
            searchResultDiv.getText().contains(expected));       
    }
}
