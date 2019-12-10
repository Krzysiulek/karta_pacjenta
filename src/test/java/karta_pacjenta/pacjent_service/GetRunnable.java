package karta_pacjenta.pacjent_service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * @project karta_pacjenta
 * @user blaise * @date 2019-12-10 * @time 21:52
 */
public class GetRunnable implements Runnable {
    private final String url;
    private final String user;
    private final String pass;
    private ArrayList<HttpStatus> httpStatuses;

    GetRunnable(String url, String user, String pass, ArrayList<HttpStatus> httpStatuses) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.httpStatuses = httpStatuses;
    }

    @Override
    public void run() {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate        restTemplate;

        restTemplate = restTemplateBuilder
                .basicAuthentication( user, pass )
                .build();

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity( url, String.class );
        final String                 body           = responseEntity.getBody();
        System.out.println("body = " + body);
        System.out.println(responseEntity.getStatusCode());
        httpStatuses.add(responseEntity.getStatusCode());
    }
}
