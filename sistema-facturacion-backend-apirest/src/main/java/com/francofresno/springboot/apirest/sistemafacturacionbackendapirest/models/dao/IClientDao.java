package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Client;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Region;

public interface IClientDao extends JpaRepository<Client, Long>{
	
	@Query("from Region")
	public List<Region> findAllRegions();
}
