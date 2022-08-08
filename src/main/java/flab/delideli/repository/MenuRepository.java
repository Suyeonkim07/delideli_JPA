package flab.delideli.repository;

import flab.delideli.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	Optional<Menu> findByShopIdAndMenuName(Long shopId, String menuName);
}
