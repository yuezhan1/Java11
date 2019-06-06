package zzti.dao;

import zzti.entity.Reply;

import java.util.List;

public interface ReplyDAO {
	/**
	 * 获得某一个帖子的所有回复数据
	 * @param nodeId 帖子的id
	 * @return 所有的回复数据
	 */
	public List<Reply> getAll(int nodeId);
	/**
	 * 添加一条帖子的回复数据，该回复所属的帖子的id在reply参数的属性中
	 * @param reply
	 */
	public void addReply(Reply reply);
	/**
	 * 根据回复的id删除一条帖子的回复
	 * @param replyId
	 */
	public void deleteReply(Reply replyId);
	/**
	 * 根据帖子id删除这个帖子的所有回复
	 * @param nodeId
	 */
	public void deleteReplyByNodeId(int nodeId);
	/**
	 * 根据回复id获取一条回复的数据 
	 * @param replyId
	 * @return
	 */
	public Reply getReplyById(Reply replyId);

}
