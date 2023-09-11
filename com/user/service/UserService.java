package com.user.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.user.entities.User;

public interface UserService {
	//add user
	public User saveUser(User user);
	//get all the users
	public List<User> getAllUser();
	//get single user by id
	public User getUser(String userId) ;
	//delete the particular user using the id
	public void deleteUser(String userId) throws NotFoundException;
	//update a particular user 
	public User updateUser(User user) ;

}
