package Diplom3.POM;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPageBurger {
    private WebDriver driver;

    // Локатор поля имя
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement nameField;
    @Step("Ввод значения в поле Имя")
    public void setName(String name) {
        nameField.setValue(name);
    }

    // Локатор поля email
    @FindBy(how = How.XPATH, using = ".//fieldset[2]//input")
    private SelenideElement emailField;
    @Step("Ввод значения в поле Email")
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    // Локатор поля пароль
    @FindBy(how = How.XPATH, using = ".//div/input[@name='Пароль']")
    private SelenideElement passwordField;
    @Step("Ввод значения в поле Пароль")
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    // Локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[@class= 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement registerButton;
    @Step("Нажатие кнопки Зарегистрироваться")
    public void clickRegisterButton(){
        registerButton.click();
    }

    @Step("Выполнение действия - Регистрирация: ввод email, пароля, имени, нажатие кнопки Зарегистрироваться")
    public void registration(String email, String password, String name) {
        setEmail(email);
        setPassword(password);
        setName(name);
        clickRegisterButton();
    }

    // Локатор кнопки-ссылки "Войти"
    @FindBy(how = How.XPATH, using = ".//a[@class = 'Auth_link__1fOlj']")
    private SelenideElement loginLink;
    @Step("Нажатие кнопки-ссылки Войти")
    public void clickLoginLink() { loginLink.click(); }

    // Локатор всплывающей ошибки "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//p[@class='input__error text_type_main-default']")
    private SelenideElement incorrectPasswordMessage;
    @Step("Получение теста всплывающей ошибки - Некорректный пароль ")
    public String getText_IncorrectPassword_Message() {
        return incorrectPasswordMessage.getText();
    }

    // Локатор всплывающей ошибки "Такой пользователь уже существует"
    @FindBy(how = How.XPATH, using = ".//p[@class='input__error text_type_main-default']")
    private SelenideElement dublicateUserMessage;
    @Step("Получение теста всплывающей ошибки - Такой пользователь уже существует ")
    public String getText_DublicateUser_Message() { return dublicateUserMessage.getText();}

}