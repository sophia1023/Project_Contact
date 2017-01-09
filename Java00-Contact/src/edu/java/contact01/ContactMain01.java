package edu.java.contact01;

import java.util.Scanner;

public class ContactMain01 {
	
	// 연락처 최대 저장 개수 - 상수
	private static final int MAX = 100;
	private static final int MENU_QUIT = 0;
	private static final int MENU_INSERT = 1;
	private static final int MENU_SELECT_ALL = 2;
	private static final int MENU_SELECT = 3;
	private static final int MENU_UPDATE = 4;
	
	// 연락처를 저장할 배열
	private static ContactVO[] contactList = new ContactVO[MAX]; // static main 메소드에서 사용할거니까 static 붙여준다
	private static int count = 0; // 저장된 데이터 개수 - 연락처를 등록할 때마다 1씩 증가
	
	private static String inputName, inputPhone, inputEmail;
	
	// 입력 준비
	private static Scanner scanner = new Scanner(System.in);	//이렇게 하지 않으면 메소드마다 스캐너 생성해줘야 하는 번거로움이 있다

	public static void main(String[] args) {
		System.out.println("                                                                                  연락처 Version 0.1");
		
		boolean run = true;
		
		while (run) {
			showMainMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();	// 숫자만 가져가고 입력버퍼에 남은 엔터키 지우기
			
			switch (choice) {
			case MENU_QUIT : // 프로그램 종료
				System.out.println("          프로그램을 종료합니다");
				run = false;
				break;
			case MENU_INSERT : //새 연락처 등록
				insertContact();
				break;
			case MENU_SELECT_ALL : // 전체 검색
				printAllContacts();
				break;
			case MENU_SELECT : // 상세 검색
				searchByIndex();
				break;
			case MENU_UPDATE : // 수정
				editContact();
				break;
			default :
				System.out.println("          메뉴를 다시 선택해 주세요");			
			}			
		}
	
		scanner.close();
		
	}
	
	private static void showMainMenu() {
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("|  0. 종료        1. 등록        2. 전체검색        3. 상세검색        4. 수정  |");
		System.out.println("------------------------------------------------------");	
		System.out.print(" 선택 > ");
	}
	
	private static void insertContact() {	// 이 메소드는 public이든 private 든 상관 없지만 static은 써줘야!
		System.out.println();
		System.out.println("          * 연락처 등록 메뉴 ");
		inputInfo();
		
		// ContactVO 인스턴스 생성
		ContactVO c = new ContactVO(inputName, inputPhone, inputEmail);
		
		// 배열에 저장
		contactList[count] = c;
		count++;
	}
	
	private static void printAllContacts() {
		System.out.println();
		System.out.println("          * 전체 검색 메뉴 ");
		if(count == 0) {
			System.out.println("                           저장된 전화번호부가 없습니다");
		} else {
			for(int i = 0; i < count; i++) {
				System.out.println("                           ---------연락처 " + (count - 1) + " 번 ---------");
				contactList[i].displayInfo();
				System.out.println();
			}
		}
	}
	
	private static void searchByIndex() {
		System.out.println();
		System.out.println("          * 상세 검색 메뉴 ");
		if(count == 0) {
			System.out.println("                           저장된 연락처가 없습니다");
		} else {
			System.out.print("                           인덱스 번호 입력  >  ");
			int indexNum = scanner.nextInt();
			if(indexNum < count) {
				contactList[indexNum].displayInfo();
			} else if(indexNum >= count || indexNum < 0){
				System.out.println("                           현재 등록된 연락처의 인덱스 번호는 0부터 " + (count - 1) + " 까지 입니다.");
				System.out.println("                           인덱스 번호를 확인해 주세요");
				searchByIndex();
			}			
		}
	}
	
	private static void editContact() {
		System.out.println();
		System.out.println("          * 수정 메뉴 ");
		System.out.print("                           인덱스 번호 입력  >  ");
		int indexNum = scanner.nextInt();
		if(indexNum < count) {
			System.out.println("                           선택하신 정보는 " + contactList[indexNum].getName() + " 의 정보입니다.");
			System.out.print("                           수정을 계속 하시려면 1 번을 눌러주세요 ");
			int editChoice = scanner.nextInt();
			scanner.nextLine();			
			if(editChoice == 1) {
				inputInfo();				
				contactList[indexNum].setName(inputName);
				contactList[indexNum].setPhone(inputPhone);
				contactList[indexNum].setEmail(inputEmail);
				
//				ContactVO tempCT = new ContactVO(inputName, inputPhone, inputEmail);
//				contactList[indexNum] = tempCT;
				
				System.out.println();
				System.out.println("                           수정된 정보입니다 ");
				contactList[indexNum].displayInfo();
			} else {
				System.out.println("                           수정 메뉴를 종료합니다.");
			}
		} else {
			System.out.println("                           현재 등록된 연락처의 인덱스 번호는 0부터 " + (count - 1) + " 까지 입니다.");
			System.out.println("                           인덱스 번호를 확인해 주세요");
			editContact();
		}
		
	}
	
	private static void inputInfo() {
		System.out.print("                           이름 입력  >  ");
		inputName = scanner.nextLine();
		System.out.print("                           전화번호 입력  >  ");
		inputPhone = scanner.nextLine();
		System.out.print("                           이메일 입력  >  ");
		inputEmail = scanner.nextLine();
	}

}



//연락처(Contact) 프로그램 ver 0.1
//배열 사용
//1. 기능 설계
// 1) 연락처 전체 리스트를 보여줘야
// 2) 연락처 등록 (저장)
// 3) 연락처 삭제 delete = " "
// 4) 연락처 수정 set
// 5) 연락처 검색 - 한명의 연락처의 상세 정보 ( 리스트는 이름 & 정보, 상세정보는 이메일 주소 생일 ....)
// 6) 종료
//
//2. 데이터 설계(클래스 설계)
// 1) class Contact : 이름, 전화번호, 이메일 getter/setter 정도.
// 2) class Main : 등록, 삭제, 수정, 검색 기능 (메소드)
//
//3. UI 설계
//
//1) 
//--------------------------------------------------------------------------------
//1. 등록   |  2. 전체검색   | 3. 상세검색  |  4. 수정   | 5. 삭제   | 0. 종료
//--------------------------------------------------------------------------------
//
//선택 >
//
//
//
//
//2)  등록 (사용자가 1번을 선택 한 경우)
//----------------------------------------------------------------------------------
//이름 > ...
//전화번호 > ...
//이메일 > ...
//
//
//3) 전체 검색 -> 전체 정보 출력
//
//4) 상세 검색 -> 인덱스 검색
//----------------------------------------------------------------------------------
//인덱스 > .... ( 숫자 입력하면 상세정보)
//
//이름 : 홍길동
//전화번호 : 010-1234-2345
//이메일 : aaaaaa@aaaa.com
//
//
//5) 수정
//----------------------------------------------------------------------------------
//
//인덱스 > ...
//
//이름 > ...
//전화번호 > ...
//이메일 > ....
