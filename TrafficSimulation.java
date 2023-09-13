/* This is a program designed to simulate traffic using Java. It was created by 
 * Jason Randolph and last modified on 5/1. */

// Imports.
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Implements ActionListeners for buttons and Runnable for the step function.
public class TrafficSimulation implements ActionListener, Runnable {
	
	// Declaring variables.
	JFrame frame = new JFrame("Traffic Simulation");
	Road road = new Road();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton semi = new JButton("Add Semi");
	JButton suv = new JButton("Add SUV");
	JButton sports = new JButton("Add SportsCar");
	JLabel throughput = new JLabel("Throughput: 0");
	Container south = new Container();
	Container west = new Container();
	boolean running = false;
	long startTime = 0;
	int carCount = 0;
	
	// Creating the layout (adding containers, buttons, etc.). 
	public TrafficSimulation() {
		frame.setSize(1000, 550);
		frame.setLayout(new BorderLayout());
		frame.add(road, BorderLayout.CENTER);
		south.setLayout(new GridLayout(1,2));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		south.add(throughput);
		stop.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		west.setLayout(new GridLayout(3,1));
		west.add(semi);
		semi.addActionListener(this);
		west.add(suv);
		suv.addActionListener(this);
		west.add(sports);
		sports.addActionListener(this);
		frame.add(west, BorderLayout.WEST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.repaint();
	}
	
	// Main method.
	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		new TrafficSimulation();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/* If the start button is pressed, the step function begins and the start time is recorded.
		 * The car count is also reset because it was stopped when the program was stopped, and restarts
		 * at 0 when the start button is hit.
		 */
		
		if(e.getSource().equals(start)) {
			if (running == false) {
				running = true;
				road.resetCarCount();
				startTime = System.currentTimeMillis();
				Thread t = new Thread(this);
				t.start();
			}
		}
		
		// If the stop button is pressed, running is set to false and the program stops. 
		if(e.getSource().equals(stop)) {
			running = false;
		}
		
		/* If the semi button is pressed, a new semi is created. If the spot in the first lane is taken,
		 * it'll move to the next lane, and when an open spot is available, the semi will be drawn.
		 */
		if(e.getSource().equals(semi)) {
			Semi semi = new Semi(0, 30);
			road.addCar(semi);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					semi.setX(x);
					semi.setY(y);
					if (road.collision(x, y, semi) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		
		// Same logic as the semi button.
		if(e.getSource().equals(suv)) {
			SUV suv = new SUV(0, 30);
			road.addCar(suv);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					suv.setX(x);
					suv.setY(y);
					if (road.collision(x, y, suv) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		
		// Same logic as the semi button. 
		if(e.getSource().equals(sports)) {
			SportsCar sports = new SportsCar(0, 20);
			road.addCar(sports);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					sports.setX(x);
					sports.setY(y);
					if (road.collision(x, y, sports) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
	}
	
	@Override
	
	/* Running function. While the program is active, the step function is called and the car count is 
	 * recorded. The throughput is also calculated by dividing the car count by the time elapsed. The framee
	 * is then repainted and a try/catch exception is used to catch errors. 
	 */
	public void run() {
		while(running == true) {
			road.step();
			carCount = road.getCarCount();
			double throughputCalc = carCount / (double)(1000 *(System.currentTimeMillis() - startTime));
			throughput.setText("Throughput: " + throughputCalc);
			frame.repaint();
			try {
				Thread.sleep(50);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
