package generator;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.google.gson.JsonObject;

import static io.restassured.RestAssured.given;

public class CustomerClient {
    private static final String CREATE_CUSTOMER = "/api/auth/register";
    private static final String PASS_CUSTOMER = "/api/auth/login";
    private static final String DELETE_CUSTOMER = "/api/auth/user";

    public Response create(Customer customer){
        return given()
                .header("Content-type", "application/json")
                .body(customer)
                .when()
                .post(CREATE_CUSTOMER);
    }
    public Response pass(String email, String password){
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("email", email);
        requestBody.addProperty("password", password);

        return given()
                .header("Content-type", "application/json")
                .body(requestBody.toString())
                .when()
                .post(PASS_CUSTOMER);
    }
    public Response delete(String token){
        return given()
                .header("Authorization", token)
                .header("Content-type", "application/json")
                .when()
                .delete(DELETE_CUSTOMER);
    }
    public Response logout(String token){
        return  given()
                .header("Authorization", token)
                .header("Content-type", "application/json")
                .and()
                .when()
                .get(PASS_CUSTOMER);
    }

    public static String loginCustomer(String email, String password) {
        CustomerClient customerClient = new CustomerClient();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        Response response = customerClient.pass(email, password);

        String jsonResponse = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(jsonResponse);

        return jsonPath.getString("accessToken");
    }

    public static void deleteCustomer(String token){
        CustomerClient customerClient = new CustomerClient();
        customerClient.delete(token);}


}
