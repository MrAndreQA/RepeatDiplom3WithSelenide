package Diplom3.Tests;

import Diplom3.POM.HomePageBurger;
import Diplom3.client.UserApi;
import Diplom3.model.User;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static Diplom3.POM.HeaderPageBurger.BASE_URI;
import static Diplom3.TestData.*;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.containsString;

public class ConstructorTest {
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
    @DisplayName("Проверка перехода в раздел \"Булки\"")
    public void crossToBunTest() {
        HomePageBurger homePage = open(BASE_URI, HomePageBurger.class);

        homePage.clickSaucesTab();
        homePage.clickBunsTab();

        Assert.assertThat(homePage.getTextOfBunsAttribute(), containsString("current"));
    }

    @Test
    @DisplayName("Проверка перехода в раздел \"Соусы\"")
    public void crossToSaucesTab() {
        HomePageBurger homePage = open(BASE_URI, HomePageBurger.class);
        homePage.clickSaucesTab();

        Assert.assertThat(homePage.getTextOfSaucesAttribute(), containsString("current"));
    }

    @Test
    @DisplayName("Проверка перехода в раздел \"Начинки\"")
    public void crossToFillingsTab() {
        HomePageBurger homePage = open(BASE_URI, HomePageBurger.class);
        homePage.clickFillingsTab();

        Assert.assertThat(homePage.getTextOfFillingsAttribute(), containsString("current"));
    }
}