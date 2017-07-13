package com.ref.arch.elk.logging.controller;

import java.io.Serializable;

public class MogamboReply implements Serializable {

	private final String reply;

	public MogamboReply(String reply) {
		super();
		this.reply = reply;
	}

	public String getReply() {
		return reply;
	}
	
}
