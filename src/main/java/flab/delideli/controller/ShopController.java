package flab.delideli.controller;


import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.ShopDTO;
import flab.delideli.annotation.UserAuthorization;
import flab.delideli.dto.ShopMapping;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"가게 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("owners/shops")
public class ShopController {

	private ShopService shopService;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "가게 등록")
	@UserAuthorization(role = UserLevel.OWNER)
	public void addShop(@CurrentUser String ownerId, @RequestBody ShopDTO shopDTO) {
		shopService.addShop(ownerId, shopDTO);
	}

	@GetMapping()
	@ApiOperation(value = "사장님 ID로 가게 리스트 조회")
	@UserAuthorization(role = UserLevel.OWNER)
	public List<ShopMapping> getShopList(@RequestParam("ownerId") String ownerId) {
		return shopService.findByOwnerId(ownerId);

	}

	@GetMapping(value="/{shopId}")
	@ApiOperation(value = "사장님 Id와 shopId로 가게 조회")
	@UserAuthorization(role = UserLevel.OWNER)
	public ShopMapping getShop(@PathVariable Long shopId, @CurrentUser String ownerId) {
		return shopService.findByShopIdAndOwnerId(shopId, ownerId);
	}
}
