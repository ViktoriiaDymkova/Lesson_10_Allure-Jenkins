package demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class RegistrationFormTests extends TestBase {

    @Test
    @Tag("homework")
    @DisplayName("Успешное заполнение формы регистрации")
    void fillFormTest() {
        step("Открываем форму регистрации", () -> {
            open("/automation-practice-form");
            //executeJavaScript("$('footer').remote()");
            //executeJavaScript("$('#fixe').remote()");
            zoom(0.65);
        });
        step("Заполняем форму регистрации", () -> {
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            $("[id=firstName]").setValue("vika");
            $("[id=lastName]").setValue("kom");
            $("[id=userEmail]").setValue("vika@kom.com");
            $("#genterWrapper").$(byText("Female")).click();
            $("[id=userNumber]").setValue("1234567890");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("July");
            $(".react-datepicker__year-select").selectOption("1991");
            $(".react-datepicker__day--011:not(.react-datepicker__day--outside-month)").click();
            $("#subjectsInput").setValue("English").pressEnter();
            $("#hobbiesWrapper").$(byText("Music")).click();
            $("#uploadPicture").uploadFromClasspath("bruss.jpeg");
            $("#currentAddress").setValue("Spb");
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Delhi")).click();
            $("#submit").click();
        });
        step("Проверка данных в форме", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text("vika kom"));
            $(".table-responsive").$(byText("Student Name"))
                    .parent().shouldHave(text("vika kom"));
        });
    }
}