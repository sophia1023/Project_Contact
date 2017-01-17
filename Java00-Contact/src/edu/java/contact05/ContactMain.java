package edu.java.contact05;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class ContactMain {

	private JFrame frame;
	private JTextField textIndex;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;

	private ContactDAO dao;
	private JTextArea textArea;
	private JButton btnName;
	private JButton btnPhone;
	private JButton btnEmail;
	private JTextField textEdit;
	private JButton btnOK;
	
	private int index;
	private int choice;
	private ContactVO vo;
	private boolean editName;
	private boolean editPhone;
	private boolean editEmail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactMain window = new ContactMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContactMain() {
		dao = ContactDAOImple.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 456, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea countArea = new JTextArea();
		ArrayList<ContactVO> list = dao.select();
		int count = list.size();
		StringBuffer buffer = new StringBuffer();
		buffer.append("현재 등록인원 : ").append(count).append("명");
		countArea.setText(buffer.toString());

		countArea.setBackground(new Color(245, 245, 245));
		countArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
		countArea.setBounds(33, 37, 110, 20);
		frame.getContentPane().add(countArea);

		JLabel lblTile = new JLabel("연락처 ver 0.5");
		lblTile.setFont(new Font("굴림", Font.PLAIN, 15));
		lblTile.setHorizontalAlignment(SwingConstants.CENTER);
		lblTile.setBorder(new LineBorder(new Color(255, 175, 175), 2));
		lblTile.setBounds(148, 10, 119, 28);
		frame.getContentPane().add(lblTile);

		JLabel lblIndex = new JLabel("인덱스");
		lblIndex.setFont(new Font("굴림", Font.PLAIN, 13));
		lblIndex.setBorder(new LineBorder(Color.ORANGE, 2));
		lblIndex.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndex.setOpaque(true);
		lblIndex.setBounds(33, 59, 62, 28);
		frame.getContentPane().add(lblIndex);

		JLabel lblName = new JLabel("이름");
		lblName.setOpaque(true);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("굴림", Font.PLAIN, 13));
		lblName.setBorder(new LineBorder(Color.ORANGE, 2));
		lblName.setBounds(33, 91, 62, 28);
		frame.getContentPane().add(lblName);

		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setOpaque(true);
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("굴림", Font.PLAIN, 13));
		lblPhone.setBorder(new LineBorder(Color.ORANGE, 2));
		lblPhone.setBounds(33, 123, 62, 28);
		frame.getContentPane().add(lblPhone);

		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setOpaque(true);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("굴림", Font.PLAIN, 13));
		lblEmail.setBorder(new LineBorder(Color.ORANGE, 2));
		lblEmail.setBounds(33, 155, 62, 28);
		frame.getContentPane().add(lblEmail);

		textIndex = new JTextField();
		textIndex.setBounds(107, 60, 304, 27);
		frame.getContentPane().add(textIndex);
		textIndex.setColumns(10);

		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(107, 92, 304, 27);
		frame.getContentPane().add(textName);

		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(107, 124, 304, 27);
		frame.getContentPane().add(textPhone);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(107, 156, 304, 27);
		frame.getContentPane().add(textEmail);

		JButton btnInsert = new JButton("등록");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnInsert.setBounds(33, 193, 72, 23);
		frame.getContentPane().add(btnInsert);

		JButton btnSelect = new JButton("검색");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectIndex();
			}
		});

		JButton btnAll = new JButton("전체검색");
		btnAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectAll();
			}
		});

		btnAll.setBounds(107, 193, 87, 23);
		frame.getContentPane().add(btnAll);
		btnSelect.setBounds(195, 193, 72, 23);
		frame.getContentPane().add(btnSelect);

		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateMenu();
			}
		});
		btnUpdate.setBounds(268, 193, 72, 23);
		frame.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(340, 193, 72, 23);
		frame.getContentPane().add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(407, 292, -372, 182);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setBounds(33, 293, 378, 181);
		frame.getContentPane().add(textArea);

		btnName = new JButton("이름");
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editName = true;
				textEdit.setVisible(true);
				btnOK.setVisible(true);
			}
		});
		btnName.setBounds(33, 230, 72, 23);
		frame.getContentPane().add(btnName);
		btnName.setVisible(false);

		btnPhone = new JButton("전화번호");
		btnPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPhone = true;
				textEdit.setVisible(true);
				btnOK.setVisible(true);
			}
		});
		btnPhone.setBounds(107, 230, 87, 23);
		frame.getContentPane().add(btnPhone);
		btnPhone.setVisible(false);

		btnEmail = new JButton("이메일");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editEmail = true;
				textEdit.setVisible(true);
				btnOK.setVisible(true);
			}
		});
		btnEmail.setBounds(195, 230, 87, 23);
		frame.getContentPane().add(btnEmail);
		btnEmail.setVisible(false);

		textEdit = new JTextField();
		textEdit.setColumns(10);
		textEdit.setBounds(33, 256, 304, 27);
		frame.getContentPane().add(textEdit);
		textEdit.setVisible(false);

		btnOK = new JButton("확인");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}

		});
		btnOK.setBounds(340, 258, 72, 23);
		frame.getContentPane().add(btnOK);
		btnOK.setVisible(false);

	}

	private void insert() {
		String name = textName.getText();
		String phone = textPhone.getText();
		String email = textEmail.getText();

		vo = new ContactVO(name, phone, email);
		int result = dao.insert(vo);
		if (result == 0) {
			System.out.println("등록 실패");
		} else {
			System.out.println("등록 성공!");
		}

	}

	private void selectAll() {
		ArrayList<ContactVO> list = dao.select();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			buffer.append("◆  No." + (i + 1)).append(list.get(i).toString()).append("\n");
		}
		textArea.setText(buffer.toString());
	}

	private void selectIndex() {
		StringBuffer buffer = new StringBuffer();
		index = Integer.parseInt(textIndex.getText());
		buffer.append("◆  No." + (index + 1)).append(dao.select(index));
		textArea.setText(buffer.toString());
	}

	private void showUpdateMenu() {
		index = Integer.parseInt(textIndex.getText());
		vo = dao.select(index);
		btnName.setVisible(true);
		btnPhone.setVisible(true);
		btnEmail.setVisible(true);

	}

	private void update() {
		int result = 0;
		if(editName == true){
			choice = 1;
			String name = textEdit.getText();
			vo = new ContactVO(name, null, null);
			result = dao.update(choice, index, vo);
		} else if(editPhone == true){
			choice = 2;
			String phone = textEdit.getText();
			vo = new ContactVO(null, phone, null);
			result = dao.update(choice, index, vo);
		} else if(editEmail == true){
			choice = 3;
			String email = textEdit.getText();
			vo = new ContactVO(null, null, email);
			result = dao.update(choice, index, vo);
		}
		
		if (result == 0) {
			System.out.println("수정 실패");
		} else {
			System.out.println("수정 성공!");
		}
		
	}

	private void delete() {
		ArrayList<ContactVO> list = dao.select();
		index = Integer.parseInt(textIndex.getText());
		int result = dao.delete(index);
		if (result == 0) {
			System.out.println("삭제 실패");
		} else {
			System.out.println("삭제 성공!");
		}

	}
}
