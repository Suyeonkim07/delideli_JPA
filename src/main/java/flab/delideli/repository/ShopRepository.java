package flab.delideli.repository;

import flab.delideli.entity.Shop;
import flab.delideli.entity.ShopMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
	ShopMapping findByShopIdAndOwnerId(Long shopId, String ownerId);
	List<ShopMapping> findByOwnerId(String OwnerId);
	Optional<Shop> findByShopNameAndShopLocation(String ShopName, String ShopLocation);
}
