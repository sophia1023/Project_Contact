package edu.java.contact04;

import java.util.ArrayList;

public interface ContactDAO {
	// 연락처 등록
	public abstract int insert(ContactVO vo);
	// 전체 검색 기능
	public abstract ArrayList<ContactVO> select();
	// 인덱스 검색 기능
	public abstract ContactVO select(int index);
	// 연락처 수정 - 이름, 전화번호, 이메일 수정
	public abstract int update(int choice, int index, ContactVO vo);
	// 연락처 삭제
	public abstract int delete(int index);

} // end interface ContactDAO


