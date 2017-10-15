package knightsTour;

public class Tour {
	private Board board;
	private final int[] x = {2, 2, 1, 1, -1, -1, -2, -2}; 
	private final int[] y = {1, -1, 2, -2, 2, -2, 1, -1};
	boolean solved = false;
	boolean closed = false;
	boolean bounded = true;
	
	public Tour(){
		board = Knights.board;
		if(bounded)
			startOptimised();
		else
			start();
	}
	
	/**
	 * Starts the knight at every possible position on the board.
	 */
	public void start(){
		for(int row = 0; row < board.size; row++){
			for(int col = 0; col < board.size; col++){
				Knights.resetBoard();
				if(run(col, row, 0)){
					solved = true;
					return;
				}
			}
		}
	}
	
	/**
	 * Recursively check every valid move to see if it gives a solution.
	 * @param col
	 * @param row
	 * @param num
	 * @return
	 */
	public boolean run(int col, int row, int num){
		if(num == board.size*board.size) //filled in all positions
			return true;
		for(int pos = 0; pos < x.length; pos++){
			int newCol = col+x[pos];
			int newRow = row+y[pos];
			if(closed){
				for(int lastPos = 0; lastPos < x.length; lastPos++){
					int newCol2 = col+x[pos];
					int newRow2 = row+y[pos];
					if(isClosedTour(newCol2, newRow2))
						return true;
				}
			}
			else if(isValidMove(newCol, newRow)){
				board.set(newCol, newRow, col, row, num);
				if(run(newCol, newRow, num+1))
					return true;
			}
		}
		board.set(col, row, -1);
		return false;
	}
	
	public void startOptimised(){
		for(int row = 0; row < board.size/2; row++){
			for(int col = row; col < board.size/2; col++){
				Knights.resetBoard();
				if(run(col, row, 0)){
					solved = true;
					return;
				}
			}
		}
	}
	
	public boolean runOptimised(int col, int row, int num){
		if(num == board.size*board.size) //filled in all positions
			return true;
		for(int pos = 0; pos < x.length; pos++){
			int newCol = col+x[pos];
			int newRow = row+y[pos];
			if(closed){
				for(int lastPos = 0; lastPos < x.length; lastPos++){
					int newCol2 = col+x[pos];
					int newRow2 = row+y[pos];
					if(isClosedTour(newCol2, newRow2))
						return true;
				}
			}
			else if(isValidMove(newCol, newRow)){
				board.set(newCol, newRow, col, row, num);
				if(run(newCol, newRow, num+1))
					return true;
			}
		}
		board.set(col, row, -1);
		return false;
	}
	
	/**
	 * Checks if knight can be moved to this position.
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isValidMove(int x, int y){
		return x>=0 && x<board.size && y>=0 && y<board.size && board.get(x, y) == -1;
	}
	
	private boolean isClosedTour(int x, int y){
		return x>=0 && x<board.size && y>=0 && y<board.size && board.get(x, y) == 0;
	}
}
