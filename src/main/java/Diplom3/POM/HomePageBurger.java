package Diplom3.POM;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePageBurger {
    private WebDriver driver;

    // Локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Войти в аккаунт']")
    private SelenideElement logToAccount;
    @Step("Нажатие кнопки Войти в аккаунт")
    public void clickLogToAccountButton() {
        logToAccount.click();
    }

    // Локатор кнопки "Оформить заказ"
    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text() = 'Оформить заказ']")
    private SelenideElement makeOrder;
    @Step("Нажатие кнопки Оформить заказ")
    public void clickToMakeOrderButton() {
        makeOrder.click();
    }

    @Step("Проверка доступности кнопки Оформить заказ")
    public boolean check_MakeOrderButton() {
        return makeOrder.isEnabled();
    }

    // Локатор раздела "Булки"
    @FindBy(how = How.XPATH, using = "//main/section[1]/div[1]/div[1]")
    private SelenideElement bunsTab;
    @Step("Нажатие кнопки Булки")
    public void clickBunsTab() { bunsTab.click(); }
    @Step("Получение значения атрибута(class) у кнопки Булки")
    public String getTextOfBunsAttribute() { return bunsTab.getAttribute("class"); }

    // Локатор раздела "Соусы"
    @FindBy(how = How.XPATH, using = "//main/section[1]/div[1]/div[2]")
    private SelenideElement saucesTab;
    @Step("Нажатие кнопки Соусы")
    public void clickSaucesTab() { saucesTab.click(); }
    @Step("Получение значения атрибута(class) у кнопки Соусы")
    public String getTextOfSaucesAttribute() { return saucesTab.getAttribute("class");  }

    // Локатор раздела "Начинки"
    @FindBy(how = How.XPATH, using = "//main/section[1]/div[1]/div[3]")
    private SelenideElement fillingsTab;
    @Step("Нажатие кнопки Начинки")
    public void clickFillingsTab() { fillingsTab.click(); }
    @Step("Получение значения атрибута(class) у кнопки Начинки")
    public String getTextOfFillingsAttribute() { return fillingsTab.getAttribute("class"); }

}