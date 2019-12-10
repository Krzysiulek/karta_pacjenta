package karta_pacjenta.pacjent_service;

import jdk.nashorn.internal.ir.annotations.Ignore;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Random;

public class GenerateTestUsers {

    private static final String RANDOM_PERSON_URL = "https://api.randomuser.me/?results=1";
    private static final String BASE_URL = "http://trunk-kartapacjentaservice.herokuapp.com";
    private static final String ADD_USER_URL = "/api/users/register";

    @Test
    @Ignore
    public void addNewRanodmPeople() throws JSONException {
        for (int i = 0; i < 200; i++) {
            createNewPerson();
        }
    }


    public void createNewPerson() throws JSONException {
        String xd = new RestTemplate().getForObject(RANDOM_PERSON_URL, String.class);
        JSONObject jsonObject = new JSONObject(xd);

        JSONArray o = jsonObject.getJSONArray("results");
        JSONObject le = (JSONObject) o.get(0);

        String userName = le.getJSONObject("login").getString("username");
        String firstName = le.getJSONObject("name").getString("first");
        String lastName = le.getJSONObject("name").getString("last");

        String password = "test";
        String email = le.getString("email");

        JSONObject location = le.getJSONObject("location");
        String address = location.getString("country") + " " + location.getString("state") + " " + location.getString("city");
        String phoneNumber = le.getString("phone");
        String personalIdentityNumber = new BigInteger(11*4, new Random()).toString();

        String response = new RestTemplate().postForObject(BASE_URL + ADD_USER_URL, new MyServiceUser(userName, password, email, firstName, lastName, address, phoneNumber, personalIdentityNumber), String.class);
    }
}
