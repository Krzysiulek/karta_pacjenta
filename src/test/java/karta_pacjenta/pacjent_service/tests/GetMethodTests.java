package karta_pacjenta.pacjent_service.tests;

import karta_pacjenta.pacjent_service.runnables.GetRunnable;
import karta_pacjenta.pacjent_service.runnables.HttpMethodRunnable;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static karta_pacjenta.pacjent_service.runnables.HttpMethodRunnable.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
class GetMethodTests {

	@Test
	void getAllUsers() {

		final String uri = "https://trunk-kartapacjentaservice.herokuapp.com/api/users";

		ArrayList<HttpStatus> httpStatuses = new ArrayList<>();

        getHttpStatus( httpStatuses,  100, new GetRunnable( uri, user, pass, httpStatuses) );

        for(HttpStatus httpStatus : httpStatuses) {
			assertEquals( httpStatus, HttpStatus.OK );
		}
	}

	@Test
	void getAllDiseases() {
		final String uri = "https://trunk-kartapacjentaservice.herokuapp.com/api/diseases";

		ArrayList<HttpStatus> httpStatuses = new ArrayList<>();

        getHttpStatus( httpStatuses,  100, new GetRunnable( uri, user, pass, httpStatuses) );

        for(HttpStatus httpStatus : httpStatuses) {
			assertEquals( httpStatus, HttpStatus.OK );
		}
	}

	//50: 1:23:412 - test
    //50: 3:21:794 - test2
    //100: 2:38:126 - test
    //100: 5:7:64- test2
	@Test
    public void test() {
        final String uri = "https://trunk-kartapacjentaservice.herokuapp.com/api/patients/test";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("admin", "admin"));

        for (int i = 0; i < 100; i++) {
            restTemplate.getForObject(uri, String.class);
        }
    }

	@Test
    void getDiseasById() {
        ArrayList<HttpStatus> httpStatuses = new ArrayList<>();

	    for(int i = 0; i < 100 ; i++) {
            final String uri = "https://trunk-kartapacjentaservice.herokuapp.com/api/diseases/" + i;

            getHttpStatus( httpStatuses,  10, new GetRunnable( uri, user, pass, httpStatuses) );

        }

        for(HttpStatus httpStatus : httpStatuses) {
            assertEquals( httpStatus, HttpStatus.OK );
        }
    }


    static void getHttpStatus(ArrayList<HttpStatus> httpStatuses, int iterations, HttpMethodRunnable httpMethodRunnable) {
        ExecutorService executor = Executors.newFixedThreadPool( THREADS);
        for (int i = 0; i < iterations ; i++) {
            executor.execute( httpMethodRunnable );
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("\nFinished all threads");
        System.out.println(httpStatuses.size());
    }


}

