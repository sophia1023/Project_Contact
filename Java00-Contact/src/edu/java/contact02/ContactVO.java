package edu.java.contact02;

//MVC 패턴에서 Model 클래스

public class ContactVO {
	// 멤버 변수(필드, 프로퍼티)
		private String name;
		private String phone;
		private String email;
		
		public ContactVO(){	
		}
		
		public ContactVO(String name, String phone, String email){
			this.name = name;
			this.phone = phone;
			this.email = email;
		}

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
		
		//println(해당 클래스 생성자 변수) 할때 프린트 되는 내용 설정
		@Override
		public String toString() {
			String str = "이름 : " + name + "\n전화번호 : " + phone + "\n이메일 : " + email;
			return str;
		}
}
