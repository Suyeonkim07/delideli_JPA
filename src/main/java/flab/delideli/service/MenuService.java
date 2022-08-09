package flab.delideli.service;

import flab.delideli.entity.Menu;
import flab.delideli.dto.UpdateMenuDTO;
import flab.delideli.exception.AlreadyAddedValueException;
import flab.delideli.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;

	@Transactional
	public void saveMenu(Long shopId, Menu menuDTO) {
		Optional<Menu> menus = menuRepository.findByShopIdAndMenuName(shopId, menuDTO.getMenuName());
		if (menus.isPresent()) {
			throw new AlreadyAddedValueException("해당 가게에 이미 등록한 메뉴입니다.");
		}
		Menu saveMenuDTO = Menu.builder()
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

	@Transactional
	public void updateMenu(Long menuId, UpdateMenuDTO updateMenuDTO) {
		Menu menu = menuRepository.findById(menuId).orElseThrow(() -> {
			throw new IllegalArgumentException("변경할 메뉴가 없습니다.");
		});
		validateMainMenuPriceNotZero(menu, updateMenuDTO);
		menu.setMenuPrice(updateMenuDTO.getMenuPrice());
		menu.setMenuStock(updateMenuDTO.getMenuStock());
		menu.setMenuInfo(updateMenuDTO.getMenuInfo());
		menu.setMenuCategory(updateMenuDTO.getMenuCategory());
		menu.setMenuActivation(updateMenuDTO.isMenuActivation());
	}

	@Transactional
	public void deleteMenu(Long menuId) {
		menuRepository.deleteById(menuId);
	}

	private void validateMainMenuPriceNotZero(Menu menu, UpdateMenuDTO updateMenuDTO) {
		if (menu.isMainMenu() == true && updateMenuDTO.getMenuPrice() == 0) {
			throw new IllegalStateException("메인메뉴는 0원이 될 수 없습니다.");
		}
	}

}