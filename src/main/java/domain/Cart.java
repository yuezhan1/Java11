package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Cart {
	
	//该购物车中存储的购物项
	private Map<String,CartItem> cartItems=new HashMap<String,CartItem>();
	
	//购物车中的商品的总计
	private double total;
}
