package com.example.dogclient;

import com.example.dogclient.api.Api;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication(proxyBeanMethods = false)
public class DogClientApplication {

	@Bean
	Api api(RestClient.Builder restClientBuilder) {
		RestClient restClient = restClientBuilder.baseUrl("http://localhost:8080")
			.requestInterceptor(new BasicAuthenticationInterceptor("user", "password"))
			.build();
		RestClientAdapter adapter = RestClientAdapter.create(restClient);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
		return factory.createClient(Api.class);
	}

	@Bean
	ApplicationRunner applicationRunner(Api api) {
		return (args) -> {
			System.err.println(api.dogs(true));
			System.err.println(api.ownedDogs("tommy"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DogClientApplication.class, args);
	}

}
