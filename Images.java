import javax.swing.*;

/**
 * Images Class is the class that set all the ImageIcon from each image file,
 * and import file element to the game
 * and get set methods to return each ImageIcon, in order to get ImageIcon in other class.
 *
 * 
 * @version (a version number or a date)
 */
public class Images 
{
	private ImageIcon water1;
    private ImageIcon water2;
    private ImageIcon water3;
    private ImageIcon water4;
	private ImageIcon bank1;
    private ImageIcon bank2;
    private ImageIcon plank1;
    private ImageIcon plank1_man;
    private ImageIcon plank2;
    private ImageIcon plank2_man;
    private ImageIcon stump1;
    private ImageIcon stump1_man;
    private ImageIcon stump2;
    private ImageIcon stump2_man;
    private ImageIcon stump3;
    private ImageIcon stump3_man;
	
    /**
     * Import actual image file to ImageIcon water1.   
     */
    public void Water1()
	{
		water1 = new ImageIcon("RiverCrossingGraphics/water1.jpg"); 
	}

    /**
     * Import actual image file to ImageIcon water2.    
     */
    public void Water2()
	{
		water2 = new ImageIcon("RiverCrossingGraphics/water2.jpg"); 
	}

    /**
     * Import actual image file to ImageIcon water3.    
     */
    public void Water3()
	{
		water3 = new ImageIcon("RiverCrossingGraphics/water3.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon water4.    
     */
    public void Water4()
	{
		water4 = new ImageIcon("RiverCrossingGraphics/water4.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon bank1.    
     */
    public void Bank1()
	{
		bank1 = new ImageIcon("RiverCrossingGraphics/bank1.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon bank2.    
     */
    public void Bank2()
	{
		bank2 = new ImageIcon("RiverCrossingGraphics/bank2.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon plank1.    
     */
    public void Plank1()
	{
		plank1 = new ImageIcon("RiverCrossingGraphics/plank1.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon plank2.    
     */
    public void Plank2()
	{
		plank2 = new ImageIcon("RiverCrossingGraphics/plank2.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon plank1_man.    
     */
    public void Plank1Man()
	{
		plank1_man = new ImageIcon("RiverCrossingGraphics/plank1_man.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon plank2_man.    
     */
    public void Plank2Man()
	{
		plank2_man = new ImageIcon("RiverCrossingGraphics/plank2_man.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon stump1.    
     */
    public void Stump1()
	{
		stump1 = new ImageIcon("RiverCrossingGraphics/stump1.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon stump2.    
     */
    public void Stump2()
	{
		stump2 = new ImageIcon("RiverCrossingGraphics/stump2.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon stump3.    
     */
    public void Stump3()
	{
		stump3 = new ImageIcon("RiverCrossingGraphics/stump3.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon stump1_man.    
     */
    public void Stump1Man()
	{
		stump1_man = new ImageIcon("RiverCrossingGraphics/stump1_man.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon stump2_man.    
     */
    public void Stump2Man()
	{
		stump2_man = new ImageIcon("RiverCrossingGraphics/stump2_man.jpg"); 
	}
    
    /**
     * Import actual image file to ImageIcon stump3_man.    
     */
    public void Stump3Man()
	{
		stump3_man = new ImageIcon("RiverCrossingGraphics/stump3_man.jpg"); 
	}
    
    /**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon water1
     */
    public ImageIcon getWater1()
	{
		return water1;
	}
    
    /**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon water2
     */
    public ImageIcon getWater2()
	{
		return water2;
	}
    
    /**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon water3
     */
    public ImageIcon getWater3()
	{
		return water3;
	}
    
    /**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon water4
     */
    public ImageIcon getWater4()
	{
		return water4;
	}
    
    /**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon bank1
     */
    public ImageIcon getBank1()
	{
		return bank1;
	}
   
    /**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon bank2
     */
	public ImageIcon getBank2()
	{
		return bank2;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon plank1
     */
	public ImageIcon getPlank1()
	{
		return plank1;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon plank2
     */
	public ImageIcon getPlank2()
	{
		return plank2;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon plank1_man
     */
	public ImageIcon getPlank1Man()
	{
		return plank1_man;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon plank2_man
     */
	public ImageIcon getPlank2Man()
	{
		return plank2_man;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon stump1
     */
	public ImageIcon getStump1()
	{
		return stump1;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon stump2
     */
	public ImageIcon getStump2()
	{
		return stump2;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon stump3
     */
	public ImageIcon getStump3()
	{
		return stump3;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon stump1_man
     */
	public ImageIcon getStump1Man()
	{
		return stump1_man;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon stump2_man
     */
	public ImageIcon getStump2Man()
	{
		return stump2_man;
	}
	
	/**
     * Provides an ImageIcon that is used in the game
     * @return ImageIcon stump3_man
     */
	public ImageIcon getStump3Man()
	{
		return stump3_man;
	}
	
}
