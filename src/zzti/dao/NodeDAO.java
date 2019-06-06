package zzti.dao;


import zzti.entity.Node;

import java.util.List;

public interface NodeDAO {
	/**
	 * 获得全部的帖子
	 * @return 帖子列表
	 */
	public List<Node> getAllNodes();
	/**
	 * 发表一个帖子
	 * @param node
	 */
	public void addNode(Node node);
	/**
	 * 删除一个帖子，注意，要把该帖子下的所有的回复全部删除 
	 * @param nodeId
	 */
	public void deleteNode(int nodeId);
	/**
	 * 根据帖子id查询一个帖子的所有数据
	 * @param nodeId
	 * @return
	 */
	public Node getNodeById(int nodeId);
	

}
