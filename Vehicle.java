// This class creates vehicles. Last modified by Jason Randolph on 5/1.
import java.awt.Graphics;

public class Vehicle {
	// Declaring variables.
	int x;
	int y;
	int width = 0;
	int height = 0;
	int speed = 0;

	// Taking in the x and y points of the vehicle and setting them to their corresponding values.
	public Vehicle(int newx, int newy) {
		x = newx;
		y= newy;
	}
	
	// Graphics engine to be used in the road class.
	public void paintMe(Graphics g) {
	}
	
	// Getting the x value of the vehicle.
	public int getX() {
		return x;
	}
	
	// Setting the x value of the vehicle.
	public void setX(int newx) {
		x = newx;
	}
	
	// Getting the speed of the vehicle to be used for movement.
	public int getSpeed() {
		return speed;
	}
	
	// Getting the y value of the vehicle.
	public int getY() {
		return y;
	}
	
	// Getting the vehicle's width.
	public int getWidth() {
		return width;
	}
	
	// Setting the y value of the vehicle.
	public void setY(int newy) {
		y = newy;
	}
}