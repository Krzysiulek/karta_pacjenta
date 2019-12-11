package karta_pacjenta.pacjent_service.runnables;

import net.minidev.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * @project karta_pacjenta
 * @user blaise * @date 2019-12-10 * @time 23:49
 */
public class PostRunnable extends HttpMethodRunnable {
    private final String                url;
    private final String                user;
    private final String                pass;
    private final JSONObject            jsonObject;
    private        ArrayList<HttpStatus> httpStatuses;

    public PostRunnable(String url, String user, String pass, JSONObject jsonObject, ArrayList<HttpStatus> httpStatuses) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.httpStatuses = httpStatuses;
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate        restTemplate;

        restTemplate = restTemplateBuilder
                .basicAuthentication( user, pass )
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>( jsonObject.toString(), headers );

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(url, HttpMethod.POST, entity, String.class);

        final String                 body           = responseEntity.getBody();
        System.out.println("body = " + body);
        System.out.println(responseEntity.getStatusCode());
        httpStatuses.add(responseEntity.getStatusCode());
    }
}
