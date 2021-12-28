package com.etiya.rentACar.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.UserService;
import com.etiya.rentACar.business.dtos.UserSearchListDto;
import com.etiya.rentACar.business.request.userRequests.CreateUserRequest;
import com.etiya.rentACar.business.request.userRequests.DeleteUserRequest;
import com.etiya.rentACar.business.request.userRequests.UpdateUserRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/user")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	@GetMapping("list")
	public DataResult<List<UserSearchListDto>> getAll(){
		return this.userService.getAll();
	}
}
