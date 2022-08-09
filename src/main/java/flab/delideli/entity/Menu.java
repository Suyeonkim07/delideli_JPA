package flab.delideli.entity;

import flab.delideli.enums.FoodCategory;
import flab.delideli.enums.MenuStock;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "menus")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuId;


	@Column(length = 100)
	@NotNull
	private String menuName;

	@NotNull
	private long menuPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id", insertable = false, updatable = false)
	private Shop shop;

	@Column(name = "shop_id")
	private Long shopId;

	@Enumerated(value = EnumType.STRING)
	@NotNull
	private MenuStock menuStock;

	@NotNull
	private boolean mainMenu = true;

	@NotNull
	private boolean menuActivation = true;

	@Column(length = 255)
	@NotNull
	private String menuInfo;

	@Enumerated(value = EnumType.STRING)
	@NotNull
	private FoodCategory menuCategory;

}