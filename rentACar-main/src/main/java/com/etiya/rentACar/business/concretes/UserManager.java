package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.UserService;
import com.etiya.rentACar.business.dtos.UserSearchListDto;
import com.etiya.rentACar.business.request.userRequests.CreateUserRequest;
import com.etiya.rentACar.business.request.userRequests.DeleteUserRequest;
import com.etiya.rentACar.business.request.userRequests.UpdateUserRequest;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.ErrorDataResult;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.UserDao;
import com.etiya.rentACar.entities.User;

@Service
public class UserManager implements UserService {
	
	private UserDao userDao;
	
	private ModelMapperService modelMapperService;

	@Autowired
	public UserManager(UserDao userDao, ModelMapperService modelMapperService) {
		super();
		this.userDao = userDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<UserSearchListDto>> getAll() {
		List<User> result = this.userDao.findAll();
		List<UserSearchListDto> response = result.stream().map(user -> modelMapperService.forDto()
				.map(user, UserSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<UserSearchListDto>>(response);
	}

	@Override
	public Result save(CreateUserRequest createUserRequest) {
		User user= modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userDao.save(user);
		return new SuccessResult("User added.");
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		User user= modelMapperService.forRequest().map(deleteUserRequest, User.class);
		this.userDao.delete(user);
		return new SuccessResult("User deleted.");
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user= modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userDao.save(user);
		return new SuccessResult("User updated.");
	}

	@Override
	public Result existsByEmail(String email) {
		if (this.userDao.existsByeMail(email)) {
			return new ErrorResult();
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		if(this.userDao.existsByeMail(email)) {
			return new SuccessDataResult<User>(this.userDao.getByeMail(email));	
		}
		return new ErrorDataResult<User>(null);
	}

	@Override
	public User getById(int userId) {
		return userDao.getById(userId);
	}

}
