package flab.delideli.dto;

import flab.delideli.enums.UserLevel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Table(name="members")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    @Column(length = 255, nullable = false)
    private String userPassword;
    @Column(length = 255, nullable = false)
    private String userName;
    @Column(length = 255, nullable = false)
    private String userPhone;
    @Enumerated(value = EnumType.STRING)
    private UserLevel userLevel;
    @Column(length = 255)
    private String userAddress;

}