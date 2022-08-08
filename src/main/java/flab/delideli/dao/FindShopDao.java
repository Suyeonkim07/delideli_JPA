package flab.delideli.dao;

import flab.delideli.entity.Shop;
import flab.delideli.dto.ShoplistDTO;
import flab.delideli.paging.PagingCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FindShopDao {

    List<ShoplistDTO> findAllShop(PagingCriteria pagingCriteria);

    List<ShoplistDTO> findShopName(@Param("shopname") String shopName, @Param("pagingCriteria") PagingCriteria pagingCriteria);
  
    Shop getShop(int shopid);
}
