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
	private boolean stage1Video = false;
	private boolean stage2Video = false;
	private boolean stage3Video = false;
	private boolean stage4Video = false;
	

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

	public boolean isStage1Video() {
		return stage1Video;
	}

	public void setStage1Video(boolean stage1Video) {
		this.stage1Video = stage1Video;
	}

	public boolean isStage2Video() {
		return stage2Video;
	}

	public void setStage2Video(boolean stage2Video) {
		this.stage2Video = stage2Video;
	}

	public boolean isStage3Video() {
		return stage3Video;
	}

	public void setStage3Video(boolean stage3Video) {
		this.stage3Video = stage3Video;
	}

	public boolean isStage4Video() {
		return stage4Video;
	}

	public void setStage4Video(boolean stage4Video) {
		this.stage4Video = stage4Video;
	}
	

}
