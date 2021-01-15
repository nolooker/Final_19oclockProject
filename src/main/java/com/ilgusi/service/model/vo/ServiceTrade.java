package com.ilgusi.service.model.vo;

import lombok.Data;

@Data
public class ServiceTrade {
	private int tNo;			//거래번호
	private int sNo;			//서비스 번호
	private int mNo;			//멤버 번호
	private int tStatus;		//거래 진행 상태
	private int tPrice;			//거래 가격
	private String startDate;	//거래시작 날짜
	private String endDate;		//거래마감 날짜
	
	//(문정) 천원단위 나누기위함
	private String tPriceTxt;   //천원단위로 ,찍혀있는 텍스트
}
