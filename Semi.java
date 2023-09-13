// Defining the semi vehicle in traffic simulation. Last modified 5/1 by Jason Randolph.
import java.awt.Color;
import java.awt.Graphics;

// Extends the vehicle class so that it can take the same methods/properties.
public class Semi extends Vehicle {
	// Taking in the x and y values of the semi's starting point and defining its width, height, and speed.
	public Semi(int newx, int newy) {
		super(newx, newy);
		width = 100;
		height = 40;
		speed = 5;
	}
	
	// Drawing the semi in blue.
	public void paintMe(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}