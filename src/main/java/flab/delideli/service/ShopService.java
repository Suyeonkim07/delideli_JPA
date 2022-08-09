package flab.delideli.service;

import flab.delideli.entity.Shop;
import flab.delideli.entity.ShopMapping;
import flab.delideli.exception.AlreadyAddedValueException;
import flab.delideli.exception.UnauthorizedException;

import java.util.List;
import java.util.Optional;

import flab.delideli.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {

	private final ShopRepository shopRepository;

	public void addShop(String ownerId, Shop shop) {
		Optional<Shop> shops = shopRepository.findByShopNameAndShopLocation(shop.getShopName(), shop.getShopLocation());
		if (shops.isPresent()) {
			throw new AlreadyAddedValueException("해당 매장이름으로 이미 등록된 가게가 존재합니다.");
		}
		Shop insertShopDTO = Shop.builder()
				.shopName(shop.getShopName())
				.shopTel(shop.getShopTel())
				.shopLocation(shop.getShopLocation())
				.shopInfo(shop.getShopInfo())
				.businessNum(shop.getBusinessNum())
				.deliveryArea(shop.getDeliveryArea())
				.foodCategory(shop.getFoodCategory())
				.notice(shop.getNotice())
				.operatingTime(shop.getOperatingTime())
				.ownerId(ownerId)
				.build();
		shopRepository.save(insertShopDTO);

	}

	public List<ShopMapping> findByOwnerId(String loginId, String ownerId) {
		if (!(loginId.equals(ownerId))) {
			throw new UnauthorizedException("권한이 없습니다.");
		}
		return shopRepository.findByOwnerId(ownerId);
	}

	public ShopMapping findByShopIdAndOwnerId(Long shopId, String ownerId) {
		verifyShopOwner(shopId, ownerId);
		return shopRepository.findByShopIdAndOwnerId(shopId, ownerId);
	}

	public void verifyShopOwner(Long shopId, String ownerId) {
		Shop shopDTO = shopRepository.findById(shopId).get();
		if (!(shopDTO.getMemberDTO().getUserId().equals(ownerId))) {
			throw new UnauthorizedException("이 가게 사장님만 접근 가능합니다.");
		}
	}
}
