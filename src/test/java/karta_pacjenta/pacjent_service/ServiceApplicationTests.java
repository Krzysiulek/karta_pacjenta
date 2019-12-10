package karta_pacjenta.pacjent_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ServiceApplicationTests {

	private static final String user = "admin";
	private static final String pass = "admin";


	@BeforeEach
	public void auth() {
		System.out.println("tutaj");
	}

	@Test
	void getAllUsers() {

		final String uri = "https://trunk-kartapacjentaservice.herokuapp.com/api/users";

		ArrayList<HttpStatus> httpStatusArrayList = new ArrayList<>();

		ExecutorService executor = Executors.newFixedThreadPool( 50);
		for (int i = 0; i < 1000 ; i++) {
			GetRunnable getRunnable = new GetRunnable(uri, user, pass, httpStatusArrayList);
			Runnable worker = getRunnable;
			executor.execute(worker);
		}
		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("\nFinished all threads");

		System.out.println(httpStatusArrayList.size());

		for(HttpStatus httpStatus : httpStatusArrayList) {
			assertEquals( httpStatus, HttpStatus.OK );
		}
	}

	@Test
	void getAllDiseases() {
		final String uri = "https://trunk-kartapacjentaservice.herokuapp.com/api/diseases";

		ArrayList<HttpStatus> httpStatusArrayList = new ArrayList<>();

		ExecutorService executor = Executors.newFixedThreadPool( 50);
		for (int i = 0; i < 1000 ; i++) {
			GetRunnable getRunnable = new GetRunnable(uri, user, pass, httpStatusArrayList);
			Runnable worker = getRunnable;
			executor.execute(worker);
		}
		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("\nFinished all threads");

		System.out.println(httpStatusArrayList.size());

		for(HttpStatus httpStatus : httpStatusArrayList) {
			assertEquals( httpStatus, HttpStatus.OK );
		}
	}
}

