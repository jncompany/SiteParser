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
public class ClienParseService {

	@Autowired
	private PostRepository dao;

	static String baseUrl = "http://www.clien.net/cs2";
	static String parseUrl = "http://www.clien.net/cs2/bbs/board.php?bo_table=park";// &page=1

	public void processParse() {

		List<PostVo> bbsList = new ArrayList<PostVo>();

		try {

			// 2페이지 까지 추출..
			for (int i = 1; i <= 2; i++) {
				Document doc = Jsoup.connect(parseUrl + "&page=" + i).get();
				Elements contents = doc.select("tr.mytr");

				// Contents Parse
				List<PostVo> bbsList1 = getParseElement(contents);
				bbsList.addAll(bbsList1);
			}

			// 리스트 소트(cnt 역순)
			Collections.sort(bbsList, new CompareCntDesc2());
			int cRow = 1;
			for (PostVo bbs : bbsList) {

				// 10건만 출력
				if (cRow >= 10) {
					break;
				}

				Document detailDoc = Jsoup.connect(bbs.getLink()).get();
				Elements detailContents = detailDoc.select("div.resContents");
				for (Element detailEl : detailContents) {

					String imgUrl = detailEl.getElementsByClass("attachedImage").select("img").eq(0).attr("src")
							.toString();
					String imgStr = detailEl.getElementsByClass("attachedImage").size() > 0 ? "<첨부이미지> " : "";
					String cont = detailEl.getElementById("writeContents").text().length() > 100
							? detailEl.getElementById("writeContents").text().substring(0, 100) + "..."
							: detailEl.getElementById("writeContents").text();

					bbs.setImgsrc(imgUrl);
					bbs.setContent(imgStr + cont);
					
					if(!"".equals(imgUrl)){
						bbs.setImgThumnailSrc(Thumbnailtor.generageImage(imgUrl));
					}

				}

				System.out.println(bbs.toString());
				cRow++;
				
				bbs.setSite("clien");
				dao.save(bbs);
			}

		} catch (IOException e) { // Jsoup의 connect 부분에서 IOException 오류가 날 수
									// 있으므로 사용한다.
			e.printStackTrace();
		}

	}

	// Contents Parse
	private List<PostVo> getParseElement(Elements contents) {

		List<PostVo> bbsList = new ArrayList<PostVo>();

		try {

			for (Element el : contents) {

				PostVo bbs = new PostVo();

				String subject = el.getElementsByTag("a").text();
				String detailUrl = baseUrl + el.getElementsByTag("a").attr("href").replace("..", "");

				bbs.setSubject(subject);
				bbs.setLink(detailUrl);


				bbs.setTime(el.getElementsByTag("td").eq(3).text());
				bbs.setCnt(Integer.parseInt(el.getElementsByTag("td").eq(4).text()));
				bbsList.add(bbs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bbsList;
	}

}
