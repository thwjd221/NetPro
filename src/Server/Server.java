package Server;

import java.util.Vector;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

class GetName {
	Socket server;
	GetName(Socket server){
		this.server = server;
	}
	
    void login(){
    	try {
             String name;  
             name = (String)Server.total_ois.get(server).readObject();
             
             Server.info.put(server, new User_info(name, 0));
             Server.sl.setUserlist();
             Server.sl.toLog("[" + name + "]" + " 님이 입장하셨습니다.");
    	} catch (IOException e) {
	         e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

class ServerThread extends Thread {
	Socket server;

	String str;
	int i = 0;
	String answer;
	
	//String answer;
	String pass = "correct!";
	String fail = "no!";
	
	public ServerThread(Socket server, String answer) {
		this.server = server;
		this.answer = answer;
	}

    
    void remove(Socket temp) {
    	String name;
		name = Server.info.get(temp).name;
		
    	Server.total_socket.remove(temp);

		Server.info.remove(temp);	//user_info 지우기
		Server.sl.setUserlist();
		Server.sl.toLog("[" + name + "]" + " 님이 퇴장하셨습니다.");
    }
    
    void sendAll(String msg, String err) {
    	synchronized (this) {
			for (int k = 0; k < Server.total_socket.size(); k++) {
				ObjectOutputStream temp = (ObjectOutputStream) Server.total_oos.get(k);
				
				try {
					temp.writeObject(msg);
					temp.flush();
					temp.reset();
				} catch (Exception e) {
					System.out.println(err);
				}
			}
    	}
    }
	

     public void run() {

		/* 5. 
		 * 
		 */

		try {
			while(true) {
				str = (String) Server.total_ois.get(server).readObject();
				String msg = "[" + Server.info.get(server).name + "]: " + str;
				Server.sl.toLog(msg);

				
				sendAll(msg, "송신 오류");
				
				// 정답 비교
				if (str.equals(answer)) { // 정답임
					sendAll(pass, "송신 오류");
					//flag = false;	//현재 turn 종료
					break;
				} else { // 정답아님
					sendAll(fail, "송신 오류");
				}
        	}
		} catch (Exception e) {
			System.out.println("정답 수신 오류");
			e.printStackTrace();
		}
		notifyAll();
	}			
}

class Game extends Thread{
	int turn = 0;
	synchronized public void run() {
		//for(int i = 0; i < 4; i++) {	//4번 실행...? 
			
		
		int i = turn;
		Vector<Point> draw_panel = null;
		
		Socket givesub = (Socket) Server.total_socket.get(i);
		String draw_user = Server.info.get(givesub).name;
		String answer = Server.subject[i];
		
		/* 2.
		 * 현재 그리는 유저 이름 송신
		 * */
		Server.sl.toLog("[" + draw_user + "]" + " 님이 그림을 그립니다.");
		for (int k = 0; k < Server.total_socket.size(); k++) {
			ObjectOutputStream temp = (ObjectOutputStream) Server.total_oos.get(k);
			try {
				temp.writeObject(draw_user);
				temp.flush();
				temp.reset();
			} catch (Exception e) {
				System.out.println("ready trans fail");
			}
		}
		
		
		/* 3.
		 * 주제 송신
		 * */
		Server.sl.toLog("[" + draw_user + "]" + " 님의 주제: " + answer);
		
		try {
			ObjectOutputStream temp = (ObjectOutputStream) Server.total_oos.get(i);
			temp.writeObject(answer);
			temp.flush();
			temp.reset();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		/* 
		 * 그림 수신
		 * */
		try {
			ObjectInputStream temp = (ObjectInputStream) Server.total_ois.get(givesub);
			draw_panel = (Vector<Point>) temp.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* 4.
		 * 그림 송신
		 * */
		for (int k = 0; k < Server.total_socket.size(); k++) {
			try {
				ObjectOutputStream temp = (ObjectOutputStream) Server.total_oos.get(k);
				temp.writeObject(draw_panel);
				temp.flush();
				temp.reset();
			} catch (Exception e1) {
				System.out.println("사진 송신 오류");
				Server.total_socket.remove(k);
			}
		}

		Server.sl.toLog("[" + draw_user + "]" + " 님의 그림 송수신 완료");
		turn++;

		for(int j = 0; j<4;j++) {
			ServerThread thread = new ServerThread((Socket) Server.total_socket.get(j), answer);
			thread.start();
		}
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//}
	}
}

public class Server {
	static HashMap<Socket, ObjectInputStream> total_ois = new HashMap<Socket, ObjectInputStream>();
	static ArrayList total_oos = new ArrayList();
	static ArrayList total_socket = new ArrayList();
	static String[] subject = new String[] { "cat", "school", "sandwich", "friend" };
	
    static Server_log sl = new Server_log();
    //static HashMap<String, Integer> info = new HashMap<String, Integer>();
    static HashMap<Socket, User_info> info = new HashMap<Socket, User_info>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss;
		ss = new ServerSocket(8888);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    sl.setVisible(true);
                    sl.toLog("[Server]: 서버가 시작되었습니다.");
                }
            });
		while (Server.total_socket.size() != 4) {
			Socket server = ss.accept();
			
			total_socket.add(server);
			
			try {
				ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
				total_ois.put(server, ois);
				total_oos.add(oos);
			} catch (IOException e) {
				e.printStackTrace();
			}

			/*0.
			 * 로그인
			 * */
			GetName login = new GetName(server);
			login.login();

            	//total_socket.remove(server);
            	//name 얻어오기
				//String name;
				//name = Server.info.get(server).name;
				//Server.info.remove(server);	//user_info 지우기
				//Server.sl.setUserlist();
				//Server.sl.toLog("[" + name + "]" + " 님이 퇴장하셨습니다.");
		}
		Game game = new Game();
			
		game.run();
		Server.sl.toLog("turn");
	}
}
