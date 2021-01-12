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
 *  Todo update credentials files [add foreign key userid at all request]
 *
 */

@SpringBootApplication
public class CloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}

}
