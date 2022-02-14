package com.netmed.payments;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymenthandlerApplication {

	public static void main(String[] args) throws JSONException {
		SpringApplication.run(PaymenthandlerApplication.class, args);

		System.out.println("Running ...");
	}

}
