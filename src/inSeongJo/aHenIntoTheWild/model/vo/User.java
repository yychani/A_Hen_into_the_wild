package inSeongJo.aHenIntoTheWild.model.vo;

import java.io.Serializable;

public class User implements Serializable {

	private String id;
	private String password;
	private String email;
	private String nickName;
	private int totalScore;
	private int stage1Score;
	private int stage2Score;
	private int stage3Score;
	private int stage4Score;

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", email=" + email + ", nickName=" + nickName
				+ ", totalScore=" + totalScore + ", stage1Score=" + stage1Score + ", stage2Score=" + stage2Score
				+ ", stage3Score=" + stage3Score + ", stage4Score=" + stage4Score + "]";
	}

	public User() {
	}

	public User(String id, String password, String email, String nickName, int totalScore, int stage1Score,
			int stage2Score, int stage3Score, int stage4Score) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.nickName = nickName;
		this.totalScore = totalScore;
		this.stage1Score = stage1Score;
		this.stage2Score = stage2Score;
		this.stage3Score = stage3Score;
		this.stage4Score = stage4Score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getStage1Score() {
		return stage1Score;
	}

	public void setStage1Score(int stage1Score) {
		this.stage1Score = stage1Score;
	}

	public int getStage2Score() {
		return stage2Score;
	}

	public void setStage2Score(int stage2Score) {
		this.stage2Score = stage2Score;
	}

	public int getStage3Score() {
		return stage3Score;
	}

	public void setStage3Score(int stage3Score) {
		this.stage3Score = stage3Score;
	}

	public int getStage4Score() {
		return stage4Score;
	}

	public void setStage4Score(int stage4Score) {
		this.stage4Score = stage4Score;
	}

}
