package utils;


import com.github.javafaker.Faker;
import entities.DeliveryInfo;
import lombok.experimental.UtilityClass;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataGenerator {

    public static String generateDate(int shift) {
        var date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        var city = faker.address().city();
        return city;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        var name = faker.name().fullName();
        return name;
    }


    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        var phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

}



