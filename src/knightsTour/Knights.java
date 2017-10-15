package knightsTour;

import ecs100.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer; 

public class Knights implements ActionListener{
	static Board board;	
	Timer timer;
	int mins;
	int secs;
	int msecs;
	
	public Knights(){
		UI.addButton("Size 3", () -> { this.changeSize(3); });
		UI.addButton("Size 5", () -> { this.changeSize(5); });
		UI.addButton("Size 6", () -> { this.changeSize(6); });
		UI.addButton("Size 7", () -> { this.changeSize(7); });
		UI.addButton("Start", this::run);
		//UI.addButton("Stop", this::stop);
		UI.addButton("Quit", UI::quit);
		UI.setKeyListener(this::doKey);
		UI.setDivider(0.1);

		board = new Board();
		timer=new Timer(500, this);
		repaint();
	}
	
	private void run(){
		board = new Board(board.size);
		timer=new Timer(500, this);
		start();
    	Tour tour = new Tour();
    	stop(tour.solved);
	}
	
    private void start(){
    	mins = 0;
    	secs = 0;
    	msecs = 0;
    	timer.start();
    }
	
    private void stop(boolean solved){
    	timer.stop();
    	displayTime();
		if(!solved)
			UI.println("NO SOLUTION FOUND");
		else
			UI.println("SOLUTION FOUND");
		repaint();
    }
	
	public void changeSize(int size){
		board = new Board(size);
		repaint();
	}
	
	/** Respond to key actions */
    public void doKey(String key) {
        switch(key){
        	case "1":
        		if(board.size!=1)
        			changeSize(1);
        		break;
        	case "2":
        		if(board.size!=2)
        			changeSize(2);
        		break;
        	case "3":
        		if(board.size!=3)
        			changeSize(3);
        		break;
        	case "4":
        		if(board.size!=4)
        			changeSize(4);
        		break;
        	case "5":
        		if(board.size!=5)
        			changeSize(5);
        		break;
        	case "6":
        		if(board.size!=6)
        			changeSize(6);
        		break;
        	case "7":
        		if(board.size!=7)
        			changeSize(7);
        		break;
        	case "8":
        		if(board.size!=8)
        			changeSize(8);
        		break;
        	case "q": case "Q":
        		UI.quit();
        		break;
        	default:
        		break;
        }
    }
    
    public static void resetBoard(){
    	board.reset();
    }
    
    private void repaint(){
    	UI.clearGraphics();
    	board.redraw();
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(msecs==500){
			secs++;
			msecs=0;
			if(secs==60){
				mins++;
				secs=0;
				msecs=0;
			}
		}
		else msecs+=500;
		displayTime();
		repaint();
	}
	
	private void displayTime(){
		UI.clearText();
		UI.println("Time is:");
		UI.printf("%d:%d:%d\n", mins, secs, msecs);
	}
	
	public static void main(String[] args) {
		new Knights();
	}
}
