package karta_pacjenta.pacjent_service.tests;

import karta_pacjenta.pacjent_service.runnables.GetRunnable;
import karta_pacjenta.pacjent_service.runnables.HttpMethodRunnable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static karta_pacjenta.pacjent_service.runnables.HttpMethodRunnable.THREADS;
import static karta_pacjenta.pacjent_service.runnables.HttpMethodRunnable.user;
import static karta_pacjenta.pacjent_service.runnables.HttpMethodRunnable.pass;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
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

