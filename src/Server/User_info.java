package Server;

public class User_info {
	String name;
	Integer score;
	boolean draw;
	
	User_info(String name, Integer score){
		this.name = name;
		this.score = score;
		draw = false;
	}
	
	void setdraw() {
		draw = true;
	}
	
	void win() {
		score++;
	}
}
