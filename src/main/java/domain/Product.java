package domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product {
	private String product_id;
	private String product_name;
	private double product_price;
	private Date product_date;
	private String product_image;
	private String product_attribute;
	private String product_description;
	private String cid;
}
