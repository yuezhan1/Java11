package zzti.dao;


import zzti.entity.AuctionDO;
import zzti.entity.CartDO;

import java.util.List;

/**
 * Created by guoli on 17/7/10.
 */
public interface TemporaryCartDAO extends BaseDAO{
    /**
     * 添加商品到临时购物车中
     * @param auctionDO
     */
    public void addToCart(String username, AuctionDO auctionDO);

    /**
     * 从临时购物车中删除一条商品记录
     * @param aucId
     */
    public void removeFromCart(String username, String aucId);

    /**
     * 从临时购物车中将所有的商品信息获取出来
     * @return
     */
    public List<CartDO> getAllFromCart(String username);

}
