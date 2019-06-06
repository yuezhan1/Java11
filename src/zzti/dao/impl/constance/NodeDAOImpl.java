package zzti.dao.impl.constance;

import zzti.dao.NodeDAO;
import zzti.dao.ReplyDAO;
import zzti.dao.UserDAO;
import zzti.entity.Node;
import zzti.entity.UserDO;

import java.sql.SQLException;
import java.util.*;

public class NodeDAOImpl implements NodeDAO {
	private static UserDAO userDAO = new UserDAOImpl();
	private static ReplyDAO repltDAO = (ReplyDAO) new ReplyDAOImpl();
	private static Map<Integer,Node> nodeList = new HashMap<Integer,Node>();
	private static int maxId=0;//每插入一条数据，maxid+1，删除记录不用修改该值，该值持续增长，类似于数据库的自增长id
	static{
		for(int i=0;i<10;i++){
			UserDO u = null;
			try {
				u = userDAO.getUserByName("admin0");
				Node node = new Node("admin0发布的帖子的标题"+i,
						u,
						new Date(new Date().getTime()+(i*100000))
						,0);
				maxId++;
				node.setId(maxId);
				nodeList.put(maxId, node);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	@Override
	public List<Node> getAllNodes() {
		
		return new ArrayList<Node>(nodeList.values());
	}
	@Override
	public void addNode(Node node) {
		maxId++;
		node.setId(maxId);
		nodeList.put(maxId, node);
		
	}
	@Override
	public void deleteNode(int nodeId) {
		Node node = nodeList.remove(nodeId);
		repltDAO.deleteReplyByNodeId(node.getId());
	}
	@Override
	public Node getNodeById(int nodeId) {
		Node node = nodeList.get(nodeId);
		
		return node;
	}

}
