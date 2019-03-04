package utils;

/*
 * This is a simple Constants file,
 * where all the constants in the project are specified in this class.
 * 
 * */
public class GameConstant {
	// Constant Variables across the project
	public static final int MAXIMUM_NUMBER_OF_PLAYERS = 6;
	public static final int MINIMUM_NUMBER_OF_PLAYERS = 2;
	// Constant Strings
	public static final String PROJECT_TITLE = new String("Risk - SOEN 6441 Porject - Group # 40");
	public static final String GAME_TITLE = new String("Risk Game");
	// Button Title Strings
	public static final String NEW_GAME_BUTTON_TITLE = new String("New Game");
	public static final String EXIT_GAME_BUTTON_TITLE = new String("Exit Game");
	public static final String REINFORCEMENT_BUTTON_TITLE = new String("Reinforcement");
	public static final String ATTACK_BUTTON_TITLE = new String("Attack");
	public static final String FORTIFY_BUTTON_TITLE = new String("Fortify");
	// some other string
	public static final String SELECT_PLAYERS = new String("Select the number of players");
	public static final String PLACE_ARMY = new String("Place your armies in any of your countries");
	// TODO: Please make changes in the below statements, just like the above modifications
	public String SELECT_ATTACKER_COUNTRY="Select one of your countries for attacking";
	public String SELECT_DEFENDER_COUNTRY="Select one of your adjacent countries for attacking";
	public String NUMBER_OF_DICES="Select the number of dices";
	public String ROLL_DICE="Rolling dices";
	public String ATTACKER_WIN="Attacker wins";
	public String DEFENDER_WIN="Defender wins";
	public static String MAP_FILE_PATH = System.getProperty("user.dir") + "\\src\\resources\\World.map";
	public static String MAP_IMAGE_FILE_PATH = System.getProperty("user.dir") + "\\src\\resources\\World.bmp";
	public static String CONTINENT_DATA_SPLITTER = "=";
	public static String COUNTRY_DATA_SPLITTER = ",";

	public GameConstant() {
			
	}

}
