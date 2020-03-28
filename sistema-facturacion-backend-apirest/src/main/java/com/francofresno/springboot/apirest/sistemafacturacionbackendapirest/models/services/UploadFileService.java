package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.ffutils.FFutils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(UploadFileService.class);
	private final static String UPLOAD_DIRECTORY = "uploads";

	@Override
	public Resource upload(String photoName) throws MalformedURLException {
		Resource resource = null;
		Path filePath = this.getPath(photoName);
		log.info(filePath.toString());

		resource = new UrlResource(filePath.toUri());

		if (!resource.exists() && !resource.isReadable()) {
			filePath = FFutils.getAbsolutePath("src/main/resources/static/images", "default.png");
			resource = new UrlResource(filePath.toUri());
			log.error("No se pudo cargar la imagen " + photoName);
		}

		return resource;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
		Path filePath = this.getPath(fileName);

		log.info(filePath.toString());

		Files.copy(file.getInputStream(), filePath);

		return fileName;
	}

	@Override
	public boolean delete(String photoName) {
		boolean ret = false;
		if (photoName != null && photoName.length() > 0) {
			Path fileToDelete = FFutils.getAbsolutePath("uploads", photoName);
			FFutils.deleteFileIfExist(fileToDelete);
			ret = true;
		}
		return ret;
	}

	@Override
	public Path getPath(String photoName) {
		return FFutils.getAbsolutePath(UPLOAD_DIRECTORY, photoName);
	}

}
