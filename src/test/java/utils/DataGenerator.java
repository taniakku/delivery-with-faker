package utils;


import com.github.javafaker.Faker;
import entities.DeliveryInfo;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@UtilityClass
public class DataGenerator {

    @UtilityClass
    public class CardDelivery {

        public static DeliveryInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new DeliveryInfo(faker.address().city(),
                    LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());


        }


    }


}
