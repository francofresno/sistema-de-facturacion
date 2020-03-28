package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Invoice;

public interface IInvoiceDao extends CrudRepository<Invoice, Long> {
	

}
