package flab.delideli.repository;

import flab.delideli.dto.MenuDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuDTO, Long> {
	Optional<MenuDTO> findByShopIdAndMenuName(Long shopId, String menuName);
}
