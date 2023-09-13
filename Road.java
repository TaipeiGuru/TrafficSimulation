/* Road class for the Traffic Simulation program. It creates the road, adds vehicles, 
 * and handles movements and collisions. Last modified by Jason Randolph on 5/1.
 */

// Imports.
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Road extends JPanel {
	
	// Declaring lane height, road width, and the vehicle array list. 
	final int LANE_HEIGHT = 120;
	final int ROAD_WIDTH = 800;
	ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	int carCount = 0;
	
	public Road() {
		super();
	}
	
	// Adding cars to the road.
	public void addCar(Vehicle v) {
		cars.add(v);
	}
	
	// Drawing the road and cars.  
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		for (int a = LANE_HEIGHT; a < LANE_HEIGHT * 4; a=a+LANE_HEIGHT) {
			for (int b=0; b < getWidth(); b = b+35) {
				g.fillRect(b, a, 30, 5);
			}
		}
		for (int a = 0; a < cars.size(); a++) {
			cars.get(a).paintMe(g);
		}
	}
	
	// Step function, handles car movement and collisions. 
	public void step() {
		for (int a = 0; a < cars.size(); a++) {
			Vehicle v = cars.get(a);
			/* If there's no collision, the car will move ahead by its speed. If it goes off the road, 
			 * it'll be reset to the beginning and the car count will increase.
			 */
			if (collision(v.getX()+v.getSpeed(), v.getY(), v) == false) {
				v.setX(v.getX()+v.getSpeed());
				if(v.getX() > ROAD_WIDTH) {
					if (collision(0, v.getY(), v) == false) {
						v.setX(-100);
						carCount++;
					}
				} 
				
			/* If there is a collision, it checks for a collision in the lanes to either side. If there's 
			 * a free space, the y position of the car will be set to that open lane, causing the car to 
			 * switch lanes.
			 */
			} else {
				if (v.getY() > 40 && collision(v.getX(), v.getY()-LANE_HEIGHT, v) == false) {
					v.setY(v.getY() - LANE_HEIGHT);
				} else if (v.getY() < 40 + 3*LANE_HEIGHT && collision(v.getX(),
					v.getY()+LANE_HEIGHT, v) == false) {
					v.setY(v.getY() + LANE_HEIGHT);
				}
			}
		}
	}
	
	/* This method checks for collisions. The vehicle is taken as a parameter so that its length can be 
	 * used. If the cars are in the same lane and one vehicle is the other, the method returns true. 
	 * Otherwise, it returns false.
	 */
	public boolean collision(int x, int y, Vehicle v) {
		for (int a = 0; a < cars.size(); a++) {
			Vehicle u = cars.get(a);
			if (y == u.getY()) {
				if (u.equals(v) == false) {
					if (x < u.getX()+u.getWidth() && x+v.getWidth() > u.getX()) {
					return true;
					}
				}
			}
		}
		return false;
	}
	
	// Getting the car count. 
	public int getCarCount() {
		return carCount;
	}
	
	// Resetting the car count. 
	public void resetCarCount() {
		carCount = 0;
	}
}