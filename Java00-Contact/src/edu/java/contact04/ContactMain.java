package edu.java.contact04;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactMain implements Menu {

	private static ContactDAO dao;
	private static Scanner sc;

	public static void main(String[] args) {
		System.out.println("************************** 연락처 Ver0.3 ******************************");
		sc = new Scanner(System.in);
		dao = ContactDAOImple.getInstance();
		boolean run = true;

		while (run) {
			showMenu();
			int chooseMenu = sc.nextInt();
			sc.nextLine(); // 숫자 입력 다음 문자 받을 경우 대비
			switch (chooseMenu) {
			case INSERT: // 등록
				insertList();
				break;
			case SELECT_ALL: // 전체검색
				showList();
				break;
			case SELECT_BY_INDEX: // 상세검색
				searchList();
				break;
			case UPDATE: // 수정
				editList();
				break;
			case DELETE:
				deleteList();
				break;
			case QUIT: // 종료
				run = false;
				break;
			default:
				System.out.println("존재하지 않는 메뉴를 선택하셨습니다!\n");
			}
		}

		System.out.println("==============================================");
		System.out.println("프로그램을 종료합니다....");
		sc.close();

	}

	private static void deleteList() {
		ArrayList<ContactVO> list = dao.select();
		int size = list.size();
		System.out.print("삭제할 연락처의 인덱스를 입력하세요 : ");
		int delIndex = sc.nextInt();
		sc.nextLine();
		if (delIndex < size && delIndex >= 0) {
			int result = dao.delete(delIndex);	
			if (result > 0) {
				System.out.println("> 연락처 삭제 성공!\n");
			} else {
				System.out.println("> 연락처 삭제 실패!\n");
			}
		} else {
			System.out.println("존재하지 않는 인덱스를 고르셨습니다.");
		}
	}

	public static void showMenu() {
		ArrayList<ContactVO> list = dao.select();
		int size = list.size();
		System.out.println("-------------------------------------------------------------------");
		System.out.println("현재 등록 인원 : " + size + "\n");
		System.out.println("     1. 등록   |   2. 전체 검색   |   3. 상세 검색   |   4. 수정   |   5. 삭제   |   0. 종료");
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

		ContactVO cvo = new ContactVO(name, phone, email);

		int result = dao.insert(cvo);
		if (result > 0) {
			System.out.println("> 연락처 등록 성공!\n");
		} else {
			System.out.println("> 연락처 등록 실패!\n");
		}
	}

	public static void showList() {

		System.out.println("\n");
		System.out.println("		*********등록된 전체 연락처*********");
		ArrayList<ContactVO> list = dao.select();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("단축번호 NO." + (i + 1));
			System.out.println(list.get(i));
			System.out.println("------------------------------------------------------");
		}
	}

	public static void searchList() {

		System.out.print("검색할 인덱스을 입력하세요 > ");
		int searchIndex = sc.nextInt();
		sc.nextLine();

		System.out.println("		*********검색한 연락처*********");
		ContactVO cvo = dao.select(searchIndex);
		System.out.println(cvo);
	}

	public static void editList() {
		ArrayList<ContactVO> list = dao.select();
		int size = list.size();
		System.out.print("수정할 인덱스을 입력하세요 > ");
		int searchIndex = sc.nextInt();
		sc.nextLine();
		ContactVO cvo = dao.select(searchIndex);
		if (searchIndex < size && searchIndex >= 0) {
			System.out.println("			*********수정할 연락처*********");
			System.out.println("------------------------------------------------------");
			System.out.println("수정할 내용을 선택하세요.");
			System.out.println("------------------------------------------------------");
			System.out.println("   1. 이름    |   2. 전화번호   |   3. 이메일 ");
			System.out.println("------------------------------------------------------");
			System.out.print("선택 항목 > ");
			int editMenu = sc.nextInt();
			sc.nextLine();
			switch (editMenu) {
			case 1:
				System.out.print("수정할 이름 > ");
				String name = sc.nextLine();
				cvo = new ContactVO(name, null, null);
				break;
			case 2:
				System.out.print("전화번호 > ");
				String phone = sc.nextLine();
				cvo = new ContactVO(null, phone, null);
				break;
			case 3:
				System.out.print("이메일 > ");
				String email = sc.nextLine();
				cvo = new ContactVO(null, null, email);
				break;
			}

			int result = dao.update(editMenu, searchIndex, cvo);
			if (result > 0) {
				System.out.println("> 연락처 수정 성공!\n");
			} else {
				System.out.println("> 연락처 수정 실패!\n");
			}

		} else {
			System.out.println("존재하지 않는 인덱스를 선택하셨습니다.");

		}
	}
}
