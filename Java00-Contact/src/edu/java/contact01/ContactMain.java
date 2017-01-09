package edu.java.contact01;

import java.util.Scanner;

public class ContactMain {

	// 연락처 최대 저장 개수 - 상수 -> final 사용한다
	private static final int MAX = 100;

	// 연락처를 저장할 배열
	private static ContactVO[] contactList = new ContactVO[MAX];
	private static int count = 0; // 저장된 데이터 개수 -> 연락처를 등록할 때마다 1씩 증가

	// 입력 준비
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		boolean run = true;

		while (run) {
			showMenu();
			int chooseMenu = sc.nextInt();
			sc.nextLine(); // 숫자 입력 다음 문자 받을 경우 대비.
			switch (chooseMenu) {
			case 1: // 등록
				insertList();
				break;
			case 2: // 전체검색
				showList();
				break;
			case 3: // 상세검색
				searchList();
				break;
			case 4: // 수정
				editList();
				break;
			case 5: // 종료
				run = false;
				break;
			default:
				System.out.println("존재하지 않는 메뉴를 선택하셨습니다.");
			}
		}

	}

	public static void showMenu() {
		System.out.println("\n***************************연락처 ver0.1*********************************");
		System.out.println("현재 등록 인원 : " + count);
		System.out.println("-------------------------------------------------------------------");
		System.out.println("     1. 등록   |   2. 전체 검색   |   3. 상세 검색   |   4. 수정   |   5. 종료");
		System.out.println("-------------------------------------------------------------------");
		System.out.print("메뉴를 선택하세요 > ");
	}

	public static void insertList() {
		System.out.println("			*******연락처 등록메뉴*********");
		System.out.println(" 등록할 정보를 입력 하세요.");
		System.out.print("이름 > ");
		String name = sc.nextLine();
		System.out.print("전화번호 > ");
		String phone = sc.nextLine();
		System.out.print("이메일 > ");
		String email = sc.nextLine();

		// contactVO 인스턴스 생성
		ContactVO c = new ContactVO(name, phone, email);
		contactList[count] = c;
		count++;
	}

	public static void showList() {
		System.out.println("		*********등록된 전체 연락처*********");
		for (int i = 0; i < count; i++) {
			contactList[i].displayInfo();
			System.out.println("------------------------------------------------------");
		}
	}

	public static void searchList() {
		System.out.print("검색할 인덱스을 입력하세요 > ");
		int searchIndex = sc.nextInt();
		sc.nextLine();
		if (searchIndex > count || searchIndex < 0) {
			System.out.println("존재하지 않는 인덱스를 선택하셨습니다.");
		} else {
			System.out.println("		*********검색한 연락처*********");
			contactList[searchIndex].displayInfo();
		}
	}
	
	public static void editList() {
		System.out.print("수정할 인덱스을 입력하세요 > ");
		int searchIndex = sc.nextInt();
		sc.nextLine();
		if (searchIndex > count || searchIndex < 0) {
			System.out.println("존재하지 않는 인덱스를 선택하셨습니다.");
		} else {
			System.out.println("			*********수정할 연락처*********");
			contactList[searchIndex].displayInfo();
			System.out.println("------------------------------------------------------");
			System.out.println("수정할 내용을 선택하세요.");
			System.out.println("------------------------------------------------------");
			System.out.println("   1. 이름    |   2. 전화번호   |   3. 이메일   | 4. 메뉴로 돌아가기   ");
			System.out.println("------------------------------------------------------");
			System.out.print("선택 항목 > ");
			int editMenu = sc.nextInt();
			sc.nextLine();
			switch(editMenu){
			case 1:
				System.out.print("수정할 이름 > ");
				String name = sc.nextLine();
				contactList[searchIndex].setName(name);
				break;
			case 2:
				System.out.print("전화번호 > ");
				String phone = sc.nextLine();
				contactList[searchIndex].setPhone(phone);
				break;
			case 3:
				System.out.print("이메일 > ");
				String email = sc.nextLine();
				contactList[searchIndex].setEmail(email);
				break;
			case 4:
				break;
			default:
				System.out.println("존재하지 않는 항목을 고르셨습니다.");
			}		
			
		}

	}
}
