package domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItem {
	private Product product;
	private int buyNum;
	private double subtotal;
	
}
