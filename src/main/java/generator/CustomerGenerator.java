package generator;

import com.github.javafaker.Faker;

public class CustomerGenerator {

    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().safeEmailAddress();
    }

    public static String randomPassword(int number) {
        Faker faker = new Faker();
        return faker.internet().password(number,number+2);
    }

    public static String randomName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }
    public static Customer randomCustomer(int number) {
        return new Customer()
                .withEmail(randomEmail())
                .withPassword(randomPassword(number))
                .withName(randomName());
    }

}
