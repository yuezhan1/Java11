package zzti.entity;

/**
 * Created by guoli on 17/7/6.
 */
public class CartDO {
    private AuctionDO auctionDO;
    private double totlePrice;
    private int number;
    public AuctionDO getAuctionDO() {
        return auctionDO;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAuctionDO(AuctionDO auctionDO) {
        this.auctionDO = auctionDO;
    }

    public double getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(double totlePrice) {
        this.totlePrice = totlePrice;
    }
}
