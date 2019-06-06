package zzti.entity;

import java.util.Date;

public class Node {
	private int id;//帖子的id
	private String title;//帖子标题
	private UserDO author;//帖子的作者
	private Date time;//发帖时间
	private int hot;//帖子的热度，回复的数量
	private String content;// 帖子的核心内容
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	
	public Node(String title, UserDO author, Date time, int hot) {
		super();
		this.title = title;
		this.author = author;
		this.time = time;
		this.hot = hot;
	}
	public UserDO getAuthor() {
		return author;
	}
	public void setAuthor(UserDO author) {
		this.author = author;
	}
	
	public Node() {
		super();
	}
	

}
