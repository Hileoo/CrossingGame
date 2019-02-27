import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * The Map3 class is used to sets up basic background, operation and initiates of map3
 * Implements MouseListener, so different action will be implemented by LEFT CLICK and RIGHT CLICK
 * Operation tips: LEFT CLICK: move the man along the plank and stump, also can move across multiple-stump
 * Operation tips: RIGHT CLICK: click on a plank or a stump close to the man, and carry or set the plank  
 * 
 *
 */
public class Map3 implements MouseListener
{
	//Window size and grid size
	private final int WINDOW_WIDTH = 450;
	private final int WINDOW_HEIGHT = 650; 
	private final int ROW =13;
	private final int COLUMN = 9;
	//Use the Image class
	private Images image;
	
	//Basic java swing component
	private JFrame frame;
	private JPanel panel;
	private JButton[][] button;
	private JPanel upInfo;
	private JTextField timerInfo;
	private JTextField highScore;
	
	//Start information of map1
	private final int xStart = 12, yStart = 2;
	private final int xFinish = 0, yFinish = 6;
	private int xPos = 12;//The initial x-location of man
	private int yPos = 2;//The initial y-location of man
	
	//Basic action variable
	private boolean canMove;
	private boolean canCarryPlank, canSetPlank;
	private boolean haveCarryPlank;
	private int carryPlank = 0;//The number of the plank have been carried, valid when 0 or 1
	private int CARRY_LENGTH,SET_LENGTH;//The length of the plank to carry or place
	private int MAX_LENGTH = 5;

	//Map3 background information, used for implementing the action
	private String mapArray3[][] = {
			{"water","water","water","water","water","water","stump","water","water"},
			{"water","water","water","water","water","water","water","water","water"},
			{"water","water","stump","water","water","water","water","water","stump"},
			{"water","water","plank","water","water","water","water","water","water"},
			{"water","water","plank","water","stump","water","stump","water","water"},
			{"water","water","plank","water","water","water","water","water","water"},
			{"stump","water","plank","water","water","water","stump","water","water"},
			{"water","water","plank","water","water","water","water","water","water"},
			{"water","water","stump","water","water","water","stump","water","stump"},
			{"water","water","plank","water","water","water","water","water","plank"},
			{"stump","water","plank","water","stump","water","water","water","stump"},
			{"water","water","plank","water","water","water","water","water","water"},
			{"water","water","stump","water","water","water","water","water","water"},};
	
	/**
     * Constructor for class Map3 
     */
	public Map3()
    {
    	
    	this.setWindow();
       
        this.imgInit();
        
        this.basicBackground(frame,panel,button);
        this.map3Background(frame,panel,button);
        
        this.addButton(panel, button);
        this.mouseListener();
        
        frame.setTitle("The Perilous Plank Puzzle Conundrum - Level3");
        frame.setVisible(true);
        frame.setResizable(false);
    }
	
    /**
     * The method to set basic window component and define the detail of these component
     * Include frame, panel, button
     * Extra component of Timer and High Score
     */
    public void setWindow()
    {
    	frame = new JFrame();
    	panel = new JPanel();
    	button = new JButton[ROW][COLUMN];
    	
    	//Use the Images class
    	image = new Images();
    	
    	frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT+35);//Extra size for upInfor
        frame.add(panel);       
        
        //Create the extra component
        upInfo = new JPanel();
        timerInfo = new JTextField();
        Timer timer = new Timer();
        Thread timeThread = new Thread(timer);
        Score score = new Score();
        highScore = new JTextField();
    	
        //Define the timerInfo
        timerInfo = timer.text();
        timerInfo.setHorizontalAlignment(JTextField.CENTER);
        timerInfo.setEditable(false);
        timerInfo.setBackground(Color.GREEN);
        
        //Define the highScore
        score.readAllScore("Score/score3.txt");
        highScore.setText("High Score is: " + score.getHighScore());
        highScore.setHorizontalAlignment(JTextField.CENTER);
        highScore.setEditable(false);
        highScore.setBackground(Color.GREEN);
        
        //Add highScore first, and then add timerInfo to upInfo
        upInfo.add(highScore);
        upInfo.add(timerInfo);
        
        timeThread.start();
        
        //Set the layout
        frame.setLayout(new BorderLayout(0,0));
        upInfo.setLayout(new GridLayout(1,2,0,0));
        panel.setLayout(new GridLayout(ROW,COLUMN,0,0));
        frame.add("North", upInfo);
        frame.add("Center", panel);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    /**
     * This method initialize the ImageIcon from class Images
     */
    public void imgInit()
    {
    	image.Water1();
    	image.Water2();
    	image.Water3();
    	image.Water4();
    	image.Bank1();
    	image.Bank2();
    	image.Plank1();
    	image.Plank2();
    	image.Plank1Man();
    	image.Plank2Man();
    	image.Stump1();
    	image.Stump2();
    	image.Stump3();
    	image.Stump1Man();
    	image.Stump2Man();
    	image.Stump3Man();	
    }
    
    /**
     * This method set the basic Image Background of the map
     * Include the top bank, bottom bank, and middle water
     * A "blank" background, which may be replaced by specific map information
     * @param frame  the frame to add panel
     * @param panel  the panel to add button
     * @param button  the button array, added in the main panel, set as ImageIcon
     */
    public void basicBackground(JFrame frame,JPanel panel,JButton button[][])
    {
    	for(int i=0;i<COLUMN;i++)
        {
        	button[0][i] = setImageIcon(image.getBank2(), new JButton());
        }
        for(int i=0;i<COLUMN;i++)
        {
            button[12][i] = setImageIcon(image.getBank1(), new JButton());
        }
        for(int i=1;i<ROW-1;i++)
        {
            for(int j=0;j<COLUMN;j++)
            {
                button[i][j] = setImageIcon(image.getWater1(), new JButton());
            }
        }
    }
    
