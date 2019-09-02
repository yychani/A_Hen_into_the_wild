package inSeongJo.aHenIntoTheWild.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import inSeongJo.aHenIntoTheWild.model.dao.UserDao;
import inSeongJo.aHenIntoTheWild.model.vo.User;
import inSeongJo.aHenIntoTheWild.view.ResultPrinter;

public class UserManager {
	private UserDao ud = new UserDao();
	private ResultPrinter rp = new ResultPrinter();
	ArrayList<User> ulist;

	// �� ���� ��Ͽ� �޼ҵ�
	public void insertUser(User u) {

		// ���Ͽ� ��ϵ� ����Ʈ ��ȸ
		ulist = ud.readUserList();

		// ��ȸ ������ �ִ��� Ȯ��
		if (ulist == null) {
			// ��ȸ ������ ���� ��� ���ο� ���� ����

			ulist = new ArrayList<User>();
		}

		// ��������Ʈ�� ���ο� ������ü �߰�
		ulist.add(u);

		// ����Ʈ�� ���Ͽ� ��� �� ����� ������ ����
		int result = ud.writeUserList(ulist);

		System.out.println(ulist);

	}

	// ���̵� �ߺ� Ȯ��
	public boolean idCheck(String id) {
		ulist = ud.readUserList();
		System.out.println(id);
		System.out.println(ulist);
		// System.out.println(ulist.size());

		boolean check = false;
		
		if (ulist != null) {
			for (User user : ulist) {
				if (user.getId().equals(id)) {
					System.out.println("���̵� �ߺ�");
					check = true;
					break; // �ߺ��Ǵ� ���̵�, true�� ��ȯ
				}

			}
		}else {
			System.out.println("�ΰ�");
		}

		/*
		 * for (User user : ulist) {
		 * 
		 * if (ulist != null) { if (user.getId().equals(id)) {
		 * System.out.println("���̵� �ߺ�"); check = true; break; // �ߺ��Ǵ� ���̵�, true�� ��ȯ } }
		 * else { System.out.println("�ΰ�"); } }
		 */

		return check;

	}

	// �α���
	public User login(String id, String pw) {

		boolean check = false;
		// �α��� ���� : true , ���� : false ��ȯ

		ulist = ud.readUserList();
		
		User loginUser = new User();
		
		for(User user : ulist) {
			if(user.getId().equals(id)) {
				if(user.getPassword().equals(pw)) {
					//�α��� ����
					System.out.println(user);
					loginUser = user;
					check = true;
				}else {
					//�α��� ����
					loginUser = null;
					check = false;
				}
				
			}
				
		}
		 

		return loginUser;
	}

}
