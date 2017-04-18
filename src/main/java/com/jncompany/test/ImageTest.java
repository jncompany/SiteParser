package com.jncompany.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.coobird.thumbnailator.Thumbnails;

public class ImageTest {

	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/imgFolder/";
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String imgUrl = "http://cache.clien.net/cs2/data/file/park/thumb/728x0_70/20170418115928_93BP1khF_1.png";
		String imgFileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1, imgUrl.lastIndexOf("."));
		
		File tempDirect = new File(UPLOADED_FOLDER);
		if(!tempDirect.exists()){
			tempDirect.mkdirs();
		}
		
		File thumbnail = new File(UPLOADED_FOLDER+imgFileName+".png");
		Thumbnails.of(new URL(imgUrl))
			.size(150, 100)
			.outputFormat("png")
			.toFile(thumbnail);
			

	}

}
