package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Demoqatest {

    @BeforeAll
    static void openPage() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
        Configuration.reopenBrowserOnFail = true;
    }

    @Test
    void successTest() {
        $(byText("Student Registration Form")); //проверка заголовка страницы

        $("#firstName").setValue("Jane");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("mail@mail.com");
        $(byText("Female")).click();
        $("#userNumber").setValue("9031112233");
        $("#dateOfBirthInput").click();
        $("[class*='month-select']").selectOptionByValue("1");
        $("[class*='year-select']").selectOptionByValue("2000");
        $("[class*='day--030']").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/data/sample.png"));
        $("#currentAddress").setValue("Moscow");
        $("#react-select-3-input").setValue("Uttar Pradesh").pressEnter();
        $("#react-select-4-input").setValue("Agra").pressEnter();
        $("#submit").pressEnter();

        $("#example-modal-sizes-title-lg").shouldBe(Condition.visible).shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Jane Doe"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("mail@mail.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9031112233"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("30 January,2000"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("sample.png"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Moscow"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Uttar Pradesh Agra"));

    }
}
