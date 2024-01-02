package com.shiladitya.layerfx.service;

import java.io.IOException;
//import java.net.http.HttpHeaders;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shiladitya.layerfx.entity.ImageData;
//import com.shiladitya.layerfx.util.ImageUtils;
import com.shiladitya.layerfx.repository.StorageRepository;
//import com.shiladitya.layerfx.util.ImageUtils;

@Service
public class StorageService
{
	public byte[] convert(byte[] image1, byte[] image2)
	{
		return image1;
	}
	@Autowired
	private StorageRepository repository;
	public String upload_image(MultipartFile image1, MultipartFile image2) throws IOException
	{
		ImageData image_data = repository.save(ImageData.builder()
//						.name(image.getOriginalFilename())
//						.type(image.getContentType())
//						.image_data1(ImageUtils.compressImage(image1.getBytes()))
//						.image_data2(ImageUtils.compressImage(image2.getBytes())).build());
						.image_data1(image1.getBytes())
						.image_data2(image2.getBytes())
						.image_conv(convert(image1.getBytes(), image2.getBytes())).build());
		if(image_data!=null)
			return "Image "+image1.getOriginalFilename()+" and "+image2.getOriginalFilename()+" uploaded successfully!";
		return null;
	}
	
	public byte[] download_image(int id)
	{
		Optional<ImageData> dbimage = repository.findById(id);
		byte[] image = dbimage.get().getImage_conv();
//		byte[] image2 = ImageUtils.decompressImage(dbimage.get().getImage_data2());
		return image;
	}
}
