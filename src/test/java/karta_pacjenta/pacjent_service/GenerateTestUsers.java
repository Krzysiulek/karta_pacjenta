package karta_pacjenta.pacjent_service;

import jdk.nashorn.internal.ir.annotations.Ignore;
import karta_pacjenta.pacjent_service.Models.DAOs.Entities.Diseases;
import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class GenerateTestUsers {

    private static final String RANDOM_PERSON_URL = "https://api.randomuser.me/?results=1";
    private static final String BASE_URL = "http://trunk-kartapacjentaservice.herokuapp.com";
    private static final String ADD_USER_URL = "/api/users/register";
    private static final String ADD_DISEASE_URL = "/api/diseases";

    /** https://random-word-api.herokuapp.com/key?. */
    private static final String API_KEY = "PQJQPXXX";
    private static final String RANDOM_WORDS_API_URL = "https://random-word-api.herokuapp.com/" + "/word?key=" + API_KEY + "&number=";

    @Test
    @Ignore
    public void addNewRanodmPeople() throws JSONException {
        for (int i = 0; i < 150; i++) {
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

    @Test
    @Ignore
    public void createRandomDiseases() throws JSONException {
        for (int i = 0; i < 200; i++) {
            try {
                createRandomDisease();
            }catch (IllegalArgumentException e) {
                System.out.println("Cos nie zadziałało. Sorry, już się poprawiam");
                i--;
            }
        }
    }

    public void createRandomDisease() throws JSONException {
        int randomWordsNum = new Random().ints(1, 0, 70).findAny().getAsInt();
        String xd = new RestTemplate().getForObject(RANDOM_WORDS_API_URL + randomWordsNum, String.class);
        System.out.println(xd);
        JSONArray o = new JSONArray(xd);

        int nameId = new Random().ints(1, 0, randomWordsNum).findFirst().getAsInt();
        int catId = new Random().ints(1, 0, randomWordsNum).findFirst().getAsInt();

        StringBuilder builder = new StringBuilder();

        new Random().ints(randomWordsNum, 0, randomWordsNum)
                .forEach(aasd -> {
                    try {
                        builder.append(o.getString(aasd)).append(" ");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });

        Diseases di = new Diseases(0L, o.getString(nameId), o.getString(catId), builder.toString());
        System.out.println(di);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("admin", "admin"));

        String res = restTemplate.postForObject(BASE_URL + ADD_DISEASE_URL, di, String.class);
        System.out.println(res);
    }
}