    /**
     * The method to add each button in button array to panel
     * @param panel  the panel which add button to
     * @param button  the button array which each button comes from
     */
    public void addButton(JPanel panel, JButton button[][])
    {
    	for(int i=0;i<ROW;i++)
        {
           for(int j=0;j<COLUMN;j++)
           {
             panel.add(button[i][j]);
           }
        }
    }
    
    /**
     * This method define the setImageIcon method, used for set ImageIcon to specific button
     * Scaled the image elements to be suitable for the window size
     * @param original  the original ImageIcon which image comes from
     * @param buttonSet  the specific button which set ImageIcon for
     * @return  return buttonSet, the specific button which has set for ImageIcon
     */
    public JButton setImageIcon(ImageIcon original, JButton buttonSet)
    {
    	ImageIcon icon = original;
    	icon.getImage();
		Image temp = icon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	icon = new ImageIcon(temp);
    	buttonSet.setIcon(icon);
    	buttonSet.setBorder(null);
    	buttonSet.setContentAreaFilled(false);
    	return buttonSet;	
    }
    
    /**
     * map3Background method provide the specific image background of map3
     * Replace some button in "basicBackground" method
     * @param frame  the frame to add panel in map3
     * @param panel  the panel to add button in map3
     * @param button  the button array in map3, added in the main panel, set as ImageIcon
     */
    public void map3Background(JFrame frame,JPanel panel,JButton button[][])
    {
    	button[12][2] = setImageIcon(image.getStump2Man(), new JButton());
    	button[0][6] = setImageIcon(image.getStump3(), new JButton());
        button[6][0] = setImageIcon(image.getStump1(), new JButton());
        button[10][0] = setImageIcon(image.getStump1(), new JButton());
        button[2][2] = setImageIcon(image.getStump1(), new JButton());
        button[8][2] = setImageIcon(image.getStump1(), new JButton());
        button[4][4] = setImageIcon(image.getStump1(), new JButton());
        button[10][4] = setImageIcon(image.getStump1(), new JButton());
        button[4][6] = setImageIcon(image.getStump1(), new JButton());
        button[6][6] = setImageIcon(image.getStump1(), new JButton());
        button[8][6] = setImageIcon(image.getStump1(), new JButton());
        button[2][8] = setImageIcon(image.getStump1(), new JButton());
        button[8][8] = setImageIcon(image.getStump1(), new JButton());
        button[10][8] = setImageIcon(image.getStump1(), new JButton());
        button[8][0] = setImageIcon(image.getWater2(), new JButton());
        button[8][4] = setImageIcon(image.getWater2(), new JButton());
        button[7][6] = setImageIcon(image.getWater2(), new JButton());
        button[6][8] = setImageIcon(image.getWater2(), new JButton());
        button[2][4] = setImageIcon(image.getWater3(), new JButton());
        button[2][6] = setImageIcon(image.getWater3(), new JButton());
        button[9][7] = setImageIcon(image.getWater3(), new JButton());
        button[4][1] = setImageIcon(image.getWater4(), new JButton());
        button[6][3] = setImageIcon(image.getWater4(), new JButton());
        button[4][8] = setImageIcon(image.getWater4(), new JButton());
        button[3][2] = setImageIcon(image.getPlank2(), new JButton());
        button[4][2] = setImageIcon(image.getPlank2(), new JButton());
        button[5][2] = setImageIcon(image.getPlank2(), new JButton());
        button[6][2] = setImageIcon(image.getPlank2(), new JButton());
        button[7][2] = setImageIcon(image.getPlank2(), new JButton());
        button[9][2] = setImageIcon(image.getPlank2(), new JButton());
        button[10][2] = setImageIcon(image.getPlank2(), new JButton());
        button[11][2] = setImageIcon(image.getPlank2(), new JButton());
        button[9][8] = setImageIcon(image.getPlank2(), new JButton());               
    }
    
    /**
     * This method add mouseListener to each button element
     */
    public void mouseListener()
    {
    	for(int i=0;i<ROW;i++)
    	{
    		for(int j=0;j<COLUMN;j++)
    		{
    			button[i][j].addMouseListener(this);
    		}
    	}
    }
    
    /**
	 * The method to define the mouseListener by MouseEvent
	 * LEFT CLICK: move the man along to the plank and stump, also can move across multiple stumps if planks connected
	 * Move: can move if click plank or click stump, can not move if click water (fish)
	 * RIGHT CLICK: carry plank and set plank close to the man
	 * Carry Plank: carry plank if click the plank close to man when have carry no plank, also could click the stump close to man, then carry the plank between stump and man_stump
	 * Set Plank: set plank if click the plank or the stump close to man when carry a specific plank, the plank length is same to the carry one
	 */
	public void mouseClicked(MouseEvent e) 
	{
		//LETF CLICK
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			for(int i=0;i<ROW;i++)
			{
				for(int j=0;j<COLUMN;j++)
				{
					if(e.getSource() == button[i][j])
					{
						moveMan(xPos,yPos,i,j);
						break;
					}
				}
			}
		}
		
