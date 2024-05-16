package com.example.importexportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ImportExportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImportExportServiceApplication.class, args);
	}

}
