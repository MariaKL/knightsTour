package knightsTour;

public class Entry {
	int parentX;
	int parentY;
	int num;
	
	public Entry(int parentX, int parentY, int num){
		this.parentX = parentX;
		this.parentY = parentY;
		this.num = num;
	}
	
	public Entry(){
		this.parentX = -1;
		this.parentY = -1;
		this.num = -1;
	}
}
