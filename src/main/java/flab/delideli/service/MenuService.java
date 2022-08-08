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

	public void updateMenu(Long menuId,UpdateMenuDTO updateMenuDTO) {
		MenuDTO menuDTO = menuRepository.findById(menuId).get();
		validateExistMenu(menuDTO);
		validateMainMenuPriceNotZero(menuDTO);
		MenuDTO updateMenuDto = MenuDTO.builder()
				.menuId(menuId)
				.menuName(menuDTO.getMenuName())
				.menuPrice(updateMenuDTO.getMenuPrice())
				.shopDTO(menuDTO.getShopDTO())
				.menuStock(updateMenuDTO.getMenuStock())
				.mainMenu(menuDTO.isMainMenu())
				.menuActivation(updateMenuDTO.isMenuActivation())
				.menuInfo(updateMenuDTO.getMenuInfo())
				.menuCategory(updateMenuDTO.getMenuCategory())
				.build();
		menuRepository.save(updateMenuDto);
	}

	public void deleteMenu(Long menuId) {
		try {
			menuRepository.deleteById(menuId);
		} catch (EmptyResultDataAccessException e) {
			throw new MenuIdEmptyException("존재하지 않는 메뉴입니다.");
		}
	}

	private void validateExistMenu(MenuDTO menuDTO) {
		if(menuDTO == null) {
			throw new IllegalArgumentException("잘못된 입력입니다.");
		}
	}

	private void validateMainMenuPriceNotZero(MenuDTO menuDTO) {
		if(menuDTO.isMainMenu() == true && menuDTO.getMenuPrice() == 0) {
			throw new IllegalStateException("메인메뉴는 0원이 될 수 없습니다.");
		}
	}

}