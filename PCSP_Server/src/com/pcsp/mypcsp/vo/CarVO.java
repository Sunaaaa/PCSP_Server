package com.pcsp.mypcsp.vo;

public class CarVO {
	String carNum;
	int carTypeNUm;
	String carLong;
	String carLat;
	String carStatus;
	int carOwnerNum;
	String carRentFee;
	String carHipassFee;
	
	public CarVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CarVO(String carNum, int carTypeNUm, String carLong, String carLat, String carStatus, int carOwnerNum,
			String carRentFee, String carHipassFee) {
		super();
		this.carNum = carNum;
		this.carTypeNUm = carTypeNUm;
		this.carLong = carLong;
		this.carLat = carLat;
		this.carStatus = carStatus;
		this.carOwnerNum = carOwnerNum;
		this.carRentFee = carRentFee;
		this.carHipassFee = carHipassFee;
	}


	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public int getCarTypeNUm() {
		return carTypeNUm;
	}
	public void setCarTypeNUm(int carTypeNUm) {
		this.carTypeNUm = carTypeNUm;
	}
	public String getCarLong() {
		return carLong;
	}
	public void setCarLong(String carLong) {
		this.carLong = carLong;
	}
	public String getCarLat() {
		return carLat;
	}
	public void setCarLat(String carLat) {
		this.carLat = carLat;
	}
	public String getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}
	public int getCarOwnerNum() {
		return carOwnerNum;
	}
	public void setCarOwnerNum(int carOwnerNum) {
		this.carOwnerNum = carOwnerNum;
	}
	public String getCarRentFee() {
		return carRentFee;
	}
	public void setCarRentFee(String carRentFee) {
		this.carRentFee = carRentFee;
	}
	public String getCarHipassFee() {
		return carHipassFee;
	}
	public void setCarHipassFee(String carHipassFee) {
		this.carHipassFee = carHipassFee;
	}
	

}
