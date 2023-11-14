package tests;

import api.ApiSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    ApiSteps apiSteps = new ApiSteps();

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "2560x1440");
        Configuration.browserVersion = System.getProperty("browserVersion", "100.0");
        Configuration.pageLoadStrategy = "eager";

        String selenoidUrl = System.getProperty("selenoidUrl", "selenoid.autotests.cloud");
        Configuration.remote = "https://user1:1234@" + selenoidUrl + "/wd/hub";
    }

    @BeforeEach
    void logAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
