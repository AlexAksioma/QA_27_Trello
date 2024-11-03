package tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import data_providers.DataProviderUser;
import dto.UserDto;
import helpers.TestNGListener;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
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

    @Description("login positive test")
    @Owner("QA Alex")
    @Test(groups = {"smoke","positive"})
    public void loginPositiveTest(Method method) {
        UserDto user = UserDto.builder()
                .email("alexmedqwerty2@gmail.com")
                .password("376Vtl150dtl!")
                .build();
        Allure.step("create user --> "+user.getEmail());
        logger.info("start method --> " + method.getName() + " with data: " + user);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        new LoginPage(getDriver()).typeLoginForm(user);
        Allure.step("start asser true");
        Assert.assertTrue(new BoardsPage(getDriver()).validateUrlBoards());
    }

    @Test(groups = "positive",
            dataProvider = "loginTestDataProvider", dataProviderClass = DataProviderUser.class)
    public void loginPositiveTest_withDP(UserDto user, Method method) {
        logger.info("start method --> " + method.getName() + " with data: " + user);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        new LoginPage(getDriver()).typeLoginForm(user);
        Assert.assertTrue(new BoardsPage(getDriver()).validateUrlBoards());
    }

    @Test(groups = "positive",
            dataProvider = "loginTestDataProviderFromCswFile", dataProviderClass = DataProviderUser.class)
    public void loginPositiveTest_withDPFromCsw(UserDto user, Method method) {
        logger.info("start method --> " + method.getName() + " with data: " + user);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        new LoginPage(getDriver()).typeLoginForm(user);
        Assert.assertTrue(new BoardsPage(getDriver()).validateUrlBoards());
    }

    @Test(groups = {"smoke", "regres"})
    public void loginPositiveTestChain() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginChain();
    }
}
