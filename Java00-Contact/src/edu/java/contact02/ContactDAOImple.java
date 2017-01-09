package edu.java.contact02;

public class ContactDAOImple implements ContactDAO {
	
	private static ContactDAOImple instance = null;

	private ContactDAOImple(){}
	
	public static ContactDAOImple getInstance(){
		if(instance == null){
			instance = new ContactDAOImple();
		}
		return instance;
	}
	
	public static final int MAX = 100;
	private ContactVO[] list = new ContactVO[MAX];
	private int count = 0;
	
	public int getCount(){
		return count;
	}

	@Override
	public int insert(ContactVO vo) {
		list[count] = vo;
		count++;
		return 1;
	}

	@Override
	public ContactVO[] select() {
		return list;
	}

	@Override
	public ContactVO select(int index) {
		
		return list[index];
	}

	@Override
	public int update(int choice, int index, ContactVO vo) {
		if (index >= 0 && index < count) {
			switch(choice){
			case 1:
				list[index].setName(vo.getName());
			case 2:
				list[index].setPhone(vo.getPhone());
				break;
			case 3:
				list[index].setEmail(vo.getEmail());
				break;
			}
			System.out.println(list[index]);
			return 1;
			
		} else {
			return 0;
		}
	}

}
