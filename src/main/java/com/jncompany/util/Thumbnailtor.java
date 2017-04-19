package com.jncompany.util;

import java.io.File;
import java.net.URL;

import net.coobird.thumbnailator.Thumbnails;

public class Thumbnailtor {
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/imgFolder/";
    //private static String UPLOADED_FOLDER = "/WEB-INF/imgFolder/";
	public static String generageImage(String imgUrl) {
		
		String imgFileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1, imgUrl.lastIndexOf("."));
		File tempDirect = new File(UPLOADED_FOLDER);
		if(!tempDirect.exists()){
			tempDirect.mkdirs();
		}
		
		File thumbnail = new File(UPLOADED_FOLDER+imgFileName+".png");
		
		try {
			
			if(!thumbnail.exists()){
				
				Thumbnails.of(new URL(imgUrl))
					//.sourceRegion(Positions.CENTER, 300, 300)
					.size(150, 100)
					.keepAspectRatio(false)
					.outputFormat("png")
					.toFile(thumbnail);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} 
		
		return imgFileName+".png";
	}

}
