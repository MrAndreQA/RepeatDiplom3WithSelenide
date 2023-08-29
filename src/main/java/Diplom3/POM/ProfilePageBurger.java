package Diplom3.POM;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePageBurger {
    private WebDriver driver;

    //Локатор поля логин
    @FindBy(how = How.XPATH, using = ".//div/ul/li[2]/div/div/input")
    private SelenideElement loginFieldInProfile;

    // Локатор вкладки "История заказов" (профиль пользователя)
    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/nav/ul/li[2]/a")
    private SelenideElement orderHistoryTab;
    public SelenideElement getOrderHistoryTab() {return orderHistoryTab;}

    // Локатор кнопки "Выход" (профиль пользователя)
    @FindBy(how = How.XPATH, using = ".//button[@class = 'Account_button__14Yp3 text text_type_main-medium text_color_inactive']")
    private SelenideElement logOutButton;
    @Step("Нажатие кнопки Выход")
    public void clickLogOutButton() {logOutButton.click();}

}