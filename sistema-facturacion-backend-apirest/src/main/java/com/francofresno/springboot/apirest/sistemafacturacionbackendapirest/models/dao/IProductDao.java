package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long> {
	
	@Query("select p from Product p where p.pName like %?1%")
	public List<Product> findByName(String term);

}
