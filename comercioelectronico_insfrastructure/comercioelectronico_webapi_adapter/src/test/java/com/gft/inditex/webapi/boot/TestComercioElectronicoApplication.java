package com.gft.inditex.webapi.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude=SecurityAutoConfiguration.class)
public class TestComercioElectronicoApplication{

	 public static void main(String[] args) {
	        SpringApplication.run(TestComercioElectronicoApplication.class, args);
	    }

}