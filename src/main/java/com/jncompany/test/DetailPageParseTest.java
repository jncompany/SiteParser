package com.jncompany.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DetailPageParseTest {


	public static void main(String[] args) {

		
		String baseUrl = "http://www.jobkorea.co.kr";
		String parseUrl = "http://www.jobkorea.co.kr/list_gi/gi_part_search.asp?Part_No=35300&pay_type_code=1&selText=nexacro&page=1";
		
		try {
				
			Document detailDoc = Jsoup.connect(parseUrl).get();
			// 웹에서 내용을 가져온다.
			Elements detailContents = detailDoc.select("div.tplList.tplJobList tbody tr"); //td table tbody tr td table	//table td.han
			for (Element detailEl : detailContents) {

				
				System.out.println("subj : "+detailEl.getElementsByTag("td").eq(0).text());
				System.out.println("cont : "+detailEl.getElementsByClass("tplTit").eq(0).text());
				System.out.println("link : "+baseUrl+detailEl.getElementsByTag("a").eq(1).attr("href").toString());
				
				//System.out.println(detailEl.select("img").attr("src").toString());
				//System.out.println(detailEl.toString());
				
				System.out.println("");
			}

		} catch (Exception e) { // Jsoup의 connect 부분에서 IOException 오류가 날 수
									// 있으므로 사용한다.
			e.printStackTrace();
		}


	}
	
}
