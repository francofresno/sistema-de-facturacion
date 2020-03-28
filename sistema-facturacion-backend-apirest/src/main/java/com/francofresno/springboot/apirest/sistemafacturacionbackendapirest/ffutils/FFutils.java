/**
 * @author Franco Fresno
 */

package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.ffutils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class FFutils {
	public static Map<String, Object> addMessageToMapResponse(Map<String, Object> anOriginalResponse, String messageKey, String messageValue) {
		Map<String, Object> response = anOriginalResponse;
		
		response.put(messageKey, messageValue);	
		return response;
	}
	
	public static Map<String, Object> addListToMapResponse(Map<String, Object> anOriginalResponse, String messageKey, List<String> messages) {
		Map<String, Object> response = anOriginalResponse;
		
		response.put(messageKey, messages);	
		return response;
	}
	
	public static List<String> bindingResultWithErrorsToStringList(BindingResult result) {
		List<String> errors = result.getFieldErrors()
				.stream()
				.map(error -> "Error '" + error.getField().toUpperCase() + "': " + error.getDefaultMessage())
				.collect(Collectors.toList());
		
		return errors;
	}
	
	public static Path getAbsolutePath(String path, String fileName) {
		return Paths.get(path).resolve(fileName).toAbsolutePath();
	}
	
	public static void deleteFileIfExist(Path pathFile) {
		File file = pathFile.toFile();
		if(file.exists() && file.canRead())
			file.delete();		
	}
}
