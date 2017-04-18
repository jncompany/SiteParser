package com.jncompany.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostVo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String site;
	private String subject;
	private String link;
	
	@Column( length = 1000 )
	private String imgsrc;
	private String imgThumnailSrc;
	private String content;
	private String time;
	private int cnt;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public String getImgThumnailSrc() {
		return imgThumnailSrc;
	}
	public void setImgThumnailSrc(String imgThumnailSrc) {
		this.imgThumnailSrc = imgThumnailSrc;
	}
	@Override
	public String toString() {
		return "PostVo [id=" + id + ", site=" + site + ", subject=" + subject + ", link=" + link + ", imgsrc=" + imgsrc + ", imgThumnailSrc ="+ imgThumnailSrc
				+ ", content=" + content + ", time=" + time + ", cnt=" + cnt + "]";
	}
	
	

}
