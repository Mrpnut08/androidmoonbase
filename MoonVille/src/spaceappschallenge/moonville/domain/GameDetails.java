package spaceappschallenge.moonville.domain;

import java.io.Serializable;

/**
 * Stores game specific settings (player name and difficulty).
 */
public class GameDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7546492796233811239L;
	private String username;
	private int difficulty_level;
	private int turn;

	public GameDetails(String name, int difficulty) {
		this.username = name;
		this.difficulty_level = difficulty;
		this.turn = 0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDifficultyLevel() {
		return difficulty_level;
	}

	public void setDifficultyLevel(int difficulty_level) {
		this.difficulty_level = difficulty_level;
	}
	
	public int getTurn() {
		return this.turn;
	}
}
