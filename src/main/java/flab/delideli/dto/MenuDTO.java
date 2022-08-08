package flab.delideli.dto;

import flab.delideli.enums.FoodCategory;
import flab.delideli.enums.MenuStock;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.netty.util.DomainMappingBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="menus")
public class MenuDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuId;
	@Column(length = 255, nullable = false)
	private String menuName;
	@Column(nullable = false)
	private long menuPrice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shop_id", insertable = false, updatable = false)
	private ShopDTO shopDTO;
	@Column(name="shop_id")
	private Long shopId;
	@Enumerated(value = EnumType.STRING)
	private MenuStock menuStock;
	@Column(nullable = false)
	private boolean mainMenu = true;
	@Column(nullable = false)
	private boolean menuActivation = true;
	@Column(length = 255)
	private String menuInfo;
	@Enumerated(value = EnumType.STRING)
	private FoodCategory menuCategory;

}