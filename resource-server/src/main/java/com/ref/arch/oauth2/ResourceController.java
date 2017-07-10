package com.ref.arch.oauth2;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class ResourceController {

	 @RequestMapping(method = RequestMethod.GET)
	    public String readFoo() {
	        return "read sample " + UUID.randomUUID().toString();
	    }

	    @PreAuthorize("hasAuthority('amit_write')")
	    @RequestMapping(method = RequestMethod.POST)
	    public String writeFoo() {
	        return "write sample " + UUID.randomUUID().toString();
	    }
}
