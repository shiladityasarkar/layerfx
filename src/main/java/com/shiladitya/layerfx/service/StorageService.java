package com.shiladitya.layerfx.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import java.net.http.HttpHeaders;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.shiladitya.layerfx.entity.ImageData;
//import com.shiladitya.layerfx.util.ImageUtils;
import com.shiladitya.layerfx.repository.StorageRepository;
//import com.shiladitya.layerfx.util.ImageUtils;
import application.ImageStegan;


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
		ImageStegan ob = new ImageStegan();
		Image image_stegan = ob.embed(bytetoImg(image1.getBytes()), bytetoImg(image2.getBytes())); 
		ImageData image_data = repository.save(ImageData.builder()
//						.name(image.getOriginalFilename())
//						.type(image.getContentType())
//						.image_data1(ImageUtils.compressImage(image1.getBytes()))
//						.image_data2(ImageUtils.compressImage(image2.getBytes())).build());
						.image_data1(image1.getBytes())
						.image_data2(image2.getBytes())
//						.image_conv(convert(image1.getBytes(), image2.getBytes())).build());
//						.image_conv(ob.embed(new Image(image1.getInputStream()),
//									new Image(image2.getInputStream()))).build());
						.image_conv(Imgtobytes(image_stegan)).build());
		if(image_data!=null)
			return image1.getOriginalFilename()+" and "+image2.getOriginalFilename()+" uploaded successfully!\n"+
					"Sshhh... I have generated your unique id -> "+image_data.getId()+" (:\nRemember it to download your encrypted image!";
		return null;
	}
	
	public byte[] download_image(int id)
//	public Image download_image(int id)
	{
		Optional<ImageData> dbimage = repository.findById(id);
		byte[] image = dbimage.get().getImage_conv();
//		Image image = dbimage.get().getImage_conv();
//		byte[] image2 = ImageUtils.decompressImage(dbimage.get().getImage_data2());
		return image;
	}
	
	public static Image bytetoImg(byte[] byteArray)
	{
		try {
            ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
            return new Image(bis);
        } catch (Exception e) {
//            e.printStackTrace();
        	System.out.println("Exception raised while trying to convert byte array to javafx Image.");
            return null;
        }
		
	}
	
	public static byte[] Imgtobytes(Image image)
	{
		try {
            // Convert JavaFX Image to BufferedImage
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

            // Write BufferedImage to ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);

            // Get the byte array
            byte[] byteArray = baos.toByteArray();

            return byteArray;
        } catch (IOException e) {
        	System.out.println("Exception raised while trying to convert javafx Image to bytes");
//            e.printStackTrace();
            return null;
        }
	}
}
