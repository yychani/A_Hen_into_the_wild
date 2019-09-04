package inSeongJo.aHenIntoTheWild.model.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import inSeongJo.aHenIntoTheWild.model.vo.User;

public class UserDao {

	public UserDao() {
	}

	public ArrayList<User> readUserList(){
		ObjectInputStream ois = null;
		ArrayList<User> list = null;
		
		
		try {
			ois = new ObjectInputStream(new FileInputStream("userList.dat"));
			
			list = (ArrayList<User>)ois.readObject();
			
		} catch (FileNotFoundException e) {
			new File("userList.dat");
		} catch (EOFException e) {
			
		}
		catch (ClassNotFoundException |IOException e) {
			e.printStackTrace();
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		
		return list;
	}

	// 파일에 리스트 기록하기용 메소드
	public int writeUserList(ArrayList<User> list) {
		ObjectOutputStream oos = null;
		int result = 0;

		try {
			oos = new ObjectOutputStream(new FileOutputStream("userList.dat"));

			oos.writeObject(list);

			result++;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
