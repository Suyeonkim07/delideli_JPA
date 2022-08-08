package flab.delideli.repository;

import flab.delideli.dto.ShopDTO;
import flab.delideli.dto.ShopMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<ShopDTO, Long> {
	ShopMapping findByShopIdAndMemberDTO_UserId(Long shopId, String ownerId);
	List<ShopMapping> findByMemberDTO_UserId(String OwnerId);
	Optional<ShopDTO> findByShopNameAndShopLocation(String ShopName, String ShopLocation);
}
