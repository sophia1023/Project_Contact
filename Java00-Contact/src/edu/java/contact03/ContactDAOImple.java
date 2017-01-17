package edu.java.contact03;

import java.util.ArrayList;

public class ContactDAOImple implements ContactDAO {

	private static ContactDAOImple instance = null;

	private ContactDAOImple() {
	}

	public static ContactDAOImple getInstance() {
		if (instance == null) {
			instance = new ContactDAOImple();
		}
		return instance;
	}

	public static final int MAX = 100; // 우리가 가지고 있는 리스트의 사이즈 최댓값.... 리스트는 사이즈가
										// 가변적임
	// private ContactVO[] list = new ContactVO[MAX];
	private ArrayList<ContactVO> list = new ArrayList<>();
	// private int count = 0;

	/*
	 * public int getCount(){ return count; }
	 */

	@Override
	public int insert(ContactVO vo) {
		// list[count] = vo;
		list.add(vo);

		return 1;
	}

	@Override
	public ArrayList<ContactVO> select() {
		return list;
	}

	@Override
	public ContactVO select(int index) {

		return list.get(index);
	}

	@Override
	public int update(int choice, int index, ContactVO vo) {
		if (index >= 0 && index < MAX) {
			switch (choice) {
			case 1:
				// list.set(index,vo);
				/* setName 사용을 원한다면 */
				// list.get(index); -> 특정 인덱스 값 꺼냈음
				list.get(index).setName(vo.getName());
			case 2:
				// list.set(index,vo);
				list.get(index).setPhone(vo.getPhone());
				break;
			case 3:
				// list.set(index,vo);
				list.get(index).setEmail(vo.getEmail());
				break;
			default:
				System.out.println("존재하지 않는 항목을 고르셨습니다.");
				return 0;
			}	
			return 1;

		} else {
			return 0;
		}
	}

	@Override
	public int delete(int index) {
		if (index >= 0 && index < MAX) {
			list.remove(index);
			return 1;
		} else {
			return 0;
		}
		//arraylist는 삭제되더라도 뒤의 인덱스를 앞으로 다 당겨 채워준다.
	}
}
