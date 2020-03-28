package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.dao.IClientDao;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.dao.IInvoiceDao;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.dao.IProductDao;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Client;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Invoice;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Product;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Region;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;
	@Autowired
	private IInvoiceDao invoiceDao;
	@Autowired
	private IProductDao productDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) this.clientDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Client> findAll(Pageable pageable) {
		return clientDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		return this.clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Client save(Client client) {
		return this.clientDao.save(client);
	}

	@Override
	@Transactional
	public void delete(Client client) {
		this.clientDao.delete(client);
	}

	@Override
	public boolean existsById(Long id) {
		return this.clientDao.existsById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegions() {
		return clientDao.findAllRegions();
	}

	@Override
	@Transactional(readOnly = true)
	public Invoice findInvoiceById(Long id) {
		return this.invoiceDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Invoice saveInvoice(Invoice invoice) {
		return this.invoiceDao.save(invoice);
	}

	@Override
	@Transactional
	public void deleteInvoiceById(Long id) {
		this.invoiceDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findProductByName(String term) {
		return this.productDao.findByName(term);
	}

}
