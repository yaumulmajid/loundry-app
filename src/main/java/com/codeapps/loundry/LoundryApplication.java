package com.codeapps.loundry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.codeapps.loundry.entity"})
public class LoundryApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoundryApplication.class, args);
	}

}
