import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Score class is the class that implement write score to the specific file
 * And read all scores which have been record in the specific file
 * Find the highest score (shortest time) in the records
 * Include the method that get highest score
 * 
 * 
 *
 */
public class Score 
{
	private String HIGH_SCORE;
	
	/**
	 * This method write a score (time string) to specific file
	 * The file is used to record each score of specific map (level)
	 * The score record is formatted as each record occupies a single line, then separate a new line 
	 * @param score  the score which needs to write in to the file 
	 * @param fileName  the file name (path) which the score been record, must suitable for specific level
	 */
	public void writeScore(String score, String fileName)
	{
		try
		{
			
			FileWriter writer=new FileWriter(fileName,true);
			//Regular the format: score + separate a new line
			writer.write(score + System.getProperty("line.separator"));
			writer.close();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method read all the records from specific file
	 * And Compare all the record with Highest Score
	 * Set as new Highest Score if the record is shorter than Highest Score
	 * @param fileName  the file name that read from
	 */
	public void readAllScore(String fileName)
	{
		HIGH_SCORE = "59:59";//Set a initial Highest Score, used for the situation that having null record in file
		String line="";//"Pointer" to read all score line by line
		try
		{
			BufferedReader in=new BufferedReader(new FileReader(fileName));
			line=in.readLine();
			while (line!=null)
			{
				//Fine the highest score line by line
				if(line.compareTo(HIGH_SCORE) < 0)
				{
					HIGH_SCORE = line;
				}
				
				line=in.readLine();
			}
			in.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * The method to get Highest Score
	 * @return  the String of the Highest Score
	 */
	public String getHighScore()
	{
		return HIGH_SCORE;
	}
		
}
