package com.jncompany.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jncompany.dao.PostRepository;
import com.jncompany.util.CompareCntDesc2;
import com.jncompany.util.Thumbnailtor;
import com.jncompany.vo.PostVo;

@Service
public class PpomParseService {

	@Autowired
	private PostRepository dao;

	static String baseUrl = "http://www.ppomppu.co.kr/zboard/";
	static String parseUrl = "http://www.ppomppu.co.kr/zboard/zboard.php?id=freeboard&page=";

	public void processParse() {

		List<PostVo> bbsList = new ArrayList<PostVo>() ;
		
		try {
			
			
			//2페이지 까지 추출..
			for(int i=1; i<=2; i++){
				Document doc = Jsoup.connect(parseUrl+i).get(); 
				Elements contents = doc.select("tr.list1");
				
				//Contents Parse
				List<PostVo> bbsList1 = getParseElement(contents);
				bbsList.addAll(bbsList1);	
			}
			
			//리스트 소트(cnt 역순)
			Collections.sort(bbsList,new CompareCntDesc2());
			int cRow = 1;
			for (PostVo bbs : bbsList) {
				
				//10건만 출력
				if(cRow >= 10 ){
					break;
				}
				
				Document detailDoc = Jsoup.connect(bbs.getLink()).get();
				// 웹에서 내용을 가져온다.
				Elements detailContents = detailDoc.select("td table tbody tr td table tbody tr"); //td table tbody tr td table	//table td.han
				for (Element detailEl : detailContents) {

					String imgUrl = detailEl.select("img").attr("src").toString();
					String imgStr = "".equals(detailEl.select("img").attr("src").toString()) ? "" : "<첨부이미지> ";
					String cont = detailEl.text().length() > 100
								? detailEl.text().substring(0, 100) + "..."
								: detailEl.text();
					

					bbs.setImgsrc(imgUrl);//imgUrl
					bbs.setContent(imgStr + cont);//imgStr + cont
					if(!"".equals(imgUrl)){
						bbs.setImgThumnailSrc(Thumbnailtor.generageImage(imgUrl));
					}

				}
				
				System.out.println(bbs.toString());
				cRow++;
				
				bbs.setSite("ppom");
				dao.save(bbs);
			}


		} catch (IOException e) { // Jsoup의 connect 부분에서 IOException 오류가 날 수
									// 있으므로 사용한다.
			e.printStackTrace();
		}

	}


	//Contents Parse
	private List<PostVo> getParseElement(Elements contents){
		
		List<PostVo> bbsList = new ArrayList<PostVo>() ;
		
		try {
			
			for (Element el : contents) {

				PostVo bbs = new PostVo();

				String subject = el.getElementsByTag("td").eq(2).select("a").text();
				String detailUrl = baseUrl + el.getElementsByTag("td").eq(2).select("a").attr("href").toString();

				bbs.setSubject(subject);;
				bbs.setLink(detailUrl);

				bbs.setTime(el.getElementsByTag("td").eq(3).text());
				bbs.setCnt(Integer.parseInt(el.getElementsByTag("td").eq(5).text()));
				bbsList.add(bbs);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bbsList;
	}
	
}
