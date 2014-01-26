package spaceappschallenge.moonville.domain;

import java.io.Serializable;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.miscellaneous.MoonVille;
import android.content.res.Resources;

/**
 * Provides values based on difficulty level.
 */
public class Difficulty implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5813235211061576115L;
	public static final int EASY = 0;
	public static final int NORMAL = 1;
	public static final int HARD = 2;

	private int researchPoints;
	private int prospectingLevel;
	private int money;
	private int launchMass;
	private Resources resources;

	// some basic difficulties... not final! ~ jodli
	public Difficulty(int difficultyLevel) {
		resources = MoonVille.getContext().getResources();
		switch (difficultyLevel) {

		case NORMAL:
			this.researchPoints = resources
					.getInteger(R.integer.difficult_research);
			this.prospectingLevel = resources
					.getInteger(R.integer.difficult_prospecting_level);
			this.money = resources.getInteger(R.integer.difficult_money);
			this.launchMass = resources
					.getInteger(R.integer.difficult_launchMass);
			break;
		case HARD:
			this.researchPoints = resources
					.getInteger(R.integer.medium_research);
			this.prospectingLevel = resources
					.getInteger(R.integer.medium_prospecting_level);
			this.money = resources.getInteger(R.integer.medium_money);
			this.launchMass = resources.getInteger(R.integer.medium_launchMass);
			break;

		default:

			this.researchPoints = resources.getInteger(R.integer.easy_research);
			this.prospectingLevel = resources
					.getInteger(R.integer.easy_prospecting_level);
			this.money = resources.getInteger(R.integer.easy_money);
			this.launchMass = resources.getInteger(R.integer.easy_launchMass);
			break;

		}
	}

	public int getResearchPoints() {
		return researchPoints;
	}

	public int getProspectingLevel() {
		return prospectingLevel;
	}

	public int getMoney() {
		return money;
	}

	public int getLaunchMass() {
		// TODO Auto-generated method stub
		return launchMass;
	}
}
