package com.jsegomez.springadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendAdminApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEnconder;

	public static void main(String[] args) {
		SpringApplication.run(BackendAdminApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		String password = "123456";
		
		for(int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEnconder.encode(password);
			System.out.println(passwordBcrypt);
		}
	}

}
