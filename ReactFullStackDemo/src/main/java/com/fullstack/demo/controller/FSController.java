package com.fullstack.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.demo.exceptions.UserNotFoundException;
import com.fullstack.demo.model.UserDb;
import com.fullstack.demo.repo.UserRepo;

@RestController
@CrossOrigin("http://localhost:3000") // frontend running in this port only allowed to access the controller to get
										// data
public class FSController {

	@Autowired
	UserRepo repo;

	// creating new User
	@PostMapping("/user")
	private boolean newUser(@RequestBody UserDb user) {
		try {
			repo.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// showing all users
	@GetMapping("/users")
	private List<UserDb> getUsers() {
		List<UserDb> users = repo.findAll();
		return users;
	}

	// showing a particular user with id
	@GetMapping("/userof/{id}")
	UserDb getUserById(@PathVariable long id) {
		return repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	// update a user
	@PutMapping("/upuser/{id}")
	UserDb updateUser(@RequestBody UserDb userUp, @PathVariable long id) {
		return repo.findById(id).map(user -> {
			user.setName(userUp.getName());
			user.setUsername(userUp.getUsername());
			user.setEmail(userUp.getEmail());
			user.setSalary(userUp.getSalary());
			return repo.save(user);
		}).orElseThrow(() -> new UserNotFoundException(id));
	}

	@DeleteMapping("/deleteuser/{id}")
	List<String> deleteUser(@PathVariable long id) {
		if (!repo.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		repo.deleteById(id);
		List<String> message = new ArrayList<>();
		message.add("User with id : " + id + " deleted");
		return message;
	}
}
