package com.ref.arch.elk.logging.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingTestController {

	
	@RequestMapping(value="/hey/mogambo", produces=MediaType.APPLICATION_JSON_VALUE)
	public MogamboReply answer(){
		return new MogamboReply("Mogambo Khush hua");
	}
	
	
}
