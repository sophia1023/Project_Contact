package edu.java.contact05;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ContactDAOImple implements ContactDAO {
	// Singleton 패턴 적용
	// 1. private static 자기자신 타입 멤버 변수 선언
	private static ContactDAOImple instance = null;
	// 2. private 생성자
	private ContactDAOImple() {
		initDataDir();
		initDataFile();
	}
	// 3. public static 자기자신을 리턴하는 메소드
	public static ContactDAOImple getInstance() {
		if (instance == null) {
			instance = new ContactDAOImple();
		}
		
		return instance;
	}
	
	// 멤버 변수(필드)
	private ArrayList<ContactVO> list;

	// 데이터를 저장할 폴더와 파일 이름 정의
	private static final String DATA_DIR = "data"; // 상대 경로
	private static final String DATA_FILE = "contact.data";
	
	// 데이터 폴더와 파일을 사용하는 File 객체 선언
	private File dataDir;
	private File dataFile;
	
	// 애플리케이션 시작될 때 데이터 폴더가 있는 지 검사하고, 없으면 새로 생성
	private void initDataDir() {
		dataDir = new File(DATA_DIR);
		System.out.println("폴더 경로: " + dataDir.getPath());
		System.out.println("절대 경로: " + dataDir.getAbsolutePath());
		
		if (!dataDir.exists()) { // 폴더가 없으면
			if (dataDir.mkdir()) {
				System.out.println("폴더 생성 성공");
			} else {
				System.out.println("폴더 생성 실패");
			}
			
		} else { // 폴더가 있으면
			System.out.println("폴더가 이미 존재");
		}
		
	} // end initDataDir()
	
	// 데이터 파일이 있는 지 없는 지 검사하고,
	// 없는 경우, ArrayList를 빈 리스트로 생성
	// 있는 경우, 파일에서 데이터를 읽어서 ArrayList를 채움
	private void initDataFile() {
		String filePath = 
				DATA_DIR + File.separator + DATA_FILE;
		// "data\\contact.data"
		dataFile = new File(filePath);
		System.out.println("파일 경로: " + dataFile.getPath());
		System.out.println("절대 경로: " + dataFile.getAbsolutePath());
		
		if (!dataFile.exists()) { // 데이터 파일이 없으면
			System.out.println("새로운 데이터 파일 생성");
			list = new ArrayList<>();
			
		} else { // 데이터 파일이 있으면
			System.out.println("기존 데이터 있음");
			readDataFromFile();
		}
		
	} // end initDataFile
	
	// FileInputStream, BufferedInputStream, ObjectInputStream
	// data\contact.data 파일에서 ArrayList 객체로 데이터를 읽어서
	// 멤버 변수 list에 저장
	private void readDataFromFile() {
		InputStream in = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;
		try {
//		in = new FileInputStream("data/contact.data");
			in = new FileInputStream(dataFile);
			bin = new BufferedInputStream(in);
			oin = new ObjectInputStream(bin);
			
			list = (ArrayList<ContactVO>) oin.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	} // end readDataFromFile()
	
	// FileOutputStream, BufferedOutputStream, ObjectOutputStream
	// 멤버 변수 list의 내용을 data\contact.data 파일에 씀
	// 메소드 완성 후 insert(), update(), delete()에서 호출
	private void writeDataToFile() {
		OutputStream out = null;
		BufferedOutputStream bout = null;
		ObjectOutputStream oout = null;
		try {
//			out = new FileOutputStream("data/contact.data");
			out = new FileOutputStream(dataFile);
			bout = new BufferedOutputStream(out);
			oout = new ObjectOutputStream(bout);
			
			oout.writeObject(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	} // end writeDataToFile()
	
	@Override
	public int insert(ContactVO vo) {
		// 새로운 연락처 정보를 메모리에 있는 ArrayList에 저장
		list.add(vo);
		// 바뀐 내용을 파일에 씀
		writeDataToFile();
		return 1;
	}

	@Override
	public ArrayList<ContactVO> select() {
		
		return list;
	}

	@Override
	public ContactVO select(int index) {
		if (index >= 0 && index < list.size()) {
			return list.get(index);
		} else {
			return null;
		}
		
	}

	@Override
	public int update(int choice, int index, ContactVO vo) {
		if (index >= 0 && index < list.size()) {
			switch (choice) {
			case 1:
				// list.set(index,vo);
				/* setName 사용을 원한다면 */
				// list.get(index); -> 특정 인덱스 값 꺼냈음
				list.get(index).setName(vo.getName());
				writeDataToFile();
				break;
			case 2:
				// list.set(index,vo);
				list.get(index).setPhone(vo.getPhone());
				writeDataToFile();
				break;
			case 3:
				// list.set(index,vo);
				list.get(index).setEmail(vo.getEmail());
				writeDataToFile();
				break;
			}	
			return 1;
		} else {
			return 0;
		}
		
	}

	@Override
	public int delete(int index) {
		if (index >= 0 && index < list.size()) {
			list.remove(index);
			writeDataToFile();
			return 1;
		} else {
			return 0;
		}
		
	}
	
} // end class ContactDAOImple