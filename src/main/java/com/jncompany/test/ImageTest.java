package com.jncompany.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.coobird.thumbnailator.Thumbnails;

public class ImageTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		File image = new File("D:"+File.separator+"20170417163556_ANETWHdu_3F3F.jpg");
		File thumbnail = new File("D:"+File.separator+"thumbnail.png");
		if (image.exists()) {
			thumbnail.getParentFile().mkdirs();
			Thumbnails.of(new URL("http://cache.clien.net/cs2/data/file/park/thumb/728x0_70/20170417165844_1ms0CqMe_123.jpg"))
				.size(150, 100)
				.outputFormat("png")
				.toFile(thumbnail);
			
		}

	}

}
