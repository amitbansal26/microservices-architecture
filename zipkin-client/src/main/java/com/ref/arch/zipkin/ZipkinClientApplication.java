package com.ref.arch.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ZipkinClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinClientApplication.class, args);
	}
	
	
	@GetMapping
	public String sayHello(){
		return "hello";
	}
}
