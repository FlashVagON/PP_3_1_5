package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

//	public static UserServiceImpl userService;
//
//    public SpringBootSecurityDemoApplication(UserServiceImpl userService) {
//        this.userService = userService;
//    }


    public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);

//        userService.addUser(new User("admin", "ad", 23, "123"));
//		userService.addUser(new User("user", "us", 31, "123"));
//		userService.addUser(new User("user2", "us2", 25, "123"));
	}

}
