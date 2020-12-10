package com.example.asyncmethod;


public class User {

    private Long id;
    private String feeCode;  // ma phi
    private String feeName;  // ten muc phi
    private String payType;  //  loai phi
    private String createUser;
    private String calculationForm; // hinh thuc tinh

	public User() {
	}

	public User(Long id, String feeCode, String feeName, String payType, String createUser, String calculationForm) {
		this.id = id;
		this.feeCode = feeCode;
		this.feeName = feeName;
		this.payType = payType;
		this.createUser = createUser;
		this.calculationForm = calculationForm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCalculationForm() {
		return calculationForm;
	}

	public void setCalculationForm(String calculationForm) {
		this.calculationForm = calculationForm;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", feeCode='" + feeCode + '\'' +
				", feeName='" + feeName + '\'' +
				", payType='" + payType + '\'' +
				", createUser='" + createUser + '\'' +
				", calculationForm='" + calculationForm + '\'' +
				'}';
	}
}
