// Defining the SUV vehicle in traffic simulation. Last modified 5/1 by Jason Randolph.

import java.awt.Color;
import java.awt.Graphics;

public class SUV extends Vehicle {
	
	// Taking in the x and y values of the SUV's starting point and defining its width, height, and speed.
	public SUV(int newx, int newy) {
		super(newx, newy);
		width = 60;
		height = 30;
		speed = 8;
	}
	
	// Drawing the SUV in green.
	public void paintMe(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
}