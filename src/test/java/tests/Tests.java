package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helper.BaseHelper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Tests extends BaseHelper {

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
            $(byText("Поиск")).click();
        });
        step("Verify that no vacancies find", () -> {
            SelenideElement result = $("h3").shouldHave(Condition.text("Результаты по запросу: qa engineer"));
            assertThat(result.sibling(0).getText()).isEqualTo("Ничего не найдено");
        });
    }

    @Test
    @DisplayName("Contact us positive request test")
    void sendContactUsTest() {
        step("Open news tab", () -> {
            $$("li.t456__list_item").find(Condition.text("Новости")).click();
        });
        step("Fill out email at contact us form", () -> {
            $("input[name=Email]").scrollIntoView(false).setValue("customer@yandex.ru");
        });
        step("Fill out phone at contact us form", () -> {
            $("input[placeholder='(999) 999-99-99']").append("9877654331").click();
            //
        });
        step("Fill out name at contact us form", () -> {
            $("input[name=Name]").setValue("Александр");
        });
        step("Fill out company at contact us form", () -> {
            $("input[name='Название компании']").setValue("Tarragon");
        });
        step("Select country at contact us form", () -> {
            $("select[name=Страна]").click();
            $$("option").find(Condition.attribute("value", "Греция")).click();

        });
        step("Select interest at contact us form", () -> {
            $("select[name='Меня интересует']").click();
            $$("option").find(Condition.attribute("value", "Рынок капитала")).click();

        });
        step("Submit form", () -> {
            $("button[type=submit]").click();
            sleep(5000);
        });
        step("Verify that form request send successfully", () -> {
            SelenideElement result = $("input[value='Заявканаглавной']").sibling(0);
            assertThat(result.getText()).isEqualTo("Спасибо, мы получили ваш запрос. Скоро с вами свяжемся.");
        });

    }

    @MethodSource("helper.LoadDataHelper#loadDataFromFile")
    @ParameterizedTest(name = "Check menu test")
    void checkMenuTest(Map data){

        Map<String, String> expectedData = data;

        step("Verify solution menu items", () -> {
            ElementsCollection menuitems = $$("li.t456__list_item").snapshot();
            assertThat(menuitems.get(0).$("a").getText()).isEqualTo(expectedData.get("solution"));
            assertThat(menuitems.get(1).$("a").getText()).isEqualTo(expectedData.get("service"));
        });
    }

    //t456__list_item"
}
