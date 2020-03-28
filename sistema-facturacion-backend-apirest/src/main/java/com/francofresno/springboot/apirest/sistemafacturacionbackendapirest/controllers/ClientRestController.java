package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.ffutils.FFutils;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Client;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.Region;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.services.IClientService;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.services.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private IClientService clientService;
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/clients")
	public List<Client> index() {
		return this.clientService.findAll();
	}

	@GetMapping("/clients/page/{page}")
	public Page<Client> index(@PathVariable Integer page) {
		return this.clientService.findAll(PageRequest.of(page, 4));
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Client client = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> finalResponse = null;

		try {
			if (!this.clientService.existsById(id)) {
				response = FFutils.addMessageToMapResponse(response, "mensaje",
						"El cliente ID: ".concat(id.toString().concat(" no existe.")));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // return
																											// with
																											// error
			}

			client = this.clientService.findById(id);
			finalResponse = new ResponseEntity<Client>(client, HttpStatus.OK);

		} catch (DataAccessException e) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"Error al realizar la consulta a la base de datos");
			response = FFutils.addMessageToMapResponse(response, "error",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IllegalArgumentException e) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"El parametro de entrada es invalido");
			response = FFutils.addMessageToMapResponse(response, "error", e.getCause().getMessage());

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		return finalResponse;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/clients")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Client client, BindingResult result) {

		Client clientNew = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Map<String, Object>> finalResponse = null;

		if (result.hasErrors()) {
			List<String> errors = FFutils.bindingResultWithErrorsToStringList(result);
			response = FFutils.addMessageToMapResponse(response, "mensaje", "Error al ingresar datos en la Base de Datos");
			response = FFutils.addListToMapResponse(response, "errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			clientNew = clientService.save(client);
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"El cliente " + clientNew.getFirstName() + " " + clientNew.getLastName() + " ha sido creado con éxito!");
			response.put("client", clientNew);

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"Error al insertar el cliente en la base de datos");
			response = FFutils.addMessageToMapResponse(response, "error",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IllegalArgumentException e) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"El parametro de entrada es invalido");
			response = FFutils.addMessageToMapResponse(response, "error", e.getCause().getMessage());

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		return finalResponse;
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/clients/{id}")
	public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Client client, BindingResult result,
			@PathVariable Long id) {

		Client clientToUpdate = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Map<String, Object>> finalResponse = null;

		if (result.hasErrors()) {
			List<String> errors = FFutils.bindingResultWithErrorsToStringList(result);
			response = FFutils.addListToMapResponse(response, "errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			if (!this.clientService.existsById(id)) {
				response = FFutils.addMessageToMapResponse(response, "mensaje",
						"El cliente ID: ".concat(id.toString().concat(" no existe.")));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // return
																											// with
																											// error
			}

			clientToUpdate = this.clientService.findById(id);
			
			clientToUpdate.setFirstName(client.getFirstName());
			clientToUpdate.setLastName(client.getLastName());
			clientToUpdate.setEmail(client.getEmail());
			clientToUpdate.setCreateAt(client.getCreateAt());
			clientToUpdate.setRegion(client.getRegion());
			
			this.clientService.save(clientToUpdate);

			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"El cliente " + clientToUpdate.getFirstName() + " " + clientToUpdate.getLastName() + " ha sido modificado con éxito!");
			response.put("client", clientToUpdate);
			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		} catch (DataAccessException e) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"Error al actualizar el cliente en la base de datos");
			response = FFutils.addMessageToMapResponse(response, "error",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IllegalArgumentException e) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"El parametro de entrada es invalido");
			response = FFutils.addMessageToMapResponse(response, "error", e.getCause().getMessage());

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		return finalResponse;
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

		Client currentCliente = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Map<String, Object>> finalResponse = null;

		try {
			if (!this.clientService.existsById(id)) {
				response = FFutils.addMessageToMapResponse(response, "mensaje",
						"El cliente ID: ".concat(id.toString().concat(" no existe.")));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // return with error
			}

			currentCliente = this.clientService.findById(id);
			String photoName = currentCliente.getPhoto();
			
			this.uploadFileService.delete(photoName);
			this.clientService.delete(currentCliente);

			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"El cliente " + currentCliente.getFirstName() + " " + currentCliente.getLastName() + " ha sido eliminado con éxito!");
			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (DataAccessException e) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"Error al eliminar el cliente de la base de datos");
			response = FFutils.addMessageToMapResponse(response, "error",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IllegalArgumentException e) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"El parametro de entrada no puede ser nulo");
			response = FFutils.addMessageToMapResponse(response, "error", e.getCause().getMessage());

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		return finalResponse;
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/clients/upload-photo")
	public ResponseEntity<Map<String, Object>> uploadPhoto(@RequestParam("file") MultipartFile file,
			@RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Map<String, Object>> finalResponse = null;

		if (!this.clientService.existsById(id)) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"El cliente ID: ".concat(id.toString().concat(" no existe.")));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // return with error
		}

		if (file.isEmpty()) {
			response = FFutils.addMessageToMapResponse(response, "mensaje",
					"El arhivo: ".concat(file.getOriginalFilename().concat(" está vacío.")));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); // return with error
		}
		
		String fileName = null;
		try {
			fileName = this.uploadFileService.copy(file);

		} catch (IOException e) {
			response = FFutils.addMessageToMapResponse(response, "mensaje", "Error al subir la imagen");
			response = FFutils.addMessageToMapResponse(response, "error", e.getCause().getMessage());

			finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Client client = clientService.findById(id);
		String photoName = client.getPhoto();
		
		this.uploadFileService.delete(photoName);
		
		client.setPhoto(fileName);
		
		this.clientService.save(client);
		
		response.put("client", client);
		response = FFutils.addMessageToMapResponse(response, "mensaje",
				"La imagen se ha subido correctamente");
		
		finalResponse = new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		return finalResponse;
	}

	@GetMapping("/uploads/img/{photoName:.+}")
	public ResponseEntity<Resource> downloadPhoto(@PathVariable String photoName) {
		Resource resource = null;

		try {
			resource = this.uploadFileService.upload(photoName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders header = new HttpHeaders();
		
		// Para forzar la descarga
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/clients/regions")
	public List<Region> listRegions() {
		return this.clientService.findAllRegions();
	}

}
