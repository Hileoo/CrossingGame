/**
 * Game class is the class that create a new game-start from map1
 * Map background and GUI is implemented in class: Map1, Map2, Map3
 * The function of move, carry plank, set plank are implemented in class: Map1, Map2, Map3
 * Promote to level2(map2) is implemented in class Map1: when man reach to Finish-Point, create Map2
 * Promote to level3(map3) is implemented in class Map2: when man reach to Finish-Point, create Map3
 * Timer is defined in the class Timer, and applied for each Map class
 * Score is defined in the class Score, and applied for each Map class
 * 
 *
 *
 */
public class Game 
{
	/**
	 * The main method to create the start of the game from level1(map1)
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Map1();    
	}

}
