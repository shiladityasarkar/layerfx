package com.shiladitya.layerfx;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shiladitya.layerfx.service.StorageService;

@SpringBootApplication
@RestController
@RequestMapping("/image")
public class LayerfxApplication
{
	@Autowired
	private StorageService service;
	
	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image1")MultipartFile image1,@RequestParam("image2")MultipartFile image2 ) throws IOException
	{
		String message = service.upload_image(image1, image2);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@GetMapping("/{image_name}")
	public ResponseEntity<?> downloadImage(@PathVariable int image_name)
	{
		byte[] downloaded_image = service.download_image(image_name);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpg"))
				.body(downloaded_image);
	}
	public static void main(String[] args)
	{
		SpringApplication.run(LayerfxApplication.class, args);
	}

}
