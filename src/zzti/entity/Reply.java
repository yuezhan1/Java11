package zzti.entity;

import java.util.Date;

/**
 * 帖子的回复
 * @author guoli
 *
 */
public class Reply {
	private int nodeId;//回复的帖子的编号
	private int replyId;//回复的id
	private String content;//回复的内容
	private Date time;//回复的时间
	private UserDO author;//帖子回复人
	
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public UserDO getAuthor() {
		return author;
	}
	public void setAuthor(UserDO author) {
		this.author = author;
	}
	public Reply(int nodeId, String content, Date time, UserDO author) {
		super();
		this.nodeId = nodeId;
		this.content = content;
		this.time = time;
		this.author = author;
	}
	

}
