package inSeongJo.aHenIntoTheWild.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import inSeongJo.aHenIntoTheWild.model.vo.Ranking;

public class RankingDao {
	
	public RankingDao(){
		
	}
	
	
	
	public ArrayList<Ranking> readRankingList(int stage){
		File file = new File("stage3Rank.dat");
//		switch(stage) {
//		case 1 : file = new File("stage1Rank.dat"); break;
//		case 2 : file = new File("stage2Rank.dat"); break;
//		case 3 : file = new File("stage3Rank.dat"); break;
//		case 4 : file = new File("stage4Rank.dat"); break;
//		}
		ArrayList<Ranking> list = null;
		//ObjectInputStream ois = null;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			//ois = new ObjectInputStream(new FileInputStream("stage3Rank.dat"));
			list = (ArrayList<Ranking>)ois.readObject();
			
		} catch (FileNotFoundException e){
			System.out.println("파일이 없습니다.");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int writeRankingList(ArrayList<Ranking> list, int stage) {
		int result = 0;
		File file = null;
		switch(stage) {
		case 1 : file = new File("stage1Rank.dat"); break;
		case 2 : file = new File("stage2Rank.dat"); break;
		case 3 : file = new File("stage3Rank.dat"); break;
		case 4 : file = new File("stage4Rank.dat"); break;
		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(list);
			
			result++;
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}

}
