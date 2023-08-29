package Diplom3.Tests;

import Diplom3.POM.HeaderPageBurger;
import Diplom3.POM.LoginPageBurger;
import Diplom3.POM.ProfilePageBurger;
import Diplom3.POM.RegisterPageBurger;
import Diplom3.client.UserApi;
import Diplom3.model.User;
import com.codeborne.selenide.Configuration;
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
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterUserTest {

    User user;
    UserApi userApi = new UserApi();
    // ProfilePageBurger profilePage = page(ProfilePageBurger.class);
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
    @DisplayName("Успешная регистрация пользователя - стандарный позитивный тест")
    public void registerUserPositiveTest() {
        HeaderPageBurger headerPage = open(BASE_URI, HeaderPageBurger.class);
        headerPage.clickPersonalAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.clickRegisterLink();

        RegisterPageBurger registerPage = page(RegisterPageBurger.class);
        registerPage.registration(USER_EMAIL, USER_PASSWORD, USER_NAME);

        open(BASE_URI);
        headerPage.clickPersonalAccountButton();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        headerPage.clickPersonalAccountButton();
        sleep(1000);
        ProfilePageBurger profilePage = page(ProfilePageBurger.class);

        assertTrue(profilePage.getOrderHistoryTab().isEnabled());
    }
    @Test
    @DisplayName("Проверка появления сообщения с ошибкой - при введении короткого пароля (5 символов)")
    @Description("Длина пароля должна быть >= 6 символов")
    public void registerUser_WithShortPassword_NegativeTest() {
        HeaderPageBurger headerPage = open(BASE_URI, HeaderPageBurger.class);
        headerPage.clickPersonalAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.clickRegisterLink();

        RegisterPageBurger registerPage = page(RegisterPageBurger.class);
        registerPage.registration(USER_EMAIL, "passw", USER_NAME );

        String actualText = "Некорректный пароль";

        //assertTrue(registerPage.check_Incorrect_Password_Message()); не полная проверка (не сверяем ОР текста)
        assertEquals(actualText, registerPage.getText_IncorrectPassword_Message());
    }

    @Test
    @DisplayName("Проверка появления сообщения с ошибкой (повторная регистрация)")
    @Description("При попытке повторной регистрации уже существующего пользователя, сообщение = \"Такой пользователь уже существует\"")
    public void register_DublicateUser_NegativeTest() {
        ValidatableResponse registerResponse = userApi.createUser(user);

        HeaderPageBurger headerPage = open(BASE_URI, HeaderPageBurger.class);
        headerPage.clickPersonalAccountButton();

        LoginPageBurger loginPage = page(LoginPageBurger.class);
        loginPage.clickRegisterLink();

        RegisterPageBurger registerPage = page(RegisterPageBurger.class);
        registerPage.registration(USER_EMAIL, USER_PASSWORD, USER_NAME);

        String actualText = "Такой пользователь уже существует";

        sleep(1000);

        assertEquals(actualText, registerPage.getText_DublicateUser_Message());
    }


}
