package flab.delideli.dto;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import flab.delideli.enums.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "shops")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shopId;
	@Column(length = 255, nullable = false)
	private String shopName;
	@Column(length = 255, nullable = false)
	private String shopTel;
	@Column(length = 255, nullable = false)
	private String shopLocation;
	@Column(length = 255, nullable = false)
	private String shopInfo;
	@Column(length = 255, nullable = false)
	private String deliveryArea;
	@Enumerated(value = EnumType.STRING)
	private FoodCategory foodCategory;
	@Column(length = 255, nullable = false)
	private String notice;
	@Column(length = 255, nullable = false)
	private String operatingTime;
	@Column(length = 255, nullable = false)
	private String businessNum;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id", insertable = false, updatable = false)
	private MemberDTO memberDTO;
	@Column(name="owner_id")
	private String ownerId;

}
