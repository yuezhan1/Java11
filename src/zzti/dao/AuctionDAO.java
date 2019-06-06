package zzti.dao;


import zzti.entity.AuctionDO;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品操作
 * Created by guoli on 17/5/14.
 */
public interface AuctionDAO extends BaseDAO{
	/**
	 * 从数据源中获取指定id对应的商品信息
	 * @param id
	 * @return
	 */
    public AuctionDO getAuction(String id)  throws SQLException;
    /**
     * 添加一条商品信息到数据源中
     * @param auc：要添加的商品对象
     */
    public void addAuction(AuctionDO auc)throws SQLException;
    /**
     * 获取数据源中的全部的商品列表
     * @return
     */
    public List<AuctionDO> getAll() throws SQLException;
    /**
     * 通过商品id删除一条商品记录
     * @param id：要删除的商品的id
     */
    public void deleteAuction(String id) throws SQLException;
    /**
     * 修改数据源中的指定商品信息
     * @param auc:要修改的商品和其修改后的信息
     */
    public void updateAuction(AuctionDO auc) throws SQLException;

}
