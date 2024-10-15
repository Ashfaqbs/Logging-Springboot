package com.user.controller;

import java.time.DateTimeException;
import java.util.List;
import java.util.Random;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.model.User;
import com.user.service.UserService;
//Basic Logging imports
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

//Apache Log4j imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;





@RestController
public class MainController {

//	   private static final org.apache.commons.logging.Log log = LogFactory.getLog(MainController.class);

	
	private static final Logger log = LogManager.getLogger(MainController.class);
	
	
	@Autowired
	private UserService service;



	//getall
	@GetMapping("/Users")
	public ResponseEntity<List<User>> getAllUSer()
	{
		List<User> getallUsers=null;
		try {
			getallUsers = this.service.getallUsers();
//			System.out.println(getallUsers);
			log.info(getallUsers.toString());
		} catch (Exception e) {
			log.error( e.getMessage().toLowerCase());
		}
		
		return ResponseEntity.ok(getallUsers);

	}


	//getone

	@GetMapping("/Users/{id}")
	public ResponseEntity<User> getUSer(@PathVariable("id") int id)
	{
		User getallUsers=null;
		try {
			getallUsers = this.service.getUser(id);
//			System.out.println(getallUsers);
			log.info(getallUsers.toString());
		} catch (Exception e) {
			log.error( e.getMessage().toLowerCase());
		}
		
		
//		if(getallUsers==null)
//		{
//			     ResponseEntity.status( HttpStatus.NOT_FOUND  ).build();	
//		}
//		else {
//			ResponseEntity.ok(getallUsers);
//		}
		
		return (getallUsers!=null) ? ResponseEntity.ok(getallUsers) : ResponseEntity.status( HttpStatus.NOT_FOUND).build();

	}

	//creating 


	@PostMapping("/Users")
	public ResponseEntity<User> addUSer(@RequestBody User user)
	{
		User getallUsers=null;
		System.out.println(user);
		try {
			getallUsers = this.service.createUser(user);
			//			System.out.println(getallUsers);
			log.info(getallUsers.toString());
		} catch (Exception e) {
			log.error( e.getMessage().toLowerCase());
		}
		return (getallUsers!=null) ? ResponseEntity.ok(getallUsers) : ResponseEntity.badRequest().build();

	}



	@PutMapping("/Users/{id}")
	public ResponseEntity<User> updateUSer(@RequestBody User user, @PathVariable("id") int id )
	{
		User getallUsers=null;
		try {
			getallUsers = this.service.createUser(user);
			System.out.println(getallUsers);
			log.info(getallUsers.toString());
		} catch (Exception e) {
			
			log.error( e.getMessage().toLowerCase());
		}
		return (getallUsers!=null) ? ResponseEntity.ok(getallUsers) : ResponseEntity.badRequest().build();

	}
	
	@ExceptionHandler(NumberFormatException.class)
	public String error( )
	{
		return "Error1";
		//returns error 1 as a response if someone user/String , if we dont use this method then full console exception logs will be showed in as a response
	}

	//delete User

	@DeleteMapping("/Users/{id}")
	public ResponseEntity<String> DeleteUSer(@PathVariable("id") int id )
	{
		String getallUsers=null;
		try {
			getallUsers = this.service.deleteUser(id);
			log.info(getallUsers.toString());
		} catch (Exception e) {
			log.error( e.getMessage().toLowerCase());
		}
		return (getallUsers!=null) ? ResponseEntity.ok(getallUsers) : ResponseEntity.badRequest().build();

	}



	//random 
	@GetMapping("r/Users")
	public User getRandUser()
	{
		User user=null;

		try {


			// List<Integer> givenList = Arrays.asList(1, 2, 3);
			//Random rand = new Random();
			//int randomElement = givenList.get(rand.nextInt(givenList.size()));

			List<User> getallUsers = this.service.getallUsers();
			Random r= new Random();

			user = getallUsers.get(r.nextInt(getallUsers.size()));
			log.info(getallUsers.toString());
		} catch (Exception e) {
			// TODO: handle exception
			log.error( e.getMessage().toLowerCase());
		}
		return user;
	}














}
