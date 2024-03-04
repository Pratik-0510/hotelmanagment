package hms.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hms.jparepo.UserRope;
import hms.pojo.User;


@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	private UserRope userRope;
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		
		// Check if the email already exists in the database
		User existingUser = userRope.findByEmail(user.getEmail());
		if (existingUser != null) {
			// Email already registered, return error response
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
		} else {
			// Email not found, proceed with registration
			userRope.save(user);
			return ResponseEntity.ok("Registration successful");
		}
	}
	@PostMapping("/login") 
	public ResponseEntity<String> login(@RequestBody User user) {
		//Check if the email with corresponding password is exists in the database
		User existingUser = userRope.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (existingUser != null) {//if exist then condition will be satisfied and returns success massage to the front-end
			return ResponseEntity.ok("Success");
		} else {//if not the condition will be un satisfied and returns the error massage
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error");
		}
	}

}
