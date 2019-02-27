import javax.swing.*;

/**
 * Timer class is the class that implements a component to record the time from each map started
 * and return a JFieldText to apply for GUI construction of each map class
 * 
 * 
 *
 */
public class Timer implements Runnable
{
    private JTextField timer = new JTextField();
    private int second = 0;
    private int minute = 0;
   
    /**
     * The method of generating the time from starting each level(map)
     * Implement the timer
     */
    public void run()
    {
        while(true)
       {
        	//Regular the time-record format
        	if(minute < 10 && second < 10)
        	{
        		timer.setText("0" + minute +":0"+ second);
        	}
        	
        	else if(minute <10 && second >= 10)
        	{
        		timer.setText("0" + minute +":"+ second);
        	}
        	
        	else if(minute >= 10 && second <10)
        	{
        		timer.setText(minute +":0"+ second);
        	}
        	else if(minute >= 10 && second >=10)
        	{
        		timer.setText(minute +":"+ second);
        	}
        	
        	try{
                Thread.sleep(1000);
                second++;
                //Each 60 seconds carry to the minute
                if(second == 60)
                {
                    second = 0;
                    minute++;
                 }
             }catch(InterruptedException e)
             {
                 e.printStackTrace();
             }     
                 
       }
    }
    
    /**
     * Return timer as JTextField
     * @return JTextField timer
     */
    public JTextField text()
    {
        while(true)
        {
            return timer;
        }
    }
    
}
