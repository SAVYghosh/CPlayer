package com.stackroute.userservice.services;

import com.stackroute.userservice.exception.UserAlreadyExsitsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;

public interface UserService {

	boolean saveUser(User user) throws UserAlreadyExsitsException;

	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
