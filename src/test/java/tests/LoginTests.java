package tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import data_providers.DataProviderUser;
import dto.UserDto;
import helpers.TestNGListener;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;

import java.lang.reflect.Method;
@Listeners(TestNGListener.class)

public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTest(Method method) {
        UserDto user = UserDto.builder()
                .email("alexmedqwerty2@gmail.com")
                .password("376Vtl150dtl!")
                .build();
        logger.info("start method --> " + method.getName() + " with data: " + user);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        new LoginPage(getDriver()).typeLoginForm(user);
        Assert.assertTrue(new BoardsPage(getDriver()).validateUrlBoards());
    }

    @Test(dataProvider = "loginTestDataProvider", dataProviderClass = DataProviderUser.class)
    public void loginPositiveTest_withDP(UserDto user, Method method) {
        logger.info("start method --> " + method.getName() + " with data: " + user);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        new LoginPage(getDriver()).typeLoginForm(user);
        Assert.assertTrue(new BoardsPage(getDriver()).validateUrlBoards());
    }

    @Test(dataProvider = "loginTestDataProviderFromCswFile", dataProviderClass = DataProviderUser.class)
    public void loginPositiveTest_withDPFromCsw(UserDto user, Method method) {
        logger.info("start method --> " + method.getName() + " with data: " + user);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        new LoginPage(getDriver()).typeLoginForm(user);
        Assert.assertTrue(new BoardsPage(getDriver()).validateUrlBoards());
    }

    @Test
    public void loginPositiveTestChain() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginChain();
    }
}
