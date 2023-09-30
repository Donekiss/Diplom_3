package generator;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CustomerToken {
    @SerializedName("accessToken")
    private String accessToken;

    public static CustomerToken fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CustomerToken.class);
    }

    @Step("Extract AccessToken from the response")
    public static String extractAccessToken(Response response) {
        String responseBody = response.getBody().asString();
        CustomerToken authResponse = CustomerToken.fromJson(responseBody);
        return authResponse.getAccessToken();
    }

    public String getAccessToken() {
        return accessToken;
    }

}
