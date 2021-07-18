package com.maxis;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MarketplaceAuthenticationServiceApplication {

	public static void main(String[] args) {

		try {

			File keyStore = new File("keystore-db.jks");
			System.out.println("keyStore------: " + keyStore.getAbsolutePath() + ": " + keyStore.exists());

			File certStore = new File("certstore-db.jks");
			System.out.println("certStore------: " + certStore.getAbsolutePath() + ": " + certStore.exists());

			System.setProperty("javax.net.ssl.keyStore", keyStore.getAbsolutePath());
			System.setProperty("javax.net.ssl.keyStorePassword", "1234567");

			System.setProperty("javax.net.ssl.trustStore", certStore.getAbsolutePath());
			System.setProperty("javax.net.ssl.trustStorePassword", "1234567");

		} catch (Exception e) {
			System.out.println(" exception:" + e.getLocalizedMessage());
		}

		SpringApplication.run(MarketplaceAuthenticationServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
