package Diplom3.client;

import Diplom3.model.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseApi {
    private String registerPath = "api/auth/register/";
    private String loginPath = "api/auth/login/";
    private String userPath = "api/auth/user/";

    @Step("Регистрация пользователя {user}")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseReqSpec())
                .body(user)
                .when()
                .post(registerPath)
                .then();
    }
    @Step("Логин пользователя")
    public ValidatableResponse loginUser(User user) {
        return given()
                .spec(getBaseReqSpec())
                .body(user)
                .post(loginPath)
                .then();
    }
    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token) {
        return given()
                .header("authorization", token)
                .spec(getBaseReqSpec())
                .when()
                .delete(userPath)
                .then();
    }


}
