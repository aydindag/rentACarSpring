package com.etiya.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	boolean existsByeMail(String eMail);
	User getByeMail(String eMail);

}

