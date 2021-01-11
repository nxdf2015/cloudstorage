package com.udacity.jwdnd.course1.cloudstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * /home
 * 		get /
 *
 * /note
 *    post /
 *    post /edit/id
 *    get /delete/id
 *
 *  Todo configure authentication
 *  todo uodate view login error and link to signup
 *  todo create controller to signup
 *  todo create create method in userservice to create a user
 *  todo update authenticationProvider verify if password  matches when user log
 *
 */

@SpringBootApplication
public class CloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}

}
