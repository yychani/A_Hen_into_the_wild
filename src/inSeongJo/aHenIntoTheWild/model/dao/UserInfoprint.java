package inSeongJo.aHenIntoTheWild.model.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserInfoprint {

	public UserInfoprint() {
		try {
			FileReader fr = new FileReader("./userlist.dat");

			BufferedReader br = new BufferedReader(fr);
			int line = 0;
			while ((line = br.read()) != -1) {
				System.out.println((char) line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new UserInfoprint();
	}

}
