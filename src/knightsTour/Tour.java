package knightsTour;

public class Tour {
	private Board board;
	private final int[] x = {2, 2, 1, 1, -1, -1, -2, -2}; 
	private final int[] y = {1, -1, 2, -2, 2, -2, 1, -1};
	boolean solved = false;
	
	public Tour(){
		board = Knights.board;
		start();
	}
	
	/**
	 * Starts the knight at every possible position on the board.
	 */
	public void start(){
		for(int row = 0; row < board.size; row++){
			for(int col = 0; col < board.size; col++){
				Knights.resetBoard();
				if(run(col, row, 0))
					solved = true;
				else
					board.set(col, row, -1);
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
			if(isValidMove(newCol, newRow)){
				board.set(newCol, newRow, col, row, num);
				//System.out.printf("TRYING POS %d, %d - MOVE NUM: %d\n", newCol, newRow, num);
				if(run(newCol, newRow, num++))
					return true;
				else
					board.set(newCol,  newRow, -1);
			}
		}
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
}
