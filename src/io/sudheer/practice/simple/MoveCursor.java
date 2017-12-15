package io.sudheer.practice.simple;

/**
 * 
 */
/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */

public class MoveCursor {
	
	java.awt.Robot robot;

	public MoveCursor()
	{
		changeCursorLocation();
	}

	public void changeCursorLocation()
	{
		try
		{
			robot = new java.awt.Robot();
			final int W = 1366;//<---enter sceen dimension
			final int H = 768; //<---ditto
			for(int x = 0; x < 20; x++)
			{
				Thread.sleep(500);
				robot.mouseMove((int)(Math.random()*W), (int)(Math.random()*H));
			}
		}
		catch(Exception e){e.printStackTrace();}
	}

	public static void main(String[] args){new MoveCursor();}

}



