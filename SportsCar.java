// Defining the sports car's vehicle in traffic simulation. Last modified 5/1 by Jason Randolph.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class SportsCar extends Vehicle {
	
	// Taking in the x and y values of the sports car's starting point and defining its width, height, and speed.
	public SportsCar(int newx, int newy) {
		super(newx, newy);
		width = 40;
		height = 20;
		speed = 12;
		
		
	}
	
	// Drawing the sports car in red.
	public void paintMe(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}