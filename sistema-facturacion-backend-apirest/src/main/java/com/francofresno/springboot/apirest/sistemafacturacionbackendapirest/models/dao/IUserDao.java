package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.EndUser;

public interface IUserDao extends CrudRepository<EndUser, Long>{
	
	public EndUser findByUsername(String username);
	
//	@Query("select u from User u where u.username=?1")
//	public User findByUsername(String username);
// Otra manera de hacer el select
	
}
