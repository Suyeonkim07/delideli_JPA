package flab.delideli.service;

import flab.delideli.dao.ShopDao;
import flab.delideli.dto.ShopDTO;
import flab.delideli.dto.ShopMapping;
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

	public void addShop(String ownerId, ShopDTO shopDTO) {
		Optional<ShopDTO> shopDTOS = shopRepository.findByShopNameAndShopLocation(shopDTO.getShopName(), shopDTO.getShopLocation());
		if(shopDTOS.isPresent())
			throw new AlreadyAddedValueException("해당 매장이름으로 이미 등록된 가게가 존재합니다.");
		ShopDTO insertShopDTO = ShopDTO.builder()
				.shopName(shopDTO.getShopName())
				.shopTel(shopDTO.getShopTel())
				.shopLocation(shopDTO.getShopLocation())
				.shopInfo(shopDTO.getShopInfo())
				.businessNum(shopDTO.getBusinessNum())
				.deliveryArea(shopDTO.getDeliveryArea())
				.foodCategory(shopDTO.getFoodCategory())
				.notice(shopDTO.getNotice())
				.operatingTime(shopDTO.getOperatingTime())
				.ownerId(ownerId).build();
		shopRepository.save(insertShopDTO);

	}

	public ShopMapping findByShopIdAndOwnerId(Long shopId, String ownerId) {
		verifyShopOwner(shopId, ownerId);
		return shopRepository.findByShopIdAndMemberDTO_UserId(shopId, ownerId);
	}

	public List<ShopMapping> findByOwnerId(String ownerId) {
		return shopRepository.findByMemberDTO_UserId(ownerId);
	}

	public void verifyShopOwner(Long shopId, String ownerId) {
		ShopDTO shopDTO = shopRepository.findById(shopId).get();
		if(!(shopDTO.getMemberDTO().getUserId().equals(ownerId)))
			throw new UnauthorizedException("이 가게 사장님만 접근 가능합니다.");
	}
}
