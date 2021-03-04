package com.jsegomez.springadmin.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageImpl implements UploadImage {
	
	private final static String route = "C:/Users/esmet/Documents/Proyectos/Spring-Angular/images-upload/";
	
	// Método para manejar el PATH
	@Override
	public Path getPath(String image) {
		return Paths.get(route).resolve(image).toAbsolutePath();
	}

	// Método para recuperar imagen
	@Override
	public Resource showImg(String image) throws MalformedURLException {
		Path filePath = getPath(image);
		Resource resource = new UrlResource(filePath.toUri());
		
		if(!resource.exists() && !resource.isReadable()) {
			throw new RuntimeException("No fue posible cargar imagen");
		}
		return resource;
	}
	
	// Método para guardar nueva imagen
	@Override
	public String upload(MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID().toString().concat("_").concat(file.getOriginalFilename().replace(" ","-"));
		Path filePath = getPath(fileName);
		
		Files.copy(file.getInputStream(), filePath);
		return fileName;
	}

	@Override
	public boolean delete(String image) {
		if(image != null && image.length() > 0) {
			Path prevImage = getPath(image);
			File deleteImage = prevImage.toFile();
			if(deleteImage.exists() && deleteImage.canRead()) {
				deleteImage.delete();
				return true;
			}
		}
		return false;
	}

}
