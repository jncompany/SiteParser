package com.jncompany.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jncompany.util.CompareCntDesc;
import com.jncompany.vo.ItemVo;

public class JobParse {

	static String baseUrl = "http://www.ppomppu.co.kr/zboard/";
	static String parseUrl = "http://www.jobkorea.co.kr/list_gi/gi_part_search.asp?Part_No=35300&pay_type_code=1&selText=xplatform&page=";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JobParse ps = new JobParse();
		
		List<ItemVo> bbsList = new ArrayList<ItemVo>() ;
		
		try {
			
			
			//2페이지 까지 추출..
			for(int i=1; i<=2; i++){
				Document doc = Jsoup.connect(parseUrl+i).get(); 
				Elements contents = doc.select("div.tplList.tplJobList");
				
				//Contents Parse
				List<ItemVo> bbsList1 = ps.getParseElement(contents);
				bbsList.addAll(bbsList1);	
			}
			
			//리스트 소트(cnt 역순)
			Collections.sort(bbsList,new CompareCntDesc());
			int cRow = 1;
			for (ItemVo bbs : bbsList) {
				
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
					String cont = detailEl.text().length() > 50
								? detailEl.text().substring(0, 50) + "..."
								: detailEl.text();
					

					bbs.setImgsrc(imgUrl);//imgUrl
					bbs.setContent(imgStr + cont);//imgStr + cont

				}
				
				System.out.println(bbs.toString());
				cRow++;
			}


		} catch (IOException e) { // Jsoup의 connect 부분에서 IOException 오류가 날 수
									// 있으므로 사용한다.
			e.printStackTrace();
		}


	}
	
	//Contents Parse
	private List<ItemVo> getParseElement(Elements contents){
		
		List<ItemVo> bbsList = new ArrayList<ItemVo>() ;
		
		try {
			
			for (Element el : contents) {

				ItemVo bbs = new ItemVo();

				String subject = el.getElementsByTag("td").eq(2).text();
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
