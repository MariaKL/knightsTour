package knightsTour;

import java.awt.Color;

import ecs100.*;

public class Board {
	Entry[][] backBoard;
	int size;
	int left = 50;
	int top = 50;
	int sqsize = 80;
	
	public Board(){
		size = 5;
		reset();
	}
	
	public Board(int size){
		this.size = size;
		reset();
	}
	
	public void reset(){
		backBoard = new Entry[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				backBoard[i][j] = new Entry();
			}
		}
	}
	
	/**
	 * Set a value for this cell without a parent value.
	 * @param x
	 * @param y
	 * @param num
	 */
	public void set(int x, int y, int num){
		backBoard[y][x] = new Entry(-1, -1, num);
	}
	
	/**
	 * Set a value for a cell with coordinates for the parent cell
	 * @param x
	 * @param y
	 * @param pX
	 * @param pY
	 * @param num
	 */
	public void set(int x, int y, int pX, int pY, int num){
		backBoard[y][x] = new Entry(pX, pY, num);
	}
	
	/**
	 * Return the move number associated with this cell.
	 * @param x
	 * @param y
	 * @return
	 */
	public int get(int x, int y){
		return backBoard[y][x].num;
	}
	
	/**
	 * Draws the cell in colours red->green for the solution with the move number
	 * in the cell.
	 * If the cell was not visited then a black cell is drawn.
	 */
	public void redraw(){
		UI.setColor(Color.BLACK);
		UI.drawRect(left, top, sqsize*size, sqsize*size);
		for(int row = 0; row < size; row++){
			for(int col = 0; col < size; col++){
				if(backBoard[row][col].num==-1){
					UI.setColor(Color.LIGHT_GRAY);
					UI.fillRect(left+col*sqsize, top+row*sqsize, sqsize, sqsize);
				}
				else{
					UI.setColor(getColor(backBoard[row][col].num/(size*size)));
					UI.fillRect(left+col*sqsize, top+row*sqsize, sqsize, sqsize);
					UI.setColor(Color.BLACK);
					UI.setFontSize(20);
					UI.drawString(backBoard[row][col].num+"", left+col*sqsize + sqsize/2, top+row*sqsize + sqsize/2 + 5);
					UI.setColor(Color.BLACK);
					UI.drawRect(left+col*sqsize, top+row*sqsize, sqsize, sqsize);
				}
			}
		}
	}
	
	/**
	 * Calculated the colour of the cell based off the move number.
	 * From https://stackoverflow.com/questions/340209/generate-colors-between-red-and-green-for-a-power-meter
	 * @param power
	 * @return
	 */
	public Color getColor(double power){
	    double H = power * 0.4; // Hue (note 0.4 = Green)
	    double S = 0.9; // Saturation
	    double B = 0.9; // Brightness

	    return Color.getHSBColor((float)H, (float)S, (float)B);
	}
	
	public String toString(){
		String s = "";
		for(int row = 0; row < size; row++){
			for(int col = 0; col < size; col++){
				s+=backBoard[row][col].num + "\t";
			}
			s+="\n";
		}
		return s;
	}
}
