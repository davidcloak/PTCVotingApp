package com.votesystem.voteserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class VoteserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteserverApplication.class, args);
	}

	@RequestMapping("/")
	String home(){
		return "<h1>Databse is alive</h1>";
	}
}
