package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SearchVacancyTest extends BaseHelper {

    @Test
    @DisplayName("Search for vacancy")
    void searchForVacancyTest() {

        step("Open career tab", () -> {
            $$("li.t456__list_item").find(Condition.text("Карьера")).click();
        });
        step("Open vacancies list", () -> {
            $$("a.tn-atom").find(Condition.text("Список доступных вакансий")).scrollIntoView(false).click();
        });
        step("Search for vacancy", () -> {
            $("input[placeholder=Поиск]").setValue("qa engineer");
            $("button.t-submit").click();
            sleep(3000);
        });

        // add asserts
    }
}
