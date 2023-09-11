package com.user.controller;

import com.user.ServiceImpl.UserServiceImpl;
import com.user.customException.ResourceNotFound;

import feign.ResponseMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.user.entities.User;
import com.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	//create handler
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
		try {
			User user1 = this.userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	//counting how many times we did retried
//	int retryCount = 1;
	@GetMapping("/{userId}")
	@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod="ratingHotelFallback")
	//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
//	@RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId) {
//		logger.info("Retry count: {}",retryCount);
//		retryCount++;

		return ResponseEntity.ok(this.userService.getUser(userId));
				
	}
	//creating fallback methods for circuit breaker
	//this method will only run when a service is down nd not working
	//the return type pf the fallback methodshould be same as of the method on whihc we applies the @
	//circuitBreaker annotation
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		logger.info("Fallback is executed because service is down: ",ex.getMessage());
		User user = User.builder()
				.email("dummy@gmail.com")
				.name("Dummy")
				.about("This user is created dummy because some service is down")
				.userId("1234")
				.build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	//method for retry concept of resilience 4j


//	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//
//		User user = User.builder()
//				.email("dummy@gmail.com")
//				.name("Dummy")
//				.about("This user is created dummy because some service is down")
//				.userId("1234")
//				.build();
//		return new ResponseEntity<>(user,HttpStatus.OK);
//	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers(){


		return ResponseEntity.ok(this.userService.getAllUser());
	}

	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable("userId") String userId) throws NotFoundException {
		if(userId==null){
			throw new ResourceNotFound("id is not foundot be deleted please try again !!");
		}
		this.userService.deleteUser(userId);
	}


	@PutMapping("/{userId}")
	public ResponseEntity<User> updateTheUser(@PathVariable("userId") String userId, @RequestBody User user){
		if(userId==null){
			throw new ResourceNotFound("Please enter valid user id and try again !!");
		}

		user.setUserId(userId);
		return ResponseEntity.ok(this.userService.updateUser(user));

	}
	
	
	

}
