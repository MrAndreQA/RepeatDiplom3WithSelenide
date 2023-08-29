package Diplom3.POM;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderPageBurger {
    private WebDriver driver;
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";

    // Локатор кнопки "Личный Кабинет"
    @FindBy(how = How.XPATH, using = ".//p[@class = 'AppHeader_header__linkText__3q_va ml-2' and text() = 'Личный Кабинет']")
    private SelenideElement personalAccountButton;
    @Step("Нажатие кнопки Личный Кабинет")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    // Локатор кнопки "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[@class = 'AppHeader_header__linkText__3q_va ml-2' and text() = 'Конструктор']")
    private SelenideElement constructorButton;
    @Step("Нажатие кнопки Конструктор")
    public void clickConstructorButton() { constructorButton.click(); }

    // Локатор логотипа "Бургер"
    @FindBy(how = How.XPATH, using = ".//div/a[@href='/']")
    private SelenideElement burgerLogo;
    @Step("Клик по логотипу Бургера")
    public void clickToBurgerLogo() { burgerLogo.click(); }
    }