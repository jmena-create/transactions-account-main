package com.pichincha.backend.test.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FavoriteDto {
	
	private String code;
	private String userCode;
	private String invoiceCode;
	private String counterpart;
	private String description;
	private LocalDateTime expirationDate;
	private String state;
	private String typePayments;
	private Integer typeProgramming;
	private Boolean allowProgramming;
	private Boolean allowPay;
	private List<ExtendedDetailDto> extendedDetails;

}
