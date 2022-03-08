package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import entities.DeliveryInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    private static Faker faker;

    @BeforeAll
    static void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    @BeforeEach
    void Preparation() {
        Configuration.holdBrowserOpen = true;
        open("");


    }


    @Test
    void ShouldSendForm() {

        String orderDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id='city'] input").val(faker.address().city());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(orderDate);
        $(byName("name")).val(faker.name().fullName());
        $("[name='phone']").val(faker.phoneNumber().phoneNumber());
        $(byClassName("checkbox__box")).click();
        $(byText("Запланировать")).click();

        $(withText("Успешно!")).should(appear, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + orderDate), Duration.ofSeconds(15));

    }

    @Test
    void ShouldSendFormTwice() {

        String orderDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id='city'] input").val(faker.address().city());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(orderDate);
        $(byName("name")).val(faker.name().fullName());
        $("[name='phone']").val(faker.phoneNumber().phoneNumber());
        $(byClassName("checkbox__box")).click();
        $(byText("Запланировать")).click();

        $(withText("Успешно!")).should(appear, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + orderDate), Duration.ofSeconds(15));

        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification']").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"), Duration.ofSeconds(2));
        $(byText("Перепланировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + orderDate), Duration.ofSeconds(15));


    }


}
