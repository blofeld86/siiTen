package Models;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class UserFactory {

    public static User getRandomUser(){
        Faker faker = new Faker();
        return new User.UserBuilder()
                .buildFirstName(faker.name().firstName())
                .buildLastName(faker.name().lastName())
                .buildPassword(faker.bothify("???????"))
                .build();
    }



    public static void main(String[] args) {
        System.out.println(getRandomUser());
    }
}
