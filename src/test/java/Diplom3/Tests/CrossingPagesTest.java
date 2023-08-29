package Diplom3.Tests;

import Diplom3.POM.HeaderPageBurger;
import Diplom3.POM.LoginPageBurger;
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

public class CrossingPagesTest {
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
    @DisplayName("Прооверка перехода в Личный кабинет")
    @Description("Переход в Личный кабинет при нажатии в хедере")
    public void crossToPersonalAccount() {
        ValidatableResponse registerResponse = userApi.createUser(user);

        HeaderPageBurger headerPage = open(BASE_URI, HeaderPageBurger.class);
        headerPage.clickPersonalAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        headerPage.clickPersonalAccountButton();
        Selenide.sleep(500);

        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = BASE_URI+ "account/profile";

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Прооверка перехода из Личного кабинета в Конструктор")
    @Description("Переход в Личный кабинет при нажатии в хедере и следом - переход в Конструктор")
    public void crossToConstructor_From_PersonalAccount_test() {
        ValidatableResponse registerResponse = userApi.createUser(user);

        HeaderPageBurger headerPage = open(BASE_URI, HeaderPageBurger.class);
        headerPage.clickPersonalAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        headerPage.clickPersonalAccountButton();
        headerPage.clickConstructorButton();

        Selenide.sleep(500);

        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = BASE_URI;

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Прооверка перехода из Личного кабинета на главную страницу (путем клика на логотип Бургера")
    @Description("Переход в Личный кабинет при нажатии в хедере и следом - переход на главную страницу (Конструктор)")
    public void crossToHomePage_From_PersonalAccount_test() {
        HeaderPageBurger headerPage = open(BASE_URI, HeaderPageBurger.class);
        headerPage.clickPersonalAccountButton();
        headerPage.clickToBurgerLogo();

        Selenide.sleep(300);

        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = BASE_URI;

        assertEquals(expectedUrl, actualUrl);
    }
}