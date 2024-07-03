package com.gft.inditex.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gft.inditex.domain","com.gft.inditex.persistencesql"})
@EnableJpaRepositories({"com.gft.inditex.persistencesql.repository"})
@EntityScan("com.gft.inditex.persistencesql.entity")

public class ComercioElectronicoApplication {

	public static void main(String[] args) {
		
	    SpringApplication comercioElectronico = new SpringApplication(ComercioElectronicoApplication.class);	    
	    comercioElectronico.run(args);
	}
	
}
