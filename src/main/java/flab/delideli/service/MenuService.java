package flab.delideli.service;

import flab.delideli.dto.MenuDTO;
import flab.delideli.dto.UpdateMenuDTO;
import flab.delideli.exception.AlreadyAddedValueException;
import flab.delideli.exception.MenuIdEmptyException;
import flab.delideli.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;

	public void saveMenu(Long shopId, MenuDTO menuDTO) {
		Optional<MenuDTO> menuDTOs = menuRepository.findByShopIdAndMenuName(shopId, menuDTO.getMenuName());
		if (menuDTOs.isPresent()) {
			throw new AlreadyAddedValueException("해당 가게에 이미 등록한 메뉴입니다.");
		}
		MenuDTO saveMenuDTO = MenuDTO.builder()
				.menuName(menuDTO.getMenuName())
				.menuPrice(menuDTO.getMenuPrice())
				.shopId(shopId)
				.menuStock(menuDTO.getMenuStock())
				.mainMenu(menuDTO.isMainMenu())
				.menuActivation(menuDTO.isMenuActivation())
				.menuInfo(menuDTO.getMenuInfo())
				.menuCategory(menuDTO.getMenuCategory())
				.build();
		menuRepository.save(saveMenuDTO);
	}

	public void updateMenu(Long menuId, UpdateMenuDTO updateMenuDTO, Long shopId) {
		Optional<MenuDTO> menuDTO = menuRepository.findById(menuId);
		validateExistMenu(menuDTO);
		validateMainMenuPriceNotZero(menuDTO);
		MenuDTO updateMenuDto = MenuDTO.builder()
				.menuId(menuId)
				.menuName(menuDTO.get().getMenuName())
				.menuPrice(updateMenuDTO.getMenuPrice())
				.shopId(shopId)
				.menuStock(updateMenuDTO.getMenuStock())
				.mainMenu(menuDTO.get().isMainMenu())
				.menuActivation(updateMenuDTO.isMenuActivation())
				.menuInfo(updateMenuDTO.getMenuInfo())
				.menuCategory(updateMenuDTO.getMenuCategory())
				.build();
		menuRepository.save(updateMenuDto);
	}

	public void deleteMenu(Long menuId) {
		menuRepository.deleteById(menuId);
	}

	private void validateExistMenu(Optional<MenuDTO> menuDTO) {
		if(menuDTO.isEmpty()) {
			throw new IllegalArgumentException("잘못된 입력입니다.");
		}
	}

	private void validateMainMenuPriceNotZero(Optional<MenuDTO> menuDTO) {
		if(menuDTO.get().isMainMenu() == true && menuDTO.get().getMenuPrice() == 0) {
			throw new IllegalStateException("메인메뉴는 0원이 될 수 없습니다.");
		}
	}

}