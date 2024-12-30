package com.sistema.electronico_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.sistema.electronico_backend.repository.mongo")
@EnableR2dbcRepositories(basePackages = "com.sistema.electronico_backend.repository.postgres")
public class ElectronicoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicoBackendApplication.class, args);
	}

}
