package tests;

import dto.BoardDto;
import dto.UserDto;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import manager.ApplicationManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;

import java.lang.reflect.Method;
import java.util.Random;

public class BoardsTests extends ApplicationManager {

    @BeforeMethod(alwaysRun = true)
    public void loginBeforeAddBoard(Method method) {
        UserDto user = UserDto.builder()
                .email("alexmedqwerty2@gmail.com")
                .password("376Vtl150dtl!")
                .build();
        logger.info("start method --> " + method.getName() + " with data: " + user);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        new LoginPage(getDriver()).typeLoginForm(user);
    }

    @Description(" add new board positive test")
    @Owner("QA Alex")
    @Test(groups = {"smoke", "positive"})
    public void addNewBoardPositiveTest() {
        Allure.step("start new board test ");
        new BoardsPage(getDriver()).createNewBoard(BoardDto.builder()
                .boardName("name_board" + new Random().nextInt(1000))
                .build());
        Allure.step("stop new board test");
    }

    @Test(groups = {"regres", "negative"})
    public void addNewBoardNegativeTest_emptyBoardName() {
        new BoardsPage(getDriver()).createNewBoard(BoardDto.builder()
                .boardName("")
                .build());
    }
}
