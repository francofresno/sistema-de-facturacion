package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.services;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.EndUser;

public interface IUserService {

	public EndUser findByUsername(String username);
}
