package edu.java.contact04;

import java.io.Serializable;

// MVC 패턴에서 Model 클래스

// 객체를 파일에 쓰거나, 파일에서 읽어오기 위해서 직렬화(Serializable)을 구현
public class ContactVO implements Serializable {
	// 멤버 변수(필드)
	private String name;
	private String phone;
	private String email;
	
	// 기본 생성자, 매개변수 생성자
	public ContactVO() {}
	public ContactVO(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	// getters/setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	// toString() 메소드 override
	@Override
	public String toString() {
		String str = "이름: " + name + "\n"
				+ "전화번호: " + phone + "\n"
				+ "이메일: " + email + "\n";
		
		return str;
	}

} // end class ContactVO


















