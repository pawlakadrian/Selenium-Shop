package models;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class UserFactory {

    public User getRandomUser() {
        Faker faker = new Faker(
                new Locale("pl-PL"), new RandomService()
        );
        User user = new UserBuilder()
                .firstName(faker.letterify("?????"))
                .lastName(faker.letterify("??????"))
                .email(faker.bothify("???##??@gmail.com"))
                .password(faker.bothify("??##??##"))
                .build();
        return user;
    }

    public User getAlreadyRegisteredUser() {
        User user = new UserBuilder()
                .firstName("Test")
                .lastName("User")
                .email("randommail@gmail.com")
                .password("trudnehasł@997")
                .build();
        return user;
    }
}
