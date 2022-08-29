package puissance4;

public class Cord{
	private int x;
	private int y;
	
	public Cord(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int x){
		this.y = y;
	}
	
	public String toString(){
		return "("+this.x+", "+this.y+")";
	}
}