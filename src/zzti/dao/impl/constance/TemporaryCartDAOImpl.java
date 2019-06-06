package zzti.dao.impl.constance;

import zzti.dao.TemporaryCartDAO;
import zzti.entity.AuctionDO;
import zzti.entity.CartDO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guoli on 17/7/10.
 */
public class TemporaryCartDAOImpl implements TemporaryCartDAO {
    private static Map<String,Map<String,CartDO>> cartList = new HashMap<String,Map<String,CartDO>>();
    @Override
    public void addToCart(String username,AuctionDO auctionDO) {
        Map<String,CartDO> userCart = cartList.get(username);
        if(userCart==null){
            userCart = new HashMap<String, CartDO>();
            cartList.put(username,userCart);
        }
        if(!userCart.containsKey(auctionDO.getId())){
            userCart.put(auctionDO.getId(),new CartDO());
        }
        CartDO cart = userCart.get(auctionDO.getId());
        cart.setAuctionDO(auctionDO);
        cart.setNumber(cart.getNumber()+1);
        cart.setTotlePrice(cart.getTotlePrice()+auctionDO.getPrice());
    }

    @Override
    public void removeFromCart(String username,String aucId) {
        Map<String,CartDO> userCart = cartList.get(username);
        if(userCart!=null){
            userCart.remove(aucId);
        }

    }

    @Override
    public List<CartDO> getAllFromCart(String username) {
        List<CartDO> list = new ArrayList<CartDO>();
        Map<String,CartDO> userCart = cartList.get(username);
        if(userCart!=null){
            list = new ArrayList<CartDO>(userCart.values());
        }
        return list;
    }
}
