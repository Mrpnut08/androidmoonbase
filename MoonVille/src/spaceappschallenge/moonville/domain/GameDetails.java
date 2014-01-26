package spaceappschallenge.moonville.domain;

import java.io.Serializable;

/**
 * Stores game specific settings (player name, difficulty, current turn 
 * and it's save file)
 */
public class GameDetails implements Serializable{
	
	private static final long serialVersionUID = -7546492796233811240L;
	
	private String player_name; 
	
	private int difficulty_level; //< Holds one of the difficulty constants defined
								  //< in the Difficulty class (EASY, MEDIUM or HARD).
	
	private int turn;	///< Represents the turn the player is currently at.
	
	private String save_file; ///< The name of the file the game is saved in.
							  ///< This is not the full path.

	///< Class constructor.
	/**
	 * Creates a new object based on information obtained through user input.
	 * The generated object is assumed to be a new game, starting from 0.
	 * @param player_name The player's name.
	 * @param difficulty_level The player's chosen difficulty level.
	 * @param save_file 
	 */
	public GameDetails(String player_name, int difficulty_level, String save_file) {
		
		//Stores the player's name and chosen difficulty.
		this.player_name = player_name;
		this.difficulty_level = difficulty_level;
		
		//Store the save file for future saves.
		this.save_file = save_file;
		
		//Set the current turn to 0, meaning that the player has not
		//yet started playing.
		this.turn = 0;
	}

	///< Gets the player's name.
	public String getPlayerName() {
		return player_name;
	}

	///< Gets the difficulty level.
	public int getDifficultyLevel() {
		return difficulty_level;
	}
	
	///< Gets the current turn.
	public int getTurn() {
		return this.turn;
	}
	
	///< Gets the save file's name.
	public String getSaveFile(){
		return this.save_file;
	}
}
