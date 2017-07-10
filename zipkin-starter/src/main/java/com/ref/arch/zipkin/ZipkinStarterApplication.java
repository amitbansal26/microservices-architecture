package com.ref.arch.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableZipkinStreamServer
public class ZipkinStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinStarterApplication.class, args);
	}
}
