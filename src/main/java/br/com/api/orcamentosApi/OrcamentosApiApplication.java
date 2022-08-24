package br.com.api.orcamentosApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class OrcamentosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrcamentosApiApplication.class, args);
	}

}
