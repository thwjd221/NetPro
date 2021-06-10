package Server;

public class User_info {
	String name;
	Integer score;
	
	User_info(String name, Integer score){
		this.name = name;
		this.score = score;
	}
	
	void win() {
		score++;
	}
}