		//RIGHT CLICK
		if(e.getButton() == MouseEvent.BUTTON3)
		{
			//Initial the carry and set status
			canCarryPlank = true;
			canSetPlank = true;
			//Get the click position
			for(int i=0;i<ROW;i++)
			{
				for(int j=0;j<COLUMN;j++)
				{
					if(e.getSource() == button[i][j])
					{
						if(canCarryPlank == true)
						{
							carryPlank(xPos,yPos,i,j);	
						}
						if(canSetPlank == true)
						{
							setPlank(xPos,yPos,i,j);	
						}			
					}
				}
			}
		}
	}
	
	/**
	 * The method to move the man when LEFT CLICK in map3
	 * @param xMan  the x-location of man
	 * @param yMan  the y-location of man
	 * @param xClick  the x-location of Click
	 * @param yClick  the y-location of Click
	 */
	public void moveMan(int xMan, int yMan, int xClick, int yClick)
	{
		//Initialize the move status
		canMove = true;
		
		//Move Horizontal
		if((xMan == xClick)&&(Math.abs(yMan-yClick) > 0))
		{
			//Click at the right of the man, and move towards right
			if(yMan < yClick)
			{
				//The minimum distance is 1, and maximum distance is |yMan-yClick|
				for(int i=1;i<=Math.abs(yMan-yClick);i++)//fix i=0 to i=1
				{
					//can not move when the man and path is neither on a stump nor on a plank
					if(mapArray3[xMan][yMan+i] != "stump" && mapArray3[xMan][yMan+i] != "plank")
					{
						canMove = false;
						break;
					}
					
					if(yMan+i == yClick)
					{
						break;
					}
				}
				
				//the operation when the man can move
				if(canMove == true)
				{
					if(mapArray3[xMan][yMan] == "stump")
					{
						setImageIcon(image.getStump1(), button[xMan][yMan]);
					}
					
					else if(mapArray3[xMan][yMan] == "plank")
					{
						setImageIcon(image.getPlank1(), button[xMan][yMan]);
					}
					
					if(mapArray3[xClick][yClick] == "stump")
					{
						setImageIcon(image.getStump1Man(), button[xClick][yClick]);
					}
					
					else if(mapArray3[xClick][yClick] == "plank")
					{
						setImageIcon(image.getPlank1Man(), button[xClick][yClick]);
					}
					
					xPos = xClick;
					yPos = yClick;
				}
			}
			
			//Click at the left of the man, and move towards left
			else if(yMan > yClick)
			{
				//The minimum distance is 1, and maximum distance is |yMan-yClick|
				for(int i=1;i<=Math.abs(yMan-yClick);i++)
				{
					//can not move when the path is neither on a stump nor on a plank
					if(mapArray3[xMan][yMan-i] != "stump" && mapArray3[xMan][yMan-i] != "plank")
					{
						canMove = false;
						break;
					}
					
					if(yMan+i == yClick)
					{
						break;
					}
				}
				
				//the operation when the man can move
				if(canMove == true)
				{
					if(mapArray3[xMan][yMan] == "stump")
					{
						setImageIcon(image.getStump1(), button[xMan][yMan]);
					}
					
					else if(mapArray3[xMan][yMan] == "plank")
					{
						setImageIcon(image.getPlank1(), button[xMan][yMan]);
					}
					
					if(mapArray3[xClick][yClick] == "stump")
					{
						setImageIcon(image.getStump1Man(), button[xClick][yClick]);
					}
					
					else if(mapArray3[xClick][yClick] == "plank")
					{
						setImageIcon(image.getPlank1Man(), button[xClick][yClick]);
					}
					
					xPos = xClick;
					yPos = yClick;
				}
			}	
		}
		
		//Move Vertical
		if((yMan == yClick)&&(Math.abs(xMan-xClick) > 0))
		{
			//Click at the down of the man, and move towards down
			if(xMan < xClick)
			{
				//The minimum distance is 1, and maximum distance is |yMan-yClick|
				for(int i=1;i<=Math.abs(xMan-xClick);i++)
				{
					//can not move when the path is neither on a stump nor on a plank
					if(mapArray3[xMan+i][yMan] != "stump" && mapArray3[xMan+i][yMan] != "plank")
					{
						canMove = false;
						break;
					}
					
					if(xMan+i == xClick)
					{
						break;
					}
				}
				
				//the operation when the man can move
				if(canMove == true)
				{
					if(xMan == xFinish && yMan == yFinish)
					{
						setImageIcon(image.getStump3(),button[xMan][yMan]);
					}
					else if(mapArray3[xMan][yMan] == "stump")
					{
						setImageIcon(image.getStump1(), button[xMan][yMan]);
					}
					else if(mapArray3[xMan][yMan] == "plank")
					{
						setImageIcon(image.getPlank2(), button[xMan][yMan]);
					}
					
					if(xClick == xStart && yClick == yStart)
					{
						setImageIcon(image.getStump2Man(), button[xClick][yClick]);
					}
					else if(mapArray3[xClick][yClick] == "stump")
					{
						setImageIcon(image.getStump1Man(), button[xClick][yClick]);
					}
					else if(mapArray3[xClick][yClick] == "plank")
					{
						setImageIcon(image.getPlank2Man(), button[xClick][yClick]);
					}
					
					xPos = xClick;
					yPos = yClick;
				}
			}
			
			//Click at the up of the man, and move towards up
			else if(xMan > xClick)
			{
				//The minimum distance is 1, and maximum distance is |yMan-yClick|
				for(int i=1;i<=Math.abs(xMan-xClick);i++)
				{
					if(mapArray3[xMan-i][yMan] != "stump" && mapArray3[xMan-i][yMan] != "plank")
					{
						canMove = false;
						break;
					}
					
					if(xMan+i == xClick)
					{
						break;
					}
				}
				
				//the operation when the man can move
				if(canMove == true)
				{
					if(xMan == xStart && yMan == yStart)
					{
						setImageIcon(image.getStump2(), button[xMan][yMan]);
					}
					else if(mapArray3[xMan][yMan] == "stump")
					{
						setImageIcon(image.getStump1(), button[xMan][yMan]);
					}				
					else if(mapArray3[xMan][yMan] == "plank")
					{
						setImageIcon(image.getPlank2(), button[xMan][yMan]);
					}
					
					if(xClick == xFinish && yClick == yFinish)
					{
						setImageIcon(image.getStump3Man(), button[xClick][yClick]);
						//The operation when man reach to the finish position
						System.exit(0);
						Score score = new Score();
						score.writeScore(timerInfo.getText(), "Score/score3.txt");//record the time (score) in file "score3.txt"	
					}
					else if(mapArray3[xClick][yClick] == "stump")
					{
						setImageIcon(image.getStump1Man(), button[xClick][yClick]);
					}
					else if(mapArray3[xClick][yClick] == "plank")
					{
						setImageIcon(image.getPlank2Man(), button[xClick][yClick]);
					}
					
					xPos = xClick;
					yPos = yClick;
				}
			}
		}
	}
	
	/**
	 * The method to Pick Up and Carry a Plank in map3
	 * Can only carry a single plank at any point in time
	 * A plank can be picked up if the man is standing on a stump directly connected to the plank
	 * Click on the plank or the stump opposite to pick up a plank are both available
	 * @param xMan  the x-position of man
	 * @param yMan  the y-position of man
	 * @param xClick  the x-position of click
	 * @param yClick  the y-position of click
	 */
	public void carryPlank(int xMan, int yMan,int xClick, int yClick)
	{
		if(canCarryPlank = true);
		{
			//Vertical Direction
			if((yMan == yClick) && Math.abs(xMan - xClick)>0 && Math.abs(xMan-xClick)<7)
			{
				//After picking up a plank, the man is on a stump, and click is a water
				if(haveCarryPlank == true && carryPlank == 1)
				{
					if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "water")
					{
						canCarryPlank = false;
					}
				
				}
				
				//The man is on a stump, and click on a stump
				if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "stump")
				{
					//Click on the above of a man_stump
					if(xMan > xClick)
					{
						for(int i=1;i<Math.abs(xMan - xClick);i++)
						{
							//If in the middle of man_stump and clicked stump has other stump, can not pick up
							if(mapArray3[xMan-i][yMan] == "stump")
							{
								canCarryPlank = false;
								break;
							}
							//If in the middle of man_stump and clicked stump is not all be plank, can not pick up
							if(mapArray3[xMan-i][yMan] != "plank")
							{
								canCarryPlank = false;
								break;
							}		
						}
						
						//the operation when the man pick up a plank
						if(canCarryPlank == true && carryPlank == 0)
						{
							for(int i=1;i<Math.abs(xMan - xClick);i++)
							{
								setImageIcon(image.getWater1(), button[xMan-i][yMan]);
								mapArray3[xMan-i][yMan] = "water";
							}
							
							//change the carry status
							haveCarryPlank = true;
							carryPlank++;
							setCarryLength(Math.abs(xMan - xClick)-1);					
						}
					}
					
					//Click on the below of man_stump
					else if(xMan < xClick)
					{
						for(int i=1;i<Math.abs(xMan - xClick);i++)
						{
							//If in the middle of man_stump and clicked stump has other stump, can not pick up
							if(mapArray3[xMan+i][yMan] == "stump")
							{
								canCarryPlank = false;
								break;
							}
							//If in the middle of man_stump and clicked stump is not all be plank, can not pick up
							if(mapArray3[xMan+i][yMan] != "plank")
							{
								canCarryPlank = false;
								break;
							}		
						}
						
						//the operation when the man pick up a plank
						if(canCarryPlank == true && carryPlank == 0)
						{
							for(int i=1;i<Math.abs(xMan - xClick);i++)
							{
								setImageIcon(image.getWater1(), button[xMan+i][yMan]);
								mapArray3[xMan+i][yMan] = "water";
							}
							
							haveCarryPlank = true;
							carryPlank++;
							setCarryLength(Math.abs(xMan - xClick)-1);						
						}
					}	
				}
				
				//The man is on a stump, and click on a plank between another stump
				if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "plank")
				{
					//Click on the above of man_stump, and below of another stump
					if(xMan > xClick)
					{
						//initialize the length of a plank
						int length = 0;
						//measure the length of the plank
						for(int i=1;i<=MAX_LENGTH;i++)
						{
							if(mapArray3[xMan-i][yMan] == "plank")
							{
								length++;
							}			
							else if(mapArray3[xMan-i][yMan] == "stump")
							{
								break;
							}
						}
							
						for(int i=1;i<=length;i++)
						{
							//If in the middle of man_stump and another stump has other stump, can not pick up
							if(mapArray3[xMan-i][yMan] == "stump")
							{
								canCarryPlank = false;
								break;
							}
							//If in the middle of man_stump and another stump is not all be plank, can not pick up
							if(mapArray3[xMan-i][yMan] != "plank")
							{
								canCarryPlank = false;
								break;
							}	
						}
						
						//Test whether the man and the click is all be plank
						for(int i=1;i<Math.abs(xClick-xMan);i++)
						{
							if(mapArray3[xMan-i][yMan] != "plank")
							{
								canCarryPlank = false;
								break;
							}
						}
						
						//the operation when the man pick up a plank
						if(canCarryPlank == true && carryPlank == 0)
						{
							for(int i=1;i<=length;i++)
							{
								setImageIcon(image.getWater1(), button[xMan-i][yMan]);
								mapArray3[xMan-i][yMan] = "water";
							}
							
							haveCarryPlank = true;
							carryPlank++;
							setCarryLength(length);
						}			
					}
					
					//Click on the below of man_stump, and above of another stump
					if(xMan < xClick)
					{
						//initialize the length of a plank
						int length = 0;
						//measure the length of the plank
						for(int i=1;i<=MAX_LENGTH;i++)
						{
							if(mapArray3[xMan+i][yMan] == "plank")
							{
								length++;
							}						
							else if(mapArray3[xMan+i][yMan] == "stump")
							{
								break;
							}
						}
	
						for(int i=1;i<=length;i++)
						{
							//If in the middle of man_stump and another stump has other stump, can not pick up
							if(mapArray3[xMan+i][yMan] == "stump")
							{
								canCarryPlank = false;
								break;
							}
							//If in the middle of man_stump and another stump is not all be plank, can not pick up
							if(mapArray3[xMan+i][yMan] != "plank")
							{
								canCarryPlank = false;
								break;
							}	
						}
						
						//Test whether the man and the click is all be plank
						for(int i=1;i<Math.abs(xClick-xMan);i++)
						{
							if(mapArray3[xMan+i][yMan] != "plank")
							{
								canCarryPlank = false;
								break;
							}
						}
						
						//the operation when the man pick up a plank
						if(canCarryPlank == true && carryPlank == 0)
						{
							for(int i=1;i<=length;i++)
							{
								setImageIcon(image.getWater1(), button[xMan+i][yMan]);
								mapArray3[xMan+i][yMan] = "water";
							}
							haveCarryPlank = true;
							carryPlank++;
							setCarryLength(length);
						}
					}	
				}
			}
			
			//Horizontal Direction
			if((xMan == xClick) && Math.abs(yMan - yClick)>0 && Math.abs(yMan-yClick)<7)
			{
				//After picking up a plank, the man is on a stump, and click is a water
				if(haveCarryPlank == true && carryPlank == 1)
				{
					if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "water")
					{
						canCarryPlank = false;
					}
				
				}
				//The man is on a stump, and click on a stump
				if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "stump")
				{
					//Click on the left of a man_stump
					if(yMan > yClick)
					{						
						for(int i=1;i<Math.abs(yMan - yClick);i++)
						{
							//If in the middle of man_stump and clicked stump has other stump, can not pick up
							if(mapArray3[xMan][yMan-i] == "stump")
							{
								canCarryPlank = false;
								break;
							}
							//If in the middle of man_stump and another stump is not all be plank, can not pick up
							if(mapArray3[xMan][yMan-i] != "plank")
							{
								canCarryPlank = false;
								break;
							}
								
						}
						
						//the operation when the man pick up a plank
						if(canCarryPlank == true && carryPlank == 0)
						{
							for(int i=1;i<Math.abs(yMan - yClick);i++)
							{
								setImageIcon(image.getWater1(), button[xMan][yMan-i]);
								mapArray3[xMan][yMan-i] = "water";
							}
							haveCarryPlank = true;
							carryPlank++;
							setCarryLength(Math.abs(yMan - yClick)-1);
							
						}
					}
					
					//Click on the right of a man_stump
					else if(yMan < yClick)
					{
						for(int i=1;i<Math.abs(yMan - yClick);i++)
						{
							//If in the middle of man_stump and clicked stump has other stump, can not pick up
							if(mapArray3[xMan][yMan+i] == "stump")
							{
								canCarryPlank = false;
								break;
							}
							//If in the middle of man_stump and another stump is not all be plank, can not pick up
							if(mapArray3[xMan][yMan+i] != "plank")
							{
								canCarryPlank = false;
								break;
							}						
						}
						//the operation when the man pick up a plank
						if(canCarryPlank == true && carryPlank == 0)
						{
							for(int i=1;i<Math.abs(yMan - yClick);i++)
							{
								setImageIcon(image.getWater1(), button[xMan][yMan+i]);
								mapArray3[xMan][yMan+i] = "water";
							}
							haveCarryPlank = true;
							carryPlank++;
							setCarryLength(Math.abs(yMan - yClick)-1);
						}
					}	
				}
								
				//The man is on a stump, and click on a plank between another stump
				if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "plank")
				{
					//Click on the left of man_stump, and right of another stump
					if(yMan > yClick)
					{
						//initialize the length of a plank
						int length = 0;
						//measure the length of the plank
						for(int i=1;i<=MAX_LENGTH;i++)
						{
							if(mapArray3[xMan][yMan-i] == "plank")
							{
								length++;
							}
							else if(mapArray3[xMan][yMan-i] == "stump")
							{
								break;
							}
						}
	
						for(int i=1;i<=length;i++)
						{
							//If in the middle of man_stump and another stump has other stump, can not pick up
							if(mapArray3[xMan][yMan-i] == "stump")
							{
								canCarryPlank = false;
								break;
							}
							//If in the middle of man_stump and another stump is not all be plank, can not pick up
							if(mapArray3[xMan][yMan-i] != "plank")
							{
								canCarryPlank = false;
								break;
							}	
						}
						
						//Test whether the man and the click is all be plank
						for(int i=1;i<Math.abs(yClick-yMan);i++)
						{
							if(mapArray3[xMan][yMan-i] != "plank")
							{
								canCarryPlank = false;
								break;
							}
						}
						
						//the operation when the man pick up a plank
						if(canCarryPlank == true && carryPlank == 0)
						{
							for(int i=1;i<=length;i++)
							{
								setImageIcon(image.getWater1(), button[xMan][yMan-i]);
								mapArray3[xMan][yMan-i] = "water";
							}
							haveCarryPlank = true;
							carryPlank++;
							setCarryLength(length);
						}		
					}
					
					//Click on the right of man_stump, and left of another stump
					if(yMan < yClick)
					{
						//initialize the length of a plank
						int length = 0;
						//measure the length of the plank
						for(int i=1;i<=MAX_LENGTH;i++)
						{
							if(mapArray3[xMan][yMan+i] == "plank")
							{
								length++;
							}
							else if(mapArray3[xMan][yMan+i] == "stump")
							{
								break;
							}
						}
	
						for(int i=1;i<=length;i++)
						{
							//If in the middle of man_stump and another stump has other stump, can not pick up
							if(mapArray3[xMan][yMan+i] == "stump")
							{
								canCarryPlank = false;
								break;
							}
							//If in the middle of man_stump and another stump is not all be plank, can not pick up
							if(mapArray3[xMan][yMan+i] != "plank")
							{
								canCarryPlank = false;
								break;
							}	
						}
						
						//Test whether the man and the click is all be plank
						for(int i=1;i<Math.abs(yClick-yMan);i++)
						{
							if(mapArray3[xMan][yMan+i] != "plank")
							{
								canCarryPlank = false;
								break;
							}
						}
						
						//the operation when the man pick up a plank
						if(canCarryPlank == true && carryPlank == 0)
						{
							for(int i=1;i<=length;i++)
							{
								setImageIcon(image.getWater1(), button[xMan][yMan+i]);
								mapArray3[xMan][yMan+i] = "water";
							}
							haveCarryPlank = true;
							carryPlank++;
							setCarryLength(length);
						}	
					}	
				}	
			}
		}
	}
	
	/**
	 * The method to place a plank in map1
	 * A plank may be placed between two stumps in either the horizontal or vertical direction
	 * Provided it is of the exact size to fit between those stumps
	 * Can not be laid diagonally.
	 * A plank cannot cross or be laid on or across another plank
	 * The player may rotate a plank to be either horizontal or vertical
	 * @param xMan  the x-location of man
	 * @param yMan  the y-location of man
	 * @param xClick  the x-location of click
	 * @param yClick  the y-location of click
	 */
	public void setPlank(int xMan, int yMan,int xClick, int yClick)
	{
		if(canCarryPlank == false)//Distinguish between two functions of right click
		{
			if(haveCarryPlank == true)
			{
				SET_LENGTH = getCarryLength();
				//Vertical Direction
				if((yMan == yClick) && Math.abs(xMan - xClick)>0 && Math.abs(xMan-xClick)<7)
				{
					//The man is on a stump, and click on a stump
					if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "stump")
					{
						//Click on the above of a man_stump
						if(xMan > xClick)
						{
							for(int i=1;i<Math.abs(xMan - xClick);i++)
							{
								//If in the middle of man_stump and clicked stump has other stump, can not place
								if(mapArray3[xMan-i][yMan] == "stump")
								{
									canSetPlank = false;
									break;
								}
								//If in the middle of man_stump and clicked stump is not all be water, can not place
								if(mapArray3[xMan-i][yMan] != "water")
								{
									canSetPlank = false;
									break;
								}	
							}

							//If the distance of clicked stump and man stump is not equal to carried plank's length, can not place
							if(Math.abs(xMan - xClick)-1 != SET_LENGTH)
							{
								canSetPlank = false;
							}
							
							//the operation when the can place a plank
							if(canSetPlank == true)
							{
								for(int i=1;i<=SET_LENGTH;i++)
								{
									setImageIcon(image.getPlank2(), button[xMan-i][yMan]);
									mapArray3[xMan-i][yMan] = "plank";
								}
								haveCarryPlank = false;
								carryPlank--;
							}
						}
						
						//Click on the below of a man_stump
						else if(xMan < xClick)
						{
							for(int i=1;i<Math.abs(xMan - xClick);i++)
							{
								//If in the middle of man_stump and clicked stump has other stump, can not place
								if(mapArray3[xMan+i][yMan] == "stump")
								{
									canSetPlank = false;
									break;
								}
								//If in the middle of man_stump and clicked stump is not all be water, can not place
								if(mapArray3[xMan+i][yMan] != "water")
								{
									canSetPlank = false;
									break;
								}	
							}
							
							//If the distance of clicked stump and man stump is not equal to carried plank's length, can not place
							if(Math.abs(xMan - xClick)-1 != SET_LENGTH)
							{
								canSetPlank = false;
							}
							
							//the operation when the can place a plank
							if(canSetPlank == true)
							{
								for(int i=1;i<=SET_LENGTH;i++)
								{
									setImageIcon(image.getPlank2(), button[xMan+i][yMan]);
									mapArray3[xMan+i][yMan] = "plank";
								}
								haveCarryPlank = false;
								carryPlank--;
							}
						}	
					}
					
					
					//The man is on a stump, and click on a water between another stump
					if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "water")
					{
						//Click on the above of man_stump, and below of another stump
						if(xMan > xClick)
						{
							//initialize the length of a plank
							int distance = 0;//The length of man needs to move to next stump
							//measure the length of the plank
							for(int i=1;i<=MAX_LENGTH+1;i++)//fixed
							{
								//Set the limit of the top
								if(xMan-i == 0)
								{
									distance++;
									break;
								}
								//measure the distance
								if(mapArray3[xMan-i][yMan] == "water")
								{
									distance++;
								}
								else if(mapArray3[xMan-i][yMan] == "stump")
								{
									distance++;
									break;
								}	
							}
							
							//Test whether the man and click is all be water
							for(int i=1;i<Math.abs(xClick-xMan);i++)
							{
								if(mapArray3[xMan-i][yMan] != "water")
								{
									canSetPlank = false;
									break;
								}
							}

							//If the end is not another stump, can not place
							if(mapArray3[xMan-distance][yMan] != "stump")
							{
								canSetPlank = false;
							}

							//Except the end point, Test the middle elements
							for(int i=1;i<distance;i++)
							{
								//If in the middle of man_stump and another stump has other stump, can not place
								if(mapArray3[xMan-i][yMan] == "stump")
								{
									canSetPlank = false;
									break;
								}
								//If in the middle of man_stump and another stump is not all be water, can not pick up
								if(mapArray3[xMan-i][yMan] != "water")
								{
									canSetPlank = false;
									break;
								}
							}
							
							//Test the distance whether be suitable for the Plank Length 
							if(distance != SET_LENGTH + 1)
							{
								canSetPlank = false;
							}
							
							//the operation when the man pick up a plank
							if(canSetPlank == true)
							{
								for(int i=1;i<=SET_LENGTH;i++)
								{
									setImageIcon(image.getPlank2(), button[xMan-i][yMan]);
									mapArray3[xMan-i][yMan] = "plank";
								}
								haveCarryPlank = false;
								carryPlank--;
							}		
						}
						
						//Click on the below of man_stump, and above of another stump
						if(xMan < xClick)
						{
							//initialize the length of a plank
							int distance = 0;
							//measure the length of the plank
							for(int i=1;i<=MAX_LENGTH+1;i++)//fixed
							{
								//Set the limit of the bottom
								if(xMan+i == ROW)
								{
									break;
								}
								//measure the distance
								if(mapArray3[xMan+i][yMan] == "water")
								{
									distance++;
								}
								else if(mapArray3[xMan+i][yMan] == "stump")
								{
									distance++;
									break;
								}	
							}
							
							//Test whether the man and click is all be water
							for(int i=1;i<Math.abs(xClick-xMan);i++)
							{
								if(mapArray3[xMan+i][yMan] != "water")
								{
									canSetPlank = false;
									break;
								}
							}
							
							//If the end is not another stump, can not place
							if(mapArray3[xMan+distance][yMan] != "stump")
							{
								canSetPlank = false;
							}
							
							//Except the end point, Test the middle elements
							for(int i=1;i<distance;i++)
							{
								//If in the middle of man_stump and another stump has other stump, can not place
								if(mapArray3[xMan+i][yMan] == "stump")
								{
									canSetPlank = false;
									break;
								}
								//If in the middle of man_stump and another stump is not all be water, can not place
								if(mapArray3[xMan+i][yMan] != "water")
								{
									canSetPlank = false;
									break;
								}	
							}
							
							//Test the distance whether be suitable for the Plank Length
							if(distance != SET_LENGTH + 1)
							{
								canSetPlank = false;
							}
							
							//the operation when the man place a plank
							if(canSetPlank == true)
							{
								for(int i=1;i<=SET_LENGTH;i++)
								{
									setImageIcon(image.getPlank2(), button[xMan+i][yMan]);
									mapArray3[xMan+i][yMan] = "plank";
								}
								haveCarryPlank = false;
								carryPlank--;
							}	
						}	
					}
				}
				
				//Horizontal Direction
				if((xMan == xClick) && Math.abs(yMan - yClick)>0 && Math.abs(yMan-yClick)<7)
				{
					//The man is on a stump, and click on a stump
					if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "stump")
					{
						//Click on the left of a man_stump
						if(yMan > yClick)
						{
							for(int i=1;i<Math.abs(yMan - yClick);i++)
							{
								//If in the middle of man_stump and clicked stump has other stump, can not place
								if(mapArray3[xMan][yMan-i] == "stump")
								{
									canSetPlank = false;
									break;
								}
								//If in the middle of man_stump and clicked stump is not all be water, can not place
								if(mapArray3[xMan][yMan-i] != "water")
								{
									canSetPlank = false;
									break;
								}	
							}
							
							//If the distance of clicked stump and man stump is not equal to carried plank's length, can not place
							if(Math.abs(yMan - yClick)-1 != SET_LENGTH)
							{
								canSetPlank = false;
							}
							
							//the operation when the can place a plank
							if(canSetPlank == true)
							{
								for(int i=1;i<Math.abs(yMan - yClick);i++)
								{
									setImageIcon(image.getPlank1(), button[xMan][yMan-i]);
									mapArray3[xMan][yMan-i] = "plank";
								}
								haveCarryPlank = false;
								carryPlank--;
							}
						}
						//Click on the right of a man_stump
						else if(yMan < yClick)
						{
							for(int i=1;i<Math.abs(yMan - yClick);i++)
							{
								//If in the middle of man_stump and clicked stump has other stump, can not place
								if(mapArray3[xMan][yMan+i] == "stump")
								{
									canSetPlank = false;
									break;
								}
								//If in the middle of man_stump and clicked stump is not all be water, can not place
								if(mapArray3[xMan][yMan+i] != "water")
								{
									canSetPlank = false;
									break;
								}	
							}
							
							//If the distance of clicked stump and man stump is not equal to carried plank's length, can not place
							if(Math.abs(yMan - yClick)-1 != SET_LENGTH)
							{
								canSetPlank = false;
							}
							
							//the operation when the can place a plank
							if(canSetPlank == true)
							{
								for(int i=1;i<Math.abs(yMan - yClick);i++)
								{
									setImageIcon(image.getPlank1(), button[xMan][yMan+i]);
									mapArray3[xMan][yMan+i] = "plank";
								}
								
								haveCarryPlank = false;
								carryPlank--;
							}
						}	
					}
										
					//The man is on a stump, and click on a water between another stump
					if(mapArray3[xMan][yMan] == "stump" && mapArray3[xClick][yClick] == "water")
					{
						//Click on the left of man_stump, and right of another stump
						if(yMan > yClick)
						{
							//initialize the length of a plank
							int distance = 0;
							//measure the length of the plank
							for(int i=1;i<=MAX_LENGTH+1;i++)//fixed
							{
								//Set the limit of the left
								if(yMan-i == 0)
								{
									distance++;
									break;
								}
								//measure the distance
								if(mapArray3[xMan][yMan-i] == "water")
								{
									distance++;
								}
								else if(mapArray3[xMan][yMan-i] == "stump")
								{
									distance++;
									break;
								}	
							}
							
							//Test whether the man and click is all be water
							for(int i=1;i<Math.abs(yClick-yMan);i++)
							{
								if(mapArray3[xMan][yMan-i] != "water")
								{
									canSetPlank = false;
									break;
								}
							}
							//If the end is not another stump, can not place
							if(mapArray3[xMan][yMan-distance] != "stump")
							{
								canSetPlank = false;
							}
							
							//Except the end point, Test the middle elements
							for(int i=1;i<distance;i++)
							{
								//If in the middle of man_stump and another stump has other stump, can not place
								if(mapArray3[xMan][yMan-i] == "stump")
								{
									canSetPlank = false;
									break;
								}
								//If in the middle of man_stump and another stump is not all be water, can not place
								if(mapArray3[xMan][yMan-i] != "water")
								{
									canSetPlank = false;
									break;
								}	
							}
							
							//Test the distance whether be suitable for the Plank Length
							if(distance != SET_LENGTH + 1)
							{
								canSetPlank = false;
							}
							
							//the operation when the man place a plank
							if(canSetPlank == true)
							{
								for(int i=1;i<=SET_LENGTH;i++)
								{
									setImageIcon(image.getPlank1(), button[xMan][yMan-i]);
									mapArray3[xMan][yMan-i] = "plank";
								}
								haveCarryPlank = false;
								carryPlank--;
							}		
						}
						
						//Click on the right of man_stump, and left of another stump
						if(yMan < yClick)
						{
							//initialize the length of a plank
							int distance = 0;
							//measure the length of the plank
							for(int i=1;i<=MAX_LENGTH+1;i++)//fixed
							{
								//Set the limit of the right
								if(yMan+i == COLUMN)
								{
									break;
								}
								//measure the distance
								if(mapArray3[xMan][yMan+i] == "water")
								{
									distance++;
								}
								else if(mapArray3[xMan][yMan+i] == "stump")
								{
									distance++;
									break;
								}	
							}
							
							//Test whether the man and click is all be water
							for(int i=1;i<Math.abs(yClick-yMan);i++)
							{
								if(mapArray3[xMan][yMan+i] != "water")
								{
									canSetPlank = false;
									break;
								}
							}
							
							//If the end is not another stump, can not place
							if(mapArray3[xMan][yMan+distance] != "stump")
							{
								canSetPlank = false;
							}
								
							//Except the end point, Test the middle elements
							for(int i=1;i<distance;i++)
							{
								//If in the middle of man_stump and another stump has other stump, can not place
								if(mapArray3[xMan][yMan+i] == "stump")
								{
									canSetPlank = false;
									break;
								}
								//If in the middle of man_stump and another stump is not all be water, can not place
								if(mapArray3[xMan][yMan+i] != "water")
								{
									canSetPlank = false;
									break;
								}
							}

							//Test the distance whether be suitable for the Plank Length
							if(distance != SET_LENGTH + 1)
							{
								canSetPlank = false;
							}
							
							//the operation when the man place a plank
							if(canSetPlank == true)
							{
								for(int i=1;i<=SET_LENGTH;i++)
								{
									setImageIcon(image.getPlank1(), button[xMan][yMan+i]);
									mapArray3[xMan][yMan+i] = "plank";
								}
								haveCarryPlank = false;
								carryPlank--;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * This method set the integer length for CARRY_LENGTH 
	 * @param length  the integer which need to set for CARRY_LENGTH
	 */
	public void setCarryLength(int length)
	{
		CARRY_LENGTH = length;
	}
	
	/**
	 * This method get the integer of CARRY_LENGTH
	 * @return  the integer of CARRY_LENGTH
	 */
	public int getCarryLength()
	{
		return CARRY_LENGTH;
	}
	
	/**
	 * This method is generated by implements MouseListener
	 * Not support in this game
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is generated by implements MouseListener
	 * Not support in this game
	 */
	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is generated by implements MouseListener
	 * Not support in this game
	 */
	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is generated by implements MouseListener
	 * Not support in this game
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
}
