package zzti.entity;

public class PersonalInfo {
	/*
	 * page1基础信息
	 */
	private Integer age;
	private String gender;
	private String address;
	private String tel;
	private String email;
	/*
	 * page2学历信息
	 */
	private String graduateSchool;
	private String highestEducation;
	private String major;
	
	
	
	public PersonalInfo() {
		super();
	}
	public PersonalInfo(Integer age, String gender, String address, String tel,
			String email, String graduateSchool, String highestEducation,
			String major) {
		super();
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.graduateSchool = graduateSchool;
		this.highestEducation = highestEducation;
		this.major = major;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	public String getHighestEducation() {
		return highestEducation;
	}
	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	

}
