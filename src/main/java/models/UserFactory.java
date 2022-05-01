package models;

import com.github.javafaker.Faker;
import models.User;

import java.util.Random;

public class UserFactory {

    public User getRandomUser(){
        Faker faker = new Faker();
        Random random = new Random();
        return new User.UserBuilder()
                .buildFirstName(faker.name().firstName())
                .buildLastName(faker.name().lastName())
                .buildPassword(faker.internet().password())
                .buildMail(faker.internet().emailAddress())
                .buildAlias(faker.ancient().hero())
                .buildStreet(faker.address().streetAddress())
                .buildCompany(faker.company().name())
                .buildCity(faker.address().city())
                .buildZipCode(Integer.toString(random.nextInt(99))+"-"+Integer.toString(random.nextInt(999)))
                .buildPhone(faker.phoneNumber().subscriberNumber(9))
                .build();

    }

    public User getAlreadyRegisteredUser(){
        return new User.UserBuilder()
                .buildFirstName("Willie")
                .buildLastName("The Coyote")
                .buildPassword("RoadRunner")
                .buildMail("wb@wb.wb")
                .buildAlias("Genius")
                .buildStreet("Nevada Fields")
                .buildCompany("Lonney Toons")
                .buildCity("Las Vegas")
                .buildZipCode("00-007")
                .buildPhone("876543219")
                .build();
    }

}
