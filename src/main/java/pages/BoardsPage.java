package pages;

import dto.BoardDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardsPage extends BasePage{
    public BoardsPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//span[text()='Create new board']")
    WebElement btnCreateNewBoard;

    @FindBy(xpath = "//input[@data-testid='create-board-title-input']")
    WebElement inputBoardName;
    @FindBy(xpath = "//button[@data-testid='create-board-submit-button']")
    WebElement btnCreateBoardSubmit;

    public boolean validateUrlBoards() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("boards"));
    }

    public void createNewBoard(BoardDto board) {
        btnCreateNewBoard.click();
        inputBoardName.sendKeys(board.getBoardName());
        //pause(3);
        //btnCreateBoardSubmit.click();
        clickWait(btnCreateBoardSubmit, 5);
    }
}
