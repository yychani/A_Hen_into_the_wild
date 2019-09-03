package inSeongJo.aHenIntoTheWild.model.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import inSeongJo.aHenIntoTheWild.model.vo.Ranking;

public class RankingDao {
	
	public RankingDao(){
		
	}
	
	public ArrayList<Ranking> readRankingList(int stage){
		String file = null;
		switch(stage) {
		case 1 : file = "stage1Rank.dat"; break;
		case 2 : file = "stage2Rank.dat"; break;
		case 3 : file = "stage3Rank.dat"; break;
		case 4 : file = "stage4Rank.dat"; break;
		}
		ArrayList<Ranking> list = null;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			list = (ArrayList<Ranking>)ois.readObject();
			
		} catch (FileNotFoundException e){
			System.out.println("파일이 없습니다.");
		} catch (EOFException e) {
			System.out.println("파일의 끝입니다.");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int writeRankingList(ArrayList<Ranking> list, int stage) {
		int result = 0;
		String file = null;
		switch(stage) {
		case 1 : file = "stage1Rank.dat"; break;
		case 2 : file = "stage2Rank.dat"; break;
		case 3 : file = "stage3Rank.dat"; break;
		case 4 : file = "stage4Rank.dat"; break;
		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(list);
			
			result++;
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		
		return result;
	}

}
