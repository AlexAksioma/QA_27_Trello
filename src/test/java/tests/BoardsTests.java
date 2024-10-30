package tests;

import dto.BoardDto;
import dto.UserDto;
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

    @BeforeMethod
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


    @Test
    public void addNewBoardPositiveTest() {
        new BoardsPage(getDriver()).createNewBoard(BoardDto.builder()
                .boardName("name_board" + new Random().nextInt(1000))
                .build());
    }
}
