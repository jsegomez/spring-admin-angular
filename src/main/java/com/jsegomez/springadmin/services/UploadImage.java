package com.jsegomez.springadmin.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadImage {
	
	// Mostrar imagen
	public Resource showImg(String image) throws MalformedURLException;
	
	// Método para cargar imagen
	public String upload(MultipartFile file) throws IOException;
	
	// Método para eliminar imagen
	public boolean delete(String image);
	
	// Método para obtener el path de la imagen
	public Path getPath(String image);

}



