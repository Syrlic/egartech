package helper;

import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class BaseHelper {
    @BeforeEach
    public void openMainPage(){
        step("Open main page ", () -> {
            open("https://egartech.ru/");
        });
    }
}
