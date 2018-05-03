package io.sudheer.practice.simple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BarChart extends JPanel
{
	private Map<String, Integer> bars =
            new LinkedHashMap<String, Integer>();
	
	/**
	 * Add new bar to chart
	 * @param app to display bar
	 * @param value size of bar
	 */
	public void addBar(String app, int value)
	{
		bars.put(app, value);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		// determine longest bar
		
		int max = Integer.MIN_VALUE;
		for (Integer value : bars.values())
		{
			max = Math.max(max, value);
		}
		
		// paint bars
		
		int width = (getWidth() / bars.size()) - 2;
		int x = 1;
		for (String app : bars.keySet())
		{
			int value = bars.get(app);
			int height = (int) 
                            ((getHeight()-5) * ((double)value / max));
			g.setColor(Color.BLUE);
			g.fillRect(x, getHeight() - height, width, height);
			g.setColor(Color.black);
			g.drawRect(x, getHeight() - height, width, height);
			x += (width + 2);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(bars.size() * 100 + 2, 480);
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Bar Chart");
		BarChart chart = new BarChart();
		chart.addBar("ENERGYPLM", 100);
		chart.addBar("AEROPLM", 8);
		chart.addBar("WINDPLM", 54);
		chart.addBar("POWERPLM", 23);
		chart.addBar("NUCLEARPLM", 25);
		frame.getContentPane().add(chart);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
