package Diplom3.POM;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageBurger {

    //Локатор поля email
    @FindBy(how = How.XPATH, using = ".//input[@name= 'name']")
    private SelenideElement emailFieldForLogin;
    @Step("Ввод значения в поле Имя")
    public void setEmail(String email) {
        emailFieldForLogin.clear();
        emailFieldForLogin.setValue(email);
    }

    //Локатор поля пароль
    @FindBy(how = How.XPATH, using = ".//input[@name= 'Пароль']")
    private SelenideElement passwordFieldForLogin;
    @Step("Ввод значения в поле Пароль")
    public void setPassword(String password) {
        passwordFieldForLogin.clear();
        passwordFieldForLogin.setValue(password);
    }

    //Локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement loginButton;
    @Step("Нажатие кнопки Войти")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Выполнение действия - Логин: ввод email, пароля, нажатие кнопки Войти")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    //Локатор кнопки-ссылки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/div/p[1]/a")
    // .//a[@class = 'Auth_link__1fOlj' and text() = 'Зарегистрироваться']
    public SelenideElement registerLink;
    @Step("Нажатие кнопки-ссылки Зарегистрироваться")
    public void clickRegisterLink() { registerLink.click(); }

    //Локатор кнопки-ссылки "Восстановить пароль"
    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/div/p[2]/a")
    public SelenideElement forgotPasswordLink;
    @Step("Нажатие кнопки-ссылки Восстановить пароль")
    public void clickForgotPasswordLink() { forgotPasswordLink.click(); }

}