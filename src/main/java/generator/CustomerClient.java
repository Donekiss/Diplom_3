package generator;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.google.gson.JsonObject;

import static io.restassured.RestAssured.given;

public class CustomerClient {
    @SerializedName("accessToken")
    private String accessToken;
    private static final String CREATE_CUSTOMER = "/api/auth/register";
    private static final String PASS_CUSTOMER = "/api/auth/login";
    private static final String DELETE_CUSTOMER = "/api/auth/user";
    public String getToken() {
        return accessToken;
    }
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

    public String CreateCustomer() {
        CustomerClient customerClient = new CustomerClient();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        Customer customer = CustomerGenerator.randomCustomer(8);
        Response response = customerClient.create(customer);

        String jsonResponse = response.getBody().asString();
        accessToken = JsonPath.from(jsonResponse).getString("accessToken");
        return accessToken;
    }
    public static String loginCustomer(String email, String password) {
        CustomerClient customerClient = new CustomerClient();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        Response response = customerClient.pass(email, password);

        String responseBody = response.getBody().asString();
        Gson gson = new Gson();
        CustomerClient authResponse = gson.fromJson(responseBody, CustomerClient.class);

        return authResponse.accessToken;
    }

    public static void deleteCustomer(String token){
        CustomerClient customerClient = new CustomerClient();
        customerClient.delete(token);}


}
