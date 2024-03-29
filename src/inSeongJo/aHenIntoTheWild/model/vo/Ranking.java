package inSeongJo.aHenIntoTheWild.model.vo;

import java.io.Serializable;

public class Ranking implements Serializable {

	private String name;
	private int score;

	public Ranking() {
	}

	public Ranking(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Ranking [name=" + name + ", score=" + score + "]";
	}

}
