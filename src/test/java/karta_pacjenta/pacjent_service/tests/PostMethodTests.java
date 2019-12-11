package karta_pacjenta.pacjent_service.tests;

import karta_pacjenta.pacjent_service.runnables.PostRunnable;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static karta_pacjenta.pacjent_service.runnables.HttpMethodRunnable.THREADS;
import static karta_pacjenta.pacjent_service.runnables.HttpMethodRunnable.user;
import static karta_pacjenta.pacjent_service.runnables.HttpMethodRunnable.pass;


/**
 * @project karta_pacjenta
 * @user blaise * @date 2019-12-11 * @time 08:06
 *
 *
 * TODO:
 *      - getHttpStatusForRegisterUser
 */
@SpringBootTest
public class PostMethodTests {

    @Test
    void postIllnessCource() {
        final String uri = "https://trunk-kartapacjentaservice.herokuapp.com/api/illnessCource";

        ArrayList<HttpStatus> httpStatuses = new ArrayList<>();

        getHttpStatusForIllnessCource( httpStatuses, 100 , uri);

        for(HttpStatus httpStatus : httpStatuses) {
            assertEquals( httpStatus, HttpStatus.OK );
        }
    }

//    @Test
//    void postRegisterUser() {
//        final String uri = "https://trunk-kartapacjentaservice.herokuapp.com/api/users/register";
//
//        ArrayList<HttpStatus> httpStatuses = new ArrayList<>();
//
//        getHttpStatusForRegisterUser( httpStatuses, 100 , uri);
//
//        for(HttpStatus httpStatus : httpStatuses) {
//            assertEquals( httpStatus, HttpStatus.OK );
//        }
//
//
//    }

    static void getHttpStatusForIllnessCource(ArrayList<HttpStatus> httpStatuses, int iterations, String uri) {
        ExecutorService executor = Executors.newFixedThreadPool( THREADS);
        for (int i = 0; i < iterations ; i++) {

            JSONObject jsonObject = new JSONObject();

            jsonObject.appendField( "doctorDescription", RandomStringUtils.randomAlphabetic( 100));
            jsonObject.appendField( "patientDescription", RandomStringUtils.randomAlphabetic(10));
            jsonObject.appendField( "prescription", RandomStringUtils.randomAlphabetic(10));
            jsonObject.appendField( "visitCategory", RandomStringUtils.randomAlphabetic( 10));
            jsonObject.appendField( "diseaseId", RandomUtils.nextInt( 1, 200 ) );

            executor.execute( new PostRunnable( uri, user, pass, jsonObject, httpStatuses ) );
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("\nFinished all threads");
        System.out.println(httpStatuses.size());
    }

    static void getHttpStatusForRegisterUser(ArrayList<HttpStatus> httpStatuses, int iterations , String uri) {
        ExecutorService executor = Executors.newFixedThreadPool( THREADS);
        for (int i = 0; i < iterations ; i++) {

            JSONObject jsonObject = new JSONObject();

            jsonObject.appendField( "doctorDescription", RandomStringUtils.randomAlphabetic( 100));
            jsonObject.appendField( "patientDescription", RandomStringUtils.randomAlphabetic(10));
            jsonObject.appendField( "prescription", RandomStringUtils.randomAlphabetic(10));
            jsonObject.appendField( "visitCategory", RandomStringUtils.randomAlphabetic( 10));
            jsonObject.appendField( "diseaseId", RandomUtils.nextInt( 1, 200 ) );

            executor.execute( new PostRunnable( uri, user, pass, jsonObject, httpStatuses ) );
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("\nFinished all threads");
        System.out.println(httpStatuses.size());
    }
}
