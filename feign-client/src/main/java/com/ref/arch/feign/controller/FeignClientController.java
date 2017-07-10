package com.ref.arch.feign.controller;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ref.arch.feign.client.SampleClient;

import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;

@RestController
public class FeignClientController {

	static {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
	            public boolean verify(String hostname, SSLSession session){
	                    return true;
	            }
	        });
	}
	private SampleClient client;
	
	@Autowired
	public FeignClientController(
			Decoder decoder, Encoder encoder, Client client) {
		this.client = Feign.builder().client(client)
				.encoder(encoder)
				.decoder(decoder)
				//.requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
				.target(SampleClient.class, "https://TEST-SERVICE");  //PROD-SVC is the name of the service registered in the discovery server
		
	}
	
	
	@RequestMapping("/feign")
	public String getData(){
		return client.getString();
	}
}
