package models;

import com.github.javafaker.Faker;

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
                .buildZipCode(Integer.toString(99)+"-"+Integer.toString(999))
                .buildPhone(faker.phoneNumber().subscriberNumber(9))
                .build();

    }

    public static void main(String[] args) {
        Faker faker = new Faker();

        System.out.println(faker.phoneNumber().subscriberNumber(9));

    }
}
