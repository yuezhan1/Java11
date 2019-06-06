package zzti.dao.impl.constance;
import zzti.dao.AuctionDAO;
import zzti.entity.AuctionDO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionDAOImpl implements AuctionDAO {
	private static Map<String, AuctionDO> auctionList = new HashMap<String,AuctionDO>();

	static{
		for(int i=0;i<20;i++){
			AuctionDO auc = new AuctionDO("商品"+i,"description"+i,100+(int)(Math.random()*100));
			auctionList.put(auc.getId(),auc);
		}
	}
	@Override
	public AuctionDO getAuction(String id) {
		return auctionList.get(id);
	}

	@Override
	public void addAuction(AuctionDO auc) {
		auctionList.put(auc.getId(),auc);
	}

	@Override
	public List<AuctionDO> getAll() {
		return new ArrayList<AuctionDO>(auctionList.values());
	}

	@Override
	public void deleteAuction(String id) {
		auctionList.remove(id);
	}

	@Override
	public void updateAuction(AuctionDO auc) {
		auctionList.put(auc.getId(), auc);
	}
}
