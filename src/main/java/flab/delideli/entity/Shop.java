package flab.delideli.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import flab.delideli.dto.MemberDTO;
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
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shopId;


	@Column(length = 100)
	@Size(min = 3, max = 100)
	@NotNull
	private String shopName;

	@Column(length = 100)
	@Size(min = 3, max = 100)
	@NotNull
	private String shopTel;

	@Column(length = 200)
	@NotNull
	private String shopLocation;

	@Column(length = 200)
	@NotNull
	private String shopInfo;

	@Column(length = 200)
	@NotNull
	private String deliveryArea;

	@Enumerated(value = EnumType.STRING)
	@NotNull
	private FoodCategory foodCategory;

	@Column(length = 200)
	@NotNull
	private String notice;

	@Column(length = 200)
	@NotNull
	private String operatingTime;

	@Column(length = 200)
	@NotNull
	private String businessNum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", insertable = false, updatable = false)
	private MemberDTO memberDTO;

	@Column(name = "owner_id")
	@NotNull
	private String ownerId;

}
