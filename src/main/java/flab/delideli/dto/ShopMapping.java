package flab.delideli.dto;

import flab.delideli.enums.FoodCategory;

public interface ShopMapping {
	String getShopName();
	String getShopTel();
	String getShopLocation();
	String getShopInfo();
	String getDeliveryArea();
	FoodCategory getFoodCategory();
	String getNotice();
	String getOperatingTime();
	String getBusinessNum();
}
