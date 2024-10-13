package tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTest(){
        UserDto user = UserDto.builder()
                .email("aksiomamedved@gmail.com")
                .password("AlexMed123!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        new LoginPage(getDriver()).typeLoginForm(user);
        Assert.assertTrue(new BoardsPage(getDriver()).validateUrlBoards());
    }

    @Test
    public void loginPositiveTestChain(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginChain();
    }
}
