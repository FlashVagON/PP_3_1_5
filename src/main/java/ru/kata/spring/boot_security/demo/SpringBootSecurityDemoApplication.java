package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

//	public static UserServiceImpl userService;
//
//    public SpringBootSecurityDemoApplication(UserServiceImpl userService) {
//        this.userService = userService;
//    }


    public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		openHomePage();



//        userService.addUser(new User("admin", "ad", 23, "123"));
//		userService.addUser(new User("user", "us", 31, "123"));
//		userService.addUser(new User("user2", "us2", 25, "123"));
	}
	private static void openHomePage() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8088/login");
	}

}
