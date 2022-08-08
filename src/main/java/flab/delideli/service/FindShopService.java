package flab.delideli.service;

import flab.delideli.entity.Shop;
import flab.delideli.dto.ShoplistDTO;

import java.util.List;

public interface FindShopService {

    List<ShoplistDTO> findAllShop(Integer cursor);
    List<ShoplistDTO> findbyShopName(String shopName, Integer cursor);
    Shop getShop(int shopid);
}
