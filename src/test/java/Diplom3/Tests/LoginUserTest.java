package Diplom3.Tests;

import Diplom3.POM.*;
import Diplom3.client.UserApi;
import Diplom3.model.User;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static Diplom3.POM.HeaderPageBurger.BASE_URI;
import static Diplom3.TestData.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginUserTest {

    User user;
    UserApi userApi = new UserApi();

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
        user = new User(USER_EMAIL, USER_PASSWORD, USER_NAME);
        Configuration.browserSize = "1920x1080";
    }
    @After
    public void tearDown() {
        ValidatableResponse loginResponse = userApi.loginUser(user);
        String accessToken =loginResponse.extract().path("accessToken");
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }
    @Test
    @DisplayName("Проверка логина через кнопку \"Войти в аккаунт\"")
    @Description("Кнопка \"Войти в аккаунт\" находится на главной странице приложения")
    public void loginUser_FromHomePage() {
        ValidatableResponse registerResponse = userApi.createUser(user);

        HomePageBurger homePage = open(BASE_URI, HomePageBurger.class);
        homePage.clickLogToAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.login(USER_EMAIL, USER_PASSWORD);

        assertTrue(homePage.check_MakeOrderButton());
    }

    @Test
    @DisplayName("Проверка логина через кнопку \"Личный кабинет\"")
    @Description("Кнопка \"Личный кабинет\" находится в Хедере")
    public void loginUser_FromPersonalAccount() {
        ValidatableResponse registerResponse = userApi.createUser(user);

        HeaderPageBurger headerPage = open(BASE_URI, HeaderPageBurger.class);
        headerPage.clickPersonalAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        headerPage.clickPersonalAccountButton();

        ProfilePageBurger profilePage = page(ProfilePageBurger.class);
        Selenide.sleep(1000);

        assertTrue(profilePage.getOrderHistoryTab().isEnabled());
    }
    @Test
    @DisplayName("Проверка логина через ссылку в Форме регистрации")
    public void loginUser_FromRegisterPage() {
        ValidatableResponse registerResponse = userApi.createUser(user);

        HomePageBurger homePage = open(BASE_URI, HomePageBurger.class);
        homePage.clickLogToAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.clickRegisterLink();

        RegisterPageBurger registerPage = page(RegisterPageBurger.class);
        registerPage.clickLoginLink();

        loginPage.login(USER_EMAIL, USER_PASSWORD);

        assertTrue(homePage.check_MakeOrderButton());
    }

    @Test
    @DisplayName("Проверка логина через ссылку в Форме восстановления пароля")
    public void loginUser_FromForgotPage() throws InterruptedException {
        ValidatableResponse registerResponse = userApi.createUser(user);

        HomePageBurger homePage = open(BASE_URI, HomePageBurger.class);
        homePage.clickLogToAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPageBurger forgotPasswordPage = page(ForgotPasswordPageBurger.class);
        forgotPasswordPage.clickLoginLink();

        loginPage.login(USER_EMAIL, USER_PASSWORD);

        assertTrue(homePage.check_MakeOrderButton());
    }

    @Test
    @DisplayName("Проверка логаута по кнопке \"Выйти\" в Личном кабинете")
    public void logOutUser_test() {
        ValidatableResponse registerResponse = userApi.createUser(user);

        HeaderPageBurger headerPage = open(BASE_URI, HeaderPageBurger.class);
        headerPage.clickPersonalAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        headerPage.clickPersonalAccountButton();

        ProfilePageBurger profilePage = page(ProfilePageBurger.class);
        profilePage.clickLogOutButton();
        Selenide.sleep(500);

        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = BASE_URI+ "login";

        assertEquals(expectedUrl, actualUrl);
    }



}
