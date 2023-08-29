package Diplom3.POM;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPageBurger {
    private WebDriver driver;

    // Локатор кнопки-ссылки "Войти"
    @FindBy(how = How.XPATH, using = ".//a[@class = 'Auth_link__1fOlj' and text() = 'Войти']")
    private SelenideElement loginLink;
    @Step("Нажатие кнопки-ссылки Войти")
    public void clickLoginLink() { loginLink.click(); }

}