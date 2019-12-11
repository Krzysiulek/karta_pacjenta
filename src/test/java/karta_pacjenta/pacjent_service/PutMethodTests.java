package karta_pacjenta.pacjent_service;

import net.minidev.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static karta_pacjenta.pacjent_service.HttpMethodRunnable.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @project karta_pacjenta
 * @user blaise * @date 2019-12-11 * @time 08:20
 */
@SpringBootTest
public class PutMethodTests {

    @Test
    void updateDisease() {
        final String uri = "https://trunk-kartapacjentaservice.herokuapp.com/api/diseases";

        ArrayList<HttpStatus> httpStatuses = new ArrayList<>();

        putDiseaseHttpStatus( httpStatuses, 100 , uri);

        for(HttpStatus httpStatus : httpStatuses) {
            assertEquals( httpStatus, HttpStatus.OK );
        }
    }


    static void putDiseaseHttpStatus(ArrayList<HttpStatus> httpStatuses, int iterations, String uri) {
        ExecutorService executor = Executors.newFixedThreadPool( THREADS);
        for (int i = 0; i < iterations ; i++) {

            JSONObject jsonObject = new JSONObject();

            jsonObject.appendField( "category", RandomStringUtils.randomAlphabetic( 10));
            jsonObject.appendField( "description", RandomStringUtils.randomAlphabetic(400));
            jsonObject.appendField( "name", RandomStringUtils.randomAlphabetic( 10));
            jsonObject.appendField( "diseaseId", RandomUtils.nextInt( 1, 200 ) );

            executor.execute( new PutRunnable( uri, user, pass, jsonObject, httpStatuses ) );
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("\nFinished all threads");
        System.out.println(httpStatuses.size());
    }
}
