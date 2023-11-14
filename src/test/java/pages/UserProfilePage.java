package pages;

import models.LoginResponseModel;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class UserProfilePage {
    @Step("Открытие профиля пользователя с авторизационными куками")
    public static void openProfileWithCookies(LoginResponseModel loginResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
        open("/profile");
    }

    @Step("Проверка отсутствия добавленной книги")
    public static void checkDeletedBookNotPresent(String bookId) {
        $("[id='" + bookId + "']").shouldNotBe(visible);
    }
}
