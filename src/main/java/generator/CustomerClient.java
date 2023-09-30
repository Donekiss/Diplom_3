package generator;

import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CustomerClient {
    private static final String CREATE_CUSTOMER = "/api/auth/register";
    private static final String PASS_CUSTOMER = "/api/auth/login";
    private static final String DELETE_CUSTOMER = "/api/auth/user";

    @Step("Login customer through Api with email and password")
    public static String loginCustomer(String email, String password) {
        CustomerClient customerClient = new CustomerClient();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        Response response = customerClient.pass(email, password);

        String jsonResponse = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(jsonResponse);

        return jsonPath.getString("accessToken");
    }

    @Step("Api delete this customer")
    public static void deleteCustomer(String token) {
        CustomerClient customerClient = new CustomerClient();
        customerClient.delete(token);
    }

    @Step("Api post to create customer")
    public Response create(Customer customer) {
        return given()
                .header("Content-type", "application/json")
                .body(customer)
                .when()
                .post(CREATE_CUSTOMER);
    }

    @Step("Api post to login customer")
    public Response pass(String email, String password) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("email", email);
        requestBody.addProperty("password", password);

        return given()
                .header("Content-type", "application/json")
                .body(requestBody.toString())
                .when()
                .post(PASS_CUSTOMER);
    }

    public Response delete(String token) {
        return given()
                .header("Authorization", token)
                .header("Content-type", "application/json")
                .when()
                .delete(DELETE_CUSTOMER);
    }


}
