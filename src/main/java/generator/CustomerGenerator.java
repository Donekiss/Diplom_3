package generator;

import com.github.javafaker.Faker;

public class CustomerGenerator {

    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().safeEmailAddress();
    }

    public static String randomPassword(int passwordLength) {
        Faker faker = new Faker();
        return faker.internet().password(passwordLength,passwordLength+2);
    }

    public static String randomName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }
    public static Customer randomCustomer(int passwordLength) {
        return new Customer()
                .withEmail(randomEmail())
                .withPassword(randomPassword(passwordLength))
                .withName(randomName());
    }

}
