package flab.delideli.dao;

import flab.delideli.entity.Shop;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopDao {

	void insertShop(Shop shopDTO);

	boolean isExistShop(@Param("shopName") String shopName,
		@Param("shopLocation") String shopLocation);

	Shop selectShopByShopIdAndOwnerId(@Param("shopId") long shopId,
	                                  @Param("ownerId") String ownerId);

	List<Shop> selectShopListByOwnerId(String ownerId);

	boolean isCurrentUserMatchingOwnerId(@Param("shopId") Long shopId,
		@Param("ownerId") String ownerId);

	String getOwnerIdInShops(long shopId);
}
