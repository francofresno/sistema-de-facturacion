package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Client;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Invoice;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Product;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Region;

public interface IClientService {
	
	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);
	
	public Client findById(Long id);
	
	public Client save(Client client);
	
	public void delete(Client client);
	
	public boolean existsById(Long id);
	
	public List<Region> findAllRegions();
	
	public Invoice findInvoiceById(Long id);
	
	public Invoice saveInvoice(Invoice invoice);
	
	public void deleteInvoiceById(Long id);
	
	public List<Product> findProductByName(String term);

}
